package entity.base;

import application.Main;
import entity.Bomb;
import entity.Bullet;
import entity.PenetratedBullet;
import logic.*;
import sharedObject.*;
import entity.Player;
import javafx.scene.image.WritableImage;

public abstract class Entity implements IRenderable {

	protected int x, y, z;
	protected boolean visible, destroy;
	protected int health;
	protected int speed;
	protected String dir;

	public abstract int getIndex();

	public abstract boolean isCollide(Entity e);

	public void setHealth(int health) {
		if (health > 0) {
			this.health = health;
		} else {
			this.health = 0;
		}
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

	@Override
	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getHealth() {
		return this.health;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void remove() {
		GameController.getGameMap().removeEntity(this.x, this.y);
	}

	@Override
	public boolean isDestroyed() {
		return destroy;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	public boolean move(String dir) {
		int targetx = x;
		int targety = y;

		switch (dir) {
		case "W":
			targety -= speed / 36;
			this.setDir("W");
			break;
		case "A":
			targetx -= speed / 36;
			this.setDir("A");
			break;
		case "S":
			targety += speed / 36;
			this.setDir("S");
			break;
		case "D":
			targetx += speed / 36;
			this.setDir("D");
			break;
		default:
			break;
		}
		if (GameController.getGameMap().isMovable(targetx, targety, this)) {

//			this.destroy = true;
			// this.remove();
//			RenderableHolder.getInstance().update();
//			if (this instanceof Player) {
//				Player player = new Player(targetx, targety);
//				GameController.setPlayer(player);
//				GameController.getGameMap().addEntity(player, targetx, targety);
//			}
//			if (this instanceof Bullet) {
//				Bullet bullet = new Bullet(targetx, targety);
//				GameController.getGameMap().addEntity(bullet, targetx, targety);
//			}
//			if (this instanceof PenetratedBullet) {
//				PenetratedBullet penetBullet = new PenetratedBullet(targetx, targety);
//				GameController.getGameMap().addEntity(penetBullet, targetx, targety);
//			}
//			RenderableHolder.getInstance().update();
//			RenderableHolder.getInstance().add(this);

			if (targetx != x || targety != y) {
				RenderableHolder.getInstance().add(new Cell(x, y));
			}
			this.x = targetx;
			this.y = targety;
			return true;
		}
		return false;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setDestroy(boolean destroy) {
		this.destroy = destroy;
	}

}
