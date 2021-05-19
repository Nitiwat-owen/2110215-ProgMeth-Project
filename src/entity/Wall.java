package entity;

import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import sharedObject.RenderableHolder;

public class Wall extends Entity implements Interactable, Destroyable {

	public Wall(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 5;
		this.health = 100;
		visible = true;
		destroy = false;
	}

	@Override
	public boolean interact(Entity e) {
		if (e instanceof Weapon) {
			setHealth(getHealth() - ((Weapon) e).getDamage());
			e.remove();
			this.Destroyable(e);
			return true;
		}
		return false;
	}

	@Override
	public boolean Destroyable(Entity e) {
		if (health <= 0) {
			destroy = true;
			visible = false;
			this.remove();
		}
		return false;
	}
	
	@Override
	public int getIndex() {
		return 5;
	}

	public void draw(GraphicsContext gc) {
		WritableImage croppedImage = new WritableImage(RenderableHolder.mapSprite.getPixelReader(),
				this.getIndex() * 48, 0, 48, 48);
		gc.drawImage(croppedImage, x * 48, y * 48);
	}
}
