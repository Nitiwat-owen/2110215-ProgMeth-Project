package entity;

import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PenetratedBullet extends WeaponEntity implements Updatable {

	public PenetratedBullet(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 5;
		this.radius = 12.0;
		this.centerX = x * 36 + 18;
		this.centerY = y * 36 + 18;
		this.damage = 50;
		this.speed = 10.0;
		this.isVisible = true;
		this.isDestroy = false;

	}

	@Override
	public int getIndex() {
		return -1;
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (!isDestroy) {
			gc.setLineWidth(1);
			gc.setStroke(Color.BLACK);
			gc.setFill(Color.RED);
			gc.fillOval(centerX- radius, centerY- radius, radius, radius);
			gc.strokeOval(centerX -radius, centerY - radius, radius, radius);
		}
	}

	@Override
	public void update() {
		this.move(dir);
	}
}
