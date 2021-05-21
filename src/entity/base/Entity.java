package entity.base;

import application.Main;
import entity.Bomb;
import entity.Bullet;
import entity.PenetratedBullet;
import logic.*;
import sharedObject.*;
import entity.Player;
import input.InputUtility;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;

public abstract class Entity implements IRenderable {

	protected int x, y, z;
	protected double centerX, centerY;
	protected boolean visible, destroy;
	protected int health;
	protected double speed;
	protected double radius;
	protected String dir;

	public abstract int getIndex();

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

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
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

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

//	public void remove() {
//		GameController.getGameMap().removeEntity(this.x, this.y);
//	}

	@Override
	public boolean isDestroyed() {
		return destroy;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	public void move(String dir) {
		double targetx = centerX;
		double targety = centerY;

		switch (dir) {
		case "W":
			targety -= speed;
			this.setDir("W");
			break;
		case "A":
			targetx -= speed;
			this.setDir("A");
			break;
		case "S":
			targety += speed;
			this.setDir("S");
			break;
		case "D":
			targetx += speed;
			this.setDir("D");
			break;
		default:
			break;
		}
		int indexX = (int) (targetx / 36);
		int indexY = (int) (targety / 36);

		if (GameController.getGameMap().isMovable(indexX, indexY, this)) {
			this.x = indexX;
			this.y = indexY;
			this.centerX = targetx;
			this.centerY = targety;
			System.out.println(centerX +","+ centerY);
			InputUtility.setCode(KeyCode.UNDEFINED);
		}
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setDestroy(boolean destroy) {
		this.destroy = destroy;
	}

}
