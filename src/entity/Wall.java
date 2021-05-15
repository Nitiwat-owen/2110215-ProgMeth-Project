package entity;

import entity.base.*;
import javafx.scene.canvas.GraphicsContext;

public class Wall extends Entity implements Interactable, Destroyable {

	public Wall(int x,int y) {
		this.x = x*48;
		this.y = y*48;
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
			e.remove();
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
	@Override
	public int getIndex() {
		return 5;
	}
	public void draw(GraphicsContext gc) {
		image
	}
}
