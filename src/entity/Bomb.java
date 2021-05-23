package entity;

import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.GameController;
import sharedObject.RenderableHolder;

public class Bomb extends WeaponEntity implements Updatable {

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 6;
		this.centerX = x * 36 + 18;
		this.centerY = y * 36 + 18;
		this.radius = 15;
		damage = 100;
		this.isVisible = true;
		this.isDestroy = false;
	}

	@Override
	public int getIndex() {
		return -1;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setLineWidth(2);
		gc.setStroke(Color.BLACK);
		gc.setFill(Color.BLACK);

		gc.strokeOval(centerX - radius, centerY - radius, radius, radius);
		gc.fillOval(centerX - radius, centerY - radius, radius, radius);
		if (!GameController.isTimerPlay() && isVisible) {
			RenderableHolder.timerSound.play();
			GameController.setTimerPlay(true);
		}
	}

	@Override
	public void update() {
		radius += 0.1;
	}
}
