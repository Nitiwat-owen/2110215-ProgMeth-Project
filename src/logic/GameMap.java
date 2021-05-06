package logic;

import java.util.ArrayList;
import entity.base.*;
import entity.*;
import item.*;

public class GameMap {
	private Cell[][] cellMap;
	private int width;
	private int height;

	private ArrayList<Entity> allEntity;

	public GameMap(int column, int row) {
		allEntity = new ArrayList<Entity>();

		setWidth(column);
		setHeight(row);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				cellMap[i][j] = new Cell();
			}
		}
	}

	public GameMap(String[][] map) {
		allEntity = new ArrayList<Entity>();

		int column = this.getWidth();
		int row = this.getHeight();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				cellMap[i][j] = new Cell();
				switch (map[i][j]) {
				case "0":
					break;
				case "B":
					addEntity(new BulletItem(), j, i);
					break;
				case "BM":
					addEntity(new BombItem(), j, i);
					break;
				case "PB":
					addEntity(new PenetratedBulletItem(), j, i);
					break;
				case "SW":
					addEntity(new SteelWall(), j, i);
					break;
				case "W":
					addEntity(new Wall(), j, i);
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

	public boolean addEntity(Entity e, int x, int y) {
		allEntity.add(e);

		e.setX(x);
		e.setY(y);
		boolean b = cellMap[y][x].setEntity(e);
		return b;
	}

	public void removeEntity(int x, int y) {
		allEntity.remove(cellMap[x][y].getEntity());

		cellMap[x][y].removeEntity();
	}

	public boolean isMovable(int x, int y, Entity e) {
		if (cellMap[x][y].IsEmpty()) {
			return true;
		}
		if (x >= 0 && x < width && y >= 0 && y < height) {
			return true;
		}
		if (cellMap[x][y].getEntity() instanceof Interactable) {
			return ((Interactable) cellMap[x][y]).interact(e);
		}
		return false;
	}

	public boolean bombExplosion(Entity e) {
		if (e instanceof Bomb) {
			int targetX = e.getX();
			int targetY = e.getY();
			for (int i = targetY - 1; i <= targetY + 1; i++) {
				for (int j = targetX - 1; i <= targetX + 1; i++) {
					Entity targetEntity = cellMap[i][j].getEntity();
					if (targetEntity instanceof Interactable) {
						((Interactable) targetEntity).interact(e);
					}
				}
			}
			return true;
		}
		return false;
	}

	public ArrayList<Entity> getAllEntity() {
		return this.allEntity;
	}
}
