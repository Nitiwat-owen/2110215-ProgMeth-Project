package entity;

import entity.base.*;
import input.InputUtility;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import logic.Cell;
import logic.GameController;
import sharedObject.RenderableHolder;
import application.GameScreen;
import application.Main;

public class Bullet extends WeaponEntity implements Updatable {

	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 5;
		this.radius = 10.0;
		this.centerX = x * 36 + 18;
		this.centerY = y * 36 + 18;
		this.damage = 20;
		this.speed = 5.0;
		this.visible = true;
		this.destroy = false;

	}

	@Override
	public int getIndex() {
		return -1;
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (!destroy) {
			gc.setLineWidth(1);
			gc.setStroke(Color.BLACK);
			gc.setFill(Color.GRAY);
			gc.fillOval(centerX - radius, centerY - radius, radius, radius);
			gc.strokeOval(centerX - radius, centerY - radius, radius, radius);
		}
	}

	@Override
	public void update() {
		this.move(dir);
	}
}
