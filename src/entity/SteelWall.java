package entity;

import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import sharedObject.RenderableHolder;

public class SteelWall extends Entity {
	public SteelWall(int x, int y) {
		this.x = x;
		this.y = y;
		visible = true;
		destroy = false;
	}

	@Override
	public int getZ() {
		return 8;
	}

	@Override
	public int getIndex() {
		return 4;
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
		WritableImage croppedImage = new WritableImage(RenderableHolder.mapSprite.getPixelReader(),
				this.getIndex() * 48, 0, 48, 48);
		gc.drawImage(croppedImage, x * 48, y * 48);
	}
}
