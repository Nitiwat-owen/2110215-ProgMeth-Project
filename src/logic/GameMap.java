package logic;

import java.util.ArrayList;
import entity.base.*;
import entity.*;
import item.*;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class GameMap {

	private int width;
	private int height;
	private Cell[][] cellMap;
	private ArrayList<CollidableEntity> collidableEntity;
	private ArrayList<WeaponEntity> weaponEntity;

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

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				cellMap[j][i] = new Cell(j, i);
				RenderableHolder.getInstance().add(cellMap[j][i]);
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

	public boolean addCollidableEntity(CollidableEntity e, int x, int y) {
		collidableEntity.add(e);
		RenderableHolder.getInstance().add(e);
		boolean b = cellMap[y][x].setEntity(e);
		return b;
	}

	public boolean addWeaponEntity(WeaponEntity e, int x, int y) {
		weaponEntity.add(e);
		RenderableHolder.getInstance().add(e);
		boolean b = cellMap[y][x].setEntity(e);
		return b;
	}

//	public void removeEntity(int x, int y) {
//		allEntity.remove(cellMap[y][x].getEntity());
//		cellMap[y][x].removeEntity();
//	}

	public void update() {
		GameController.getPlayer().update();
		for (CollidableEntity e1 : collidableEntity) {
			for (WeaponEntity e2 : weaponEntity) {
				if (e1.isCollide(e2)) {
					e1.interact(e2);
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
			return ((Interactable) cellMap[y][x].getEntity()).interact(e);
		}
		return false;
	}

	public boolean bombExplosion(Entity e) {
		if (e instanceof Bomb) {
			System.out.println("IsBomb");
			int targetX = e.getX();
			int targetY = e.getY();
			for (int i = targetY - 1; i <= targetY + 1; i++) {
				for (int j = targetX - 1; j <= targetX + 1; j++) {
					Entity targetEntity = cellMap[i][j].getEntity();
					if (targetEntity instanceof Interactable) {
						((Interactable) targetEntity).interact(e);
						System.out.println("BOMB!!");
					}
				}
			}
			return true;
		}
		return false;
	}

	public void shooting(int x, int y, String dir, GraphicsContext gc) {
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
			if (GameController.isSimpleBullet() && GameController.getBulletCount() > 0) {
				Bullet bullet = new Bullet(targetX, targetY);
				bullet.setDir(dir);
				RenderableHolder.getInstance().add(bullet);
				GameController.setBulletCount(GameController.getBulletCount() - 1);
				System.out.println("GameMap shooting bullet");
			} else if (!GameController.isSimpleBullet() && GameController.getPenetratedCount() > 0) {
				PenetratedBullet penetBullet = new PenetratedBullet(targetX, targetY);
				penetBullet.setDir(dir);
				RenderableHolder.getInstance().add(penetBullet);
				GameController.setPenetratedCount(GameController.getPenetratedCount() - 1);
				System.out.println("GameMap shooting penetratedBullet");
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
		if (cellMap[targetY][targetX].IsEmpty() && GameController.getBombCount() > 0) {
			RenderableHolder.getInstance().add(new Bomb(targetX, targetY));
			GameController.setBombCount(GameController.getBombCount() - 1);
			System.out.println("GameMap planting Bomb");
		}
	}

	public ArrayList<Entity> getAllEntity() {
		return this.allEntity;
	}

	public void exitGate() {
		int playerX = GameController.getPlayer().getX();
		int playerY = GameController.getPlayer().getY();
		if (playerX == 14 && playerY == 2) {
			GameController.setWin(true);
		}
	}
}
