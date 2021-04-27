package entity;

import entity.base.*;

public class Player extends Entity {
	private int bullets;
	private int penetratedBullets;
	private int bombs;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		bullets = 10;
		penetratedBullets = 0;
		bombs = 0;
	}
}
