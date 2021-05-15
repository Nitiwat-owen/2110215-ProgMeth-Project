package entity.base;

import entity.Bomb;
import entity.Bullet;
import entity.PenetratedBullet;
import sharedObject.*;
public abstract class Entity implements IRenderable {
	public abstract int getIndex();
	protected int x, y, z;
	protected boolean visible, destroy;
	protected int health;
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
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
	public boolean isDestroyed() {
		return destroy;
	}
	public boolean isVisible() {
		return visible;
	}
}
