package logic;

import java.util.ArrayList;
import java.util.Iterator;
import entity.base.*;
import entity.*;
import item.*;
import sharedObject.RenderableHolder;
import exception.OutOfWeaponException;

public class GameMap {

	private int width;
	private int height;
	private Cell[][] cellMap;
	private ArrayList<CollidableEntity> collidableEntity;
	private ArrayList<Entity> movableEntity;

	/*
	 * public GameMap(int column, int row) { allEntity = new ArrayList<Entity>();
	 * 
	 * setWidth(column); setHeight(row);
	 * 
	 * for (int i = 0; i < row; i++) { for (int j = 0; j < column; j++) {
	 * cellMap[i][j] = new Cell(); } } }
	 */

	public GameMap(String[][] map) {

		int column = map[0].length;
		int row = map.length;

		setWidth(column);
		setHeight(row);

		cellMap = new Cell[row][column];
		collidableEntity = new ArrayList<CollidableEntity>();
		movableEntity = new ArrayList<Entity>();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				cellMap[i][j] = new Cell(j, i);
				RenderableHolder.getInstance().add(cellMap[i][j]);
				switch (map[i][j]) {
				case "0":
					break;
				case "B":
					addCollidableEntity(new BulletItem(j, i), j, i);
					break;
				case "BM":
					addCollidableEntity(new BombItem(j, i), j, i);
					break;
				case "PB":
					addCollidableEntity(new PenetratedBulletItem(j, i), j, i);
					break;
				case "SW":
					addCollidableEntity(new SteelWall(j, i), j, i);
					break;
				case "W":
					addCollidableEntity(new Wall(j, i), j, i);
					break;
				default:
					break;
				}
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void addCollidableEntity(CollidableEntity e, int x, int y) {
		if (cellMap[y][x].IsEmpty()) {
			collidableEntity.add(e);
			RenderableHolder.getInstance().add(e);
			cellMap[y][x].setEntity(e);
			cellMap[y][x].setIsEmpty(false);
		}
	}

	public void addMovableEntity(Entity e, int x, int y) {
		if (cellMap[y][x].IsEmpty()) {
			movableEntity.add(e);
			RenderableHolder.getInstance().add(e);
		}
	}

	public void update() {
		GameController.getPlayer().update();
		for (Iterator<Entity> itr2 = movableEntity.iterator(); itr2.hasNext();) {
			Entity e2 = itr2.next();
			((Updatable) e2).update();
			for (Iterator<CollidableEntity> itr1 = collidableEntity.iterator(); itr1.hasNext();) {
				CollidableEntity e1 = itr1.next();
				if (e1.isCollide(e2)) {
					e1.interact(e2);
					if (e1.isDestroyed()) {
						itr1.remove();
						cellMap[e1.getY()][e1.getX()].setIsEmpty(true);
						cellMap[e1.getY()][e1.getX()].setEntity(null);
					}
					if (e2.isDestroyed()) {
						try {
							itr2.remove();
							if (e2 instanceof Bomb) {
								bombExplosion(e2);
								RenderableHolder.timerSound.stop();
								RenderableHolder.explosionSound.play();
								GameController.setTimerPlay(false);
							}
						}catch(IllegalStateException e) {
							
						}
					}
				}
			}
		}
	}

	public boolean isMovable(int x, int y, Entity e) {
		if (x < 0 || x >= width || y < 0 || y >= height) {
			return false;
		}
		if (cellMap[y][x].IsEmpty()) {
			return true;
		}
		if (cellMap[y][x].getEntity() instanceof Interactable) {
			if (e instanceof Player) {
				return ((Interactable) cellMap[y][x].getEntity()).interact(e);
			} else {
				return true;
			}

		}
		return false;

	}

	public void bombExplosion(Entity e) {
		int targetX = e.getX();
		int targetY = e.getY();
		for (int i = targetY - 1; i <= targetY + 1; i++) {
			for (int j = targetX - 1; j <= targetX + 1; j++) {
				Entity targetEntity = cellMap[i][j].getEntity();
				if (targetEntity instanceof Wall) {
					((Interactable) targetEntity).interact(e);
					cellMap[targetEntity.getY()][targetEntity.getX()].setIsEmpty(true);
					cellMap[targetEntity.getY()][targetEntity.getX()].setEntity(null);
				}
			}
		}
	}

	public void shooting(int x, int y, String dir) {
		int targetX = x, targetY = y;
		switch (dir) {
		case "W":
			targetX = x;
			targetY = y - 1;
			break;
		case "A":
			targetX = x - 1;
			targetY = y;
			break;
		case "S":
			targetX = x;
			targetY = y + 1;
			break;
		case "D":
			targetX = x + 1;
			targetY = y;
			break;
		default:
			break;
		}
		if (cellMap[targetY][targetX].IsEmpty()) {
			try {
				if (GameController.getBulletCount() == 0 && GameController.isSimpleBullet()) {
					RenderableHolder.outOfWeaponSound.play();
					throw new OutOfWeaponException("Bullet");
				} else if (GameController.getPenetratedCount() == 0 && !GameController.isSimpleBullet()) {
					RenderableHolder.outOfWeaponSound.play();
					throw new OutOfWeaponException("PenetratedBullet");
				} else if (GameController.isSimpleBullet()) {
					Bullet bullet = new Bullet(targetX, targetY);
					bullet.setDir(dir);
					addMovableEntity(bullet, targetX, targetY);
					GameController.setBulletCount(GameController.getBulletCount() - 1);
					RenderableHolder.bulletSound.play();
				} else if (!GameController.isSimpleBullet()) {
					PenetratedBullet penetBullet = new PenetratedBullet(targetX, targetY);
					penetBullet.setDir(dir);
					addMovableEntity(penetBullet, targetX, targetY);
					GameController.setPenetratedCount(GameController.getPenetratedCount() - 1);
					RenderableHolder.penetBulletSound.play();
				}
			} catch (OutOfWeaponException e) {
			}
		}
	}

	public void planting(int x, int y, String dir) {
		int targetX = x, targetY = y;
		switch (dir) {
		case "W":
			targetX = x;
			targetY = y - 1;
			break;
		case "A":
			targetX = x - 1;
			targetY = y;
			break;
		case "S":
			targetX = x;
			targetY = y + 1;
			break;
		case "D":
			targetX = x + 1;
			targetY = y;
			break;
		default:
			break;
		}
		if (cellMap[targetY][targetX].IsEmpty()) {
			try {
				if (GameController.getBombCount() == 0) {
					RenderableHolder.outOfWeaponSound.play();
					throw new OutOfWeaponException("Bomb");
				}
				GameController.setBombCount(GameController.getBombCount() - 1);
				addMovableEntity(new Bomb(targetX, targetY), targetX, targetY);
			} catch (OutOfWeaponException e) {
			}

		}
	}

	public void exitGate() {
		GameController.setWin(true);
	}

	public Cell[][] getCellMap() {
		return cellMap;
	}

	public void setCellMap(Cell[][] cellMap) {
		this.cellMap = cellMap;
	}

}
