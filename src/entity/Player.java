package entity;

import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import sharedObject.RenderableHolder;

public class Player extends Entity {
	private int bullets;
	private int penetratedBullets;
	private int bombs;

	public Player(int x, int y) {
		bullets = 10;
		penetratedBullets = 0;
		bombs = 0;
		this.x = x;
		this.y = y;
		z = 20;
	}

	@Override
	public int getIndex() {
		return -1;
	}

	@Override
	public int getZ() {
		return 50;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public boolean isDestroyed() {
		return false;
	}

	@Override
	public void draw(GraphicsContext gc) {
		WritableImage tankImage = new WritableImage(RenderableHolder.tankSprite.getPixelReader(), 0, 0, 48, 48);
		gc.drawImage(tankImage, x * 48, y * 48);
	}

}
