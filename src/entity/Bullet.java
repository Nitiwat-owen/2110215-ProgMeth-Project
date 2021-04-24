package entity;

import entity.base.*;

public class Bullet extends Entity implements Interactable {
	private final double speed = 5.0;
	private int damage;

	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		this.damage = 20;
	}

	public boolean interact(Entity e) {
		if (e instanceof Wall) {
			((Wall) e).setHealth(((Wall) e).getHealth() - this.damage);
			//remove
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
