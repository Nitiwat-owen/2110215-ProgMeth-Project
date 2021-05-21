package item;

import entity.Player;
import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import logic.GameController;
import sharedObject.RenderableHolder;

public class PenetratedBulletItem extends CollidableEntity {
	public PenetratedBulletItem(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 12;
		this.radius = 15;
		this.centerX = x + radius;
		this.centerY = y + radius;
		visible = true;
		destroy = false;
	}

	@Override
	public boolean interact(Entity e) {
		if (e instanceof Player) {
			GameController.addPenetratedCount();
		} else {
			e.setDestroy(true);
		}
		destroy = true;
		return true;
	}

	@Override
	public int getIndex() {
		return 1;
	}

	@Override
	public void draw(GraphicsContext gc) {
		WritableImage croppedImage = new WritableImage(RenderableHolder.mapSprite.getPixelReader(),
				this.getIndex() * 36, 0, 36, 36);
		gc.drawImage(croppedImage, x * 36, y * 36);
	}
}
