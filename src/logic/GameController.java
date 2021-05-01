package logic;

import entity.*;
public class GameController {
	private static boolean isPlanted;
	private static Player player;
	private static boolean isWin;
	private static int bullet_count;
	private static int penetrated_count;
	private static int bomb_count;
	public static void addBombCount() {
		GameController.bomb_count++;
	}
	
	public static void addBulletCount(){
		GameController.bullet_count += 5;
	}
	
	public static void addPenetratedCount() {
		GameController.penetrated_count += 3;
	}
	
	public static boolean isGameWin(){
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
	
}
