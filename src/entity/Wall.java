package entity;

import application.Main;
import entity.base.*;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.WritableImage;
import sharedObject.RenderableHolder;

public class Wall extends Entity implements Interactable, Destroyable {
	
	private ProgressBar healthBar;
	
	public Wall(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 7;
		this.health = 100;
		visible = true;
		destroy = false;
		healthBar = new ProgressBar();
	}

	@Override
	public boolean interact(Entity e) {
		if (e instanceof Weapon) {
			setHealth(getHealth() - ((Weapon) e).getDamage());
			//e.remove();
			e.setDestroy(true);
			e.setVisible(false);
			this.Destroyable(e);
			healthBar.setVisible(true);
			System.out.println(health);
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
			Main.gameCanvas.drawMap(Main.gameGC);
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
		
		
	}
}
