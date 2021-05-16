package entity;

import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import sharedObject.RenderableHolder;

public class Wall extends Entity implements Interactable, Destroyable {

	public Wall(int x, int y) {
		this.x = x;
		this.y = y;
		this.health = 100;
		visible = true;
		destroy = false;
	}

	public void setHealth(int health) {
		if (health > 0) {
			this.health = health;
		} else {
			this.health = 0;
		}
	}

	public int getHealth() {
		return this.health;
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
	public int getZ() {
		return 5;
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
	public int getIndex() {
		return 5;
	}

	public void draw(GraphicsContext gc) {
		WritableImage croppedImage = new WritableImage(RenderableHolder.mapSprite.getPixelReader(),
				this.getIndex() * 48, 0, 48, 48);
		gc.drawImage(croppedImage, x * 48, y * 48);
	}
}
