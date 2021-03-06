package entity;

import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import sharedObject.RenderableHolder;

public class SteelWall extends CollidableEntity {
	public SteelWall(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 8;
		this.radius = 18;
		this.centerX = x * 36 + 18;
		this.centerY = y * 36 + 18;
		isVisible = true;
		isDestroy = false;
	}

	@Override
	public boolean interact(Entity e) {
		if (e instanceof WeaponEntity) {
			e.setDestroy(true);
			e.setVisible(false);
			if(e instanceof Bullet) {
				RenderableHolder.bulletHittingSound.play();
			}
			if(e instanceof PenetratedBullet) {
				RenderableHolder.penetBulletHittingSound.play();
			}
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
