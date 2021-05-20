package logic;

import java.util.ArrayList;

import entity.*;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class GameController {

	private static GameMap gameMap;

	private static boolean isPlanted;

	private static Player player;

	private static boolean isWin;

	private static int bullet_count;

	private static int penetrated_count;

	private static int bomb_count;

	private static boolean isSimpleBullet;

	private static ArrayList<Bullet> bulletField;

	public static void InitializeMap(String[][] map, int x, int y) {
		gameMap = new GameMap(map);
		isPlanted = false;
		player = new Player(x, y);

		// gameMap.addEntity(player, x, y);
		RenderableHolder.getInstance().add(player);

		isWin = false;
		bullet_count = 0;
		penetrated_count = 0;
		bomb_count = 0;
		isSimpleBullet = true;
		bulletField = new ArrayList<Bullet>();
	}

	public static boolean isBullet() {
		return isSimpleBullet;
	}

	public static void setSimpleBullet(boolean isBullet) {
		GameController.isSimpleBullet = isBullet;
	}

	public static void movePlayer(String dir) {
		player.move(dir);
	}

	public static void addBombCount() {
		GameController.bomb_count++;
	}

	public static void addBulletCount() {
		GameController.bullet_count += 5;
	}

	public static void addPenetratedCount() {
		GameController.penetrated_count += 3;
	}

	public static boolean isGameWin() {
		return isWin;
	}

	public static boolean isPlanted() {
		return isPlanted;
	}

	public static void setPlanted(boolean isPlanted) {
		GameController.isPlanted = isPlanted;
	}

	public static Player getPlayer() {
		return player;
	}

	public static void setPlayer(Player player) {
		GameController.player = player;
	}

	public static boolean isWin() {
		return isWin;
	}

	public static void setWin(boolean isWin) {
		GameController.isWin = isWin;
	}

	public static int getBullet_count() {
		return bullet_count;
	}

	public static void setBullet_count(int bullet_count) {
		GameController.bullet_count = bullet_count;
	}

	public static int getPenetrated_count() {
		return penetrated_count;
	}

	public static void setPenetrated_count(int penetrated_count) {
		GameController.penetrated_count = penetrated_count;
	}

	public static int getBomb_count() {
		return bomb_count;
	}

	public static void setBomb_count(int bomb_count) {
		GameController.bomb_count = bomb_count;
	}

	public static GameMap getGameMap() {
		return gameMap;
	}

	public static void setGameMap(GameMap gameMap) {
		GameController.gameMap = gameMap;
	}

	public static void update() {
		player.update();
	}

	public static void shoot(GraphicsContext gc) {
		String currentDir = player.getDir();
		int x = player.getX();
		int y = player.getY();
		bullet_count -= 1;
		gameMap.shooting(x, y, currentDir, gc);
	}

	public static void plantedBomb() {
		String currentDir = player.getDir();
		int x = player.getX();
		int y = player.getY();
		GameController.getGameMap().planting(x, y, currentDir);
	}

	public void addBullet(int x, int y) {
		bulletField.add(new Bullet(x, y));
	}

	public ArrayList<Bullet> getBulletField() {
		return bulletField;
	}
}
