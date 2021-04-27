package entity.base;

import entity.Bomb;
import entity.Bullet;
import entity.PenetratedBullet;

public abstract class Entity {
	protected int x, y;
	protected boolean visible, destroy;
	protected int health;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void remove() {

	}

	public boolean isWeapon(Entity e) {
		return e.getClass() == Bullet.class || e.getClass() == Bomb.class || e.getClass() == PenetratedBullet.class;
	}
}
