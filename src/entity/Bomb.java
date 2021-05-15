package entity;

import entity.base.*;

public class Bomb extends Entity {
	
	private int damage;
	
	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
		damage = 100;
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
