package entity;

import entity.base.*;

public class PenetratedBullet extends Entity {
	private final double speed = 2.5;
	private int damage;

	public PenetratedBullet(int x, int y) {
		this.x = x;
		this.y = y;
		this.damage = 50;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDamage() {
		return this.damage;
	}
}
