package entity;

import entity.base.*;

public class Wall extends Entity implements Interactable, Destroyable {

	public Wall(int x, int y) {
		this.x = x;
		this.y = y;
		this.health = 100;
		visible = true;
		destroy = false;
	}

	public void setHealth(int health) {
		if (health > 0) {
			this.health = health;
		} else {
			this.health = 0;
		}
	}

	public int getHealth() {
		return this.health;
	}

	@Override
	public boolean interact(Entity e) {
		if (e instanceof Weapon) {
			setHealth(getHealth()-((Weapon)e).getDamage());
			this.Destroyable(e);
			return true;
		}
		return false;
	}

	@Override
	public boolean Destroyable(Entity e) {
		if (health <= 0) {
			destroy = true;
			// remove
		}
		return false;
	}
}
