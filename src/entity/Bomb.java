package entity;

import entity.base.*;
import input.InputUtility;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import logic.Cell;
import logic.GameController;
import sharedObject.RenderableHolder;

public class Bomb extends WeaponEntity implements Updatable {

	private boolean flashing;
	private int flashingCount;
	private int flashDurationCount;

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 6;
		this.centerX = x * 36 + 12;
		this.centerY = y * 36 + 12;
		this.radius = 14;
		damage = 100;
		this.visible = true;
		this.destroy = false;
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

		gc.strokeOval(centerX, centerY, radius, radius);
		gc.fillOval(centerX, centerY, radius, radius);
		if (!GameController.isExplosionPlay()) {
			RenderableHolder.explosionSound.play();
			GameController.setExplosionPlay(true);
		}
	}

	@Override
	public void update() {
		radius += 0.02;
		if (radius > 18.0) {
			visible = false;
			destroy = true;
		}
	}
}
