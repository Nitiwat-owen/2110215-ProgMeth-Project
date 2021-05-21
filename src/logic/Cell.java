package logic;

import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Cell extends Entity {
	private Entity entity;
	private boolean isEmpty;
	private int x, y;

	public Cell(int x, int y) {
		entity = null;
		isEmpty = true;
		this.x = x;
		this.y = y;
		this.z = -999;
		this.visible = true;
		this.destroy = false;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity e) {
		if(IsEmpty()) {
			this.entity = entity;
		}
	}

	public boolean IsEmpty() {
		return isEmpty;
	}

	public void setIsEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public int getIndex() {
		return 3;
	}

	@Override
	public void draw(GraphicsContext gc) {
//		for (int x = 0; x <= GameController.getGameMap().getWidth(); x++) {
//			for (int y = 0; y <= GameController.getGameMap().getHeight(); y++) {
		WritableImage croppedImage = new WritableImage(RenderableHolder.mapSprite.getPixelReader(),
				this.getIndex() * 36, 0, 36, 36);
		gc.drawImage(croppedImage, x * 36, y * 36);
//			}
//		}
	}
}
