package item;

import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import logic.*;
import sharedObject.RenderableHolder;

public class BombItem extends CollidableEntity {
	public BombItem(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 10;
		this.radius = 15;
		this.centerX = x + radius;
		this.centerY = y + radius;
		visible = true;
		destroy = false;
	}

	public void interact(Entity e) {
		this.remove();
		GameController.addBombCount();
		destroy = true;
		e.setDestroy(true);
	}

	@Override
	public int getIndex() {
		return 2;
	}

	@Override
	public void draw(GraphicsContext gc) {
		WritableImage croppedImage = new WritableImage(RenderableHolder.mapSprite.getPixelReader(),
				this.getIndex() * 36, 0, 36, 36);
		gc.drawImage(croppedImage, x * 36, y * 36);
	}
}
