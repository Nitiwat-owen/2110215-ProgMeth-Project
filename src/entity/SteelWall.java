package entity;

import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import sharedObject.RenderableHolder;

public class SteelWall extends CollidableEntity implements Interactable {
	public SteelWall(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 8;
		this.radius = 18;
		this.centerX = x + radius;
		this.centerY = y + radius;
		visible = true;
		destroy = false;
	}

	@Override
	public boolean interact(Entity e) {
		if (e instanceof Weapon && isCollide(e)) {
			e.setDestroy(true);
			e.setVisible(false);
			return true;
		}
		return false;
	}
	
	@Override
	public int getIndex() {
		return 4;
	}
	
	
	@Override
	public void draw(GraphicsContext gc) {
		WritableImage croppedImage = new WritableImage(RenderableHolder.mapSprite.getPixelReader(),
				this.getIndex() * 36, 0, 36, 36);
		gc.drawImage(croppedImage, x * 36, y * 36);
	}
}
