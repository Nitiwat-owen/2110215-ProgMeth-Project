package entity;

import java.awt.Rectangle;
import application.Main;
import entity.base.*;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import sharedObject.RenderableHolder;

public class Wall extends CollidableEntity implements Interactable, Destroyable {

	private double percentage;
	private boolean barVisible;

	public Wall(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 7;
		this.radius = 18;
		this.centerX = x + radius;
		this.centerY = y + radius;
		this.health = 100;
		visible = true;
		destroy = false;
		barVisible = false;
	}

	@Override
	public void interact(Entity e) {
			setHealth(getHealth() - ((WeaponEntity) e).getDamage());
			e.setDestroy(true);
			e.setVisible(false);
			this.Destroyable(e);
			this.barVisible = true;
	}

	@Override
	public boolean Destroyable(Entity e) {
		if (health <= 0) {
			destroy = true;
			visible = false;
		}
		return false;
	}

	@Override
	public int getIndex() {
		return 5;
	}

	public void draw(GraphicsContext gc) {
		WritableImage croppedImage = new WritableImage(RenderableHolder.mapSprite.getPixelReader(),
				this.getIndex() * 36, 0, 36, 36);
		gc.drawImage(croppedImage, x * 36, y * 36);

		if (barVisible) {
			gc.setLineWidth(2);
			gc.setFill(Color.BLACK);
			gc.setStroke(Color.BLACK);
			gc.fillRect(x * 36 + 3, y * 36 + 30, 30, 6);
			gc.strokeRect(x * 36 + 3, y * 36 + 30, 30, 6);

			percentage = health / 100.0;

			if (percentage >= 0.5) {
				gc.setFill(Color.GREEN);
				gc.fillRect(x * 36 + 3, y * 36 + 30, percentage * 30, 6);
			} else {
				gc.setFill(Color.RED);
				gc.fillRect(x * 36 + 3, y * 36 + 30, percentage * 30, 6);
			}
		}

	}
}
