package item;

import entity.Player;
import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import logic.GameController;
import sharedObject.RenderableHolder;

public class BulletItem extends CollidableEntity {
	public BulletItem(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 11;
		this.radius = 15;
		this.centerX = x + radius;
		this.centerY = y + radius;
		isVisible = true;
		isDestroy = false;
	}

	@Override
	public boolean interact(Entity e) {
		if (isVisible) {
			if (e instanceof Player) {
				GameController.addBulletCount();
				isVisible = false;
				isDestroy = true;
				GameController.getGameMap().getCellMap()[y][x].setIsEmpty(true);
				GameController.getGameMap().getCellMap()[y][x].setEntity(null);
			} else {
				e.setDestroy(true);
			}

		}
		return true;
	}

	@Override
	public int getIndex() {
		return 0;
	}

	@Override
	public void draw(GraphicsContext gc) {
		WritableImage croppedImage = new WritableImage(RenderableHolder.mapSprite.getPixelReader(),
				this.getIndex() * 36, 0, 36, 36);
		gc.drawImage(croppedImage, x * 36, y * 36);
	}
}
