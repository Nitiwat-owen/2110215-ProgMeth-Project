package item;

import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import logic.GameController;
import sharedObject.RenderableHolder;

public class BulletItem extends Entity implements Interactable {
	public BulletItem(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 11;
		visible = true;
		destroy = false;
	}

	public boolean interact(Entity e) {
		this.remove();
		GameController.addBulletCount();
		visible = false;
		destroy = true;
		return true;
	}

	@Override
	public int getIndex() {
		return 0;
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
