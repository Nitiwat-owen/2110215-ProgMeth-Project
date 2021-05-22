package logic;

import application.Main;
import entity.*;
import input.InputUtility;
import javafx.scene.input.KeyCode;
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

	private static boolean isTimerPlay;

	public static void InitializeMap(String[][] map, int x, int y) {
		gameMap = new GameMap(map);
		isPlanted = false;
		player = new Player(x, y);

		// gameMap.addEntity(player, x, y);
		RenderableHolder.getInstance().add(player);

		isWin = false;
		bullet_count = 10;
		penetrated_count = 10;
		bomb_count = 0;
		isSimpleBullet = true;
		isTimerPlay = false;
	}

	public static boolean isSimpleBullet() {
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

	public static int getBulletCount() {
		return bullet_count;
	}

	public static void setBulletCount(int bullet_count) {
		GameController.bullet_count = bullet_count;
	}

	public static int getPenetratedCount() {
		return penetrated_count;
	}

	public static void setPenetratedCount(int penetrated_count) {
		GameController.penetrated_count = penetrated_count;
	}

	public static int getBombCount() {
		return bomb_count;
	}

	public static void setBombCount(int bomb_count) {
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
		gameMap.update();
		KeyCode code = InputUtility.getCode();
		switch (code) {
		case SPACE:
			shoot();
			InputUtility.setCode(KeyCode.UNDEFINED);
			break;
		case Q:
			setSimpleBullet(!GameController.isSimpleBullet());
			InputUtility.setCode(KeyCode.UNDEFINED);
			break;
		case B:
			plantedBomb();
			InputUtility.setCode(KeyCode.UNDEFINED);
			break;
		case P :
			Main.animation.stop();
			Main.creatingMenuPane();
			InputUtility.setCode(KeyCode.UNDEFINED);
			break;
		default:
			break;
		}
	}

	public static void shoot() {
		String currentDir = player.getDir();
		int x = player.getX();
		int y = player.getY();
		gameMap.shooting(x, y, currentDir);
	}

	public static void plantedBomb() {
		String currentDir = player.getDir();
		int x = player.getX();
		int y = player.getY();
		GameController.getGameMap().planting(x, y, currentDir);
	}

	public static boolean isTimerPlay() {
		return isTimerPlay;
	}

	public static void setTimerPlay(boolean isTimerPlay) {
		GameController.isTimerPlay = isTimerPlay;
	}

}
