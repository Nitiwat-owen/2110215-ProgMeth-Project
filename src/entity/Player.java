package entity;

import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import sharedObject.RenderableHolder;

public class Player extends Entity {
	private int bullets;
	private int penetratedBullets;
	private int bombs;
	private int angle;

	public Player(int x, int y) {
		bullets = 10;
		penetratedBullets = 0;
		bombs = 0;
		this.x = x;
		this.y = y;
		z = 20;
		this.angle = 0;
		this.visible = true;
		this.destroy = false;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	@Override
	public int getIndex() {
		switch (angle) {
		case 0:
			return 0;
		case 90:
			return 1;
		case 180:
			return 2;
		case 270:
			return 3;
		default:
			break;
		}
		return -1;
	}

	@Override
	public int getZ() {
		return 50;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public boolean isDestroyed() {
		return destroy;
	}

	@Override
	public void draw(GraphicsContext gc) {
		WritableImage tankImage = new WritableImage(RenderableHolder.tankSprite.getPixelReader(), this.getIndex() * 48,
				0, 48, 48);
		gc.drawImage(tankImage, x * 48, y * 48);
	}

}
