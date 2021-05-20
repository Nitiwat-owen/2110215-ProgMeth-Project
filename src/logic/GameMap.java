package logic;

import java.util.ArrayList;
import entity.base.*;
import entity.*;
import item.*;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class GameMap {

	private Cell[][] cellMap;

	private int width;
	private int height;

	private ArrayList<Entity> allEntity;

	/*
	 * public GameMap(int column, int row) { allEntity = new ArrayList<Entity>();
	 * 
	 * setWidth(column); setHeight(row);
	 * 
	 * for (int i = 0; i < row; i++) { for (int j = 0; j < column; j++) {
	 * cellMap[i][j] = new Cell(); } } }
	 */

	public GameMap(String[][] map) {
		allEntity = new ArrayList<Entity>();

		int column = map[0].length;
		int row = map.length;

		setWidth(column);
		setHeight(row);

		cellMap = new Cell[row][column];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				cellMap[i][j] = new Cell(j, i);
				RenderableHolder.getInstance().addBackground(cellMap[i][j]);
				switch (map[i][j]) {
				case "0":
					break;
				case "B":
					addEntity(new BulletItem(j, i), j, i);
					break;
				case "BM":
					addEntity(new BombItem(j, i), j, i);
					break;
				case "PB":
					addEntity(new PenetratedBulletItem(j, i), j, i);
					break;
				case "SW":
					addEntity(new SteelWall(j, i), j, i);
					break;
				case "W":
					addEntity(new Wall(j, i), j, i);
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

	public Cell[][] getCellMap() {
		return cellMap;
	}

	public void setCellMap(Cell[][] cellMap) {
		this.cellMap = cellMap;
	}

	public boolean addEntity(Entity e, int x, int y) {
		allEntity.add(e);
		RenderableHolder.getInstance().add(e);

		boolean b = cellMap[y][x].setEntity(e);
		return b;
	}

	public boolean addBackground(Entity e, int x, int y) {
		allEntity.add(e);
		RenderableHolder.getInstance().addBackground(e);

		boolean b = cellMap[y][x].setEntity(e);
		return b;
	}

	public void removeEntity(int x, int y) {
		allEntity.remove(cellMap[y][x].getEntity());
//		System.out.println(allEntity.remove(cellMap[x][y].getEntity()));
		cellMap[y][x].removeEntity();
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
			if (GameController.isSimpleBullet()) {
				Bullet bullet = new Bullet(targetX, targetY);
				bullet.setDir(dir);
				RenderableHolder.getInstance().add(bullet);
				System.out.println("GameMap shooting bullet");
			} else {
				PenetratedBullet penetBullet = new PenetratedBullet(targetX, targetY);
				penetBullet.setDir(dir);
				RenderableHolder.getInstance().add(penetBullet);
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
		if (cellMap[targetY][targetX].IsEmpty()) {
			RenderableHolder.getInstance().add(new Bomb(targetX, targetY));
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
