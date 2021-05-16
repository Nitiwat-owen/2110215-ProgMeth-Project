package logic;

import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Cell implements IRenderable {
	private Entity entity;
	private boolean isEmpty;
	private int x, y;

	public Cell(int x, int y) {
		isEmpty = true;
		entity = null;
		this.x = x;
		this.y = y;
	}

	public boolean IsEmpty() {
		return isEmpty;
	}

	public boolean setEntity(Entity e) {
		if (isEmpty) {
			isEmpty = false;
			entity = e;
			return true;
		} else {
			if (e instanceof Weapon) {
				Interactable e1 = (Interactable) entity;
				return e1.interact(e);
			} else {
				return false;
			}
		}
	}

	public Entity getEntity() {
		return entity;
	}

	public void removeEntity() {
		entity = null;
		isEmpty = true;
	}

	public int getIndex() {
		return 3;
	}

	@Override
	public boolean isDestroyed() {
		return false;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getZ() {
		return -999;
	}

	@Override
	public void draw(GraphicsContext gc) {
		for (int x = 0; x <= GameController.getGameMap().getWidth(); x++) {
			for (int y = 0; y <= GameController.getGameMap().getHeight(); y++) {
				WritableImage croppedImage = new WritableImage(RenderableHolder.mapSprite.getPixelReader(),
						this.getIndex() * 48, 0, 48, 48);
				gc.drawImage(croppedImage, x * 48, y * 48);
			}
		}
	}
}
