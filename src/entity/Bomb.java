package entity;

import entity.base.*;

public class Bomb extends Entity implements Interactable {

	private int damage;

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
		damage = 100;
	}

	public boolean interact(Entity e) {
		if (e instanceof Wall) {
			((Wall) e).setHealth(((Wall) e).getHealth() - this.damage);
			this.remove();
			return true;
		}
		return false;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDamage() {
		return this.damage;
	}
}
