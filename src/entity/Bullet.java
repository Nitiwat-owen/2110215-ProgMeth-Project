package entity;

import entity.base.*;

public class Bullet extends Weapon {
	private final double speed = 5.0;

	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		this.damage = 20;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDamage() {
		return this.damage;
	}
	@Override
	public int getIndex() {
		return -1;
	}
}
