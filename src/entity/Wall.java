package entity;

import entity.base.*;

public class Wall extends Entity implements Destroyable {

	public Wall(int x, int y) {
		this.x = x;
		this.y = y;
		this.health = 100;
	}

	public void setHealth(int health) {
		if(health > 0) {
			this.health = health;
		}else {
			//remove
		}
	}

	public int getHealth() {
		return this.health;
	}
	
	public boolean Destroyable(Entity e) {
		
	}
}
