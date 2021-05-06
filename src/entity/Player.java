package entity;

import entity.base.*;

public class Player extends Entity {
	private int bullets;
	private int penetratedBullets;
	private int bombs;
	
	public Player() {
		bullets = 10;
		penetratedBullets = 0;
		bombs = 0;
	}
}
