package entity.base;

import entity.Bomb;
import entity.Bullet;
import entity.PenetratedBullet;
import logic.GameController;
import sharedObject.*;
import entity.Player;

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

	public boolean move(String dir) {
		int targetx = x;
		int targety = y;
		int angle = 0;

		switch (dir) {
		case "W":
			targety -= 1;
			angle = 0;
			break;
		case "A":
			targetx -= 1;
			angle = 270;
			break;
		case "S":
			targety += 1;
			angle = 180;
			break;
		case "D":
			targetx += 1;
			angle = 90;
			break;
		default:
			break;
		}
		if (GameController.getGameMap().isMovable(targetx, targety, this)) {
//			this.destroy = true;
//			RenderableHolder.getInstance().update();
//			RenderableHolder.getInstance().add(this);
			((Player) this).setAngle(angle);
			this.x = targetx;
			this.y = targety;
			return true;
		}
		return false;
	}
}
