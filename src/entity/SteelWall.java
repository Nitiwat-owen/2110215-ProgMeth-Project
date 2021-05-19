package entity;

import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import sharedObject.RenderableHolder;

public class SteelWall extends Entity {
	public SteelWall(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 8;
		visible = true;
		destroy = false;
	}

	@Override
	public int getIndex() {
		return 4;
	}

	@Override
	public void draw(GraphicsContext gc) {
		WritableImage croppedImage = new WritableImage(RenderableHolder.mapSprite.getPixelReader(),
				this.getIndex() * 48, 0, 48, 48);
		gc.drawImage(croppedImage, x * 48, y * 48);
	}
}
