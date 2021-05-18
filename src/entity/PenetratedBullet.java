package entity;

import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PenetratedBullet extends Entity {
	private final double speedX, speedY;
	private int damage;

	public PenetratedBullet(int x, int y) {
		this.x = x;
		this.y = y;
		this.damage = 50;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDamage() {
		return this.damage;
	}

	@Override
	public int getIndex() {
		return -1;
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
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillOval(x + 24, y + 24, 12, 12);
	}

	public void update() {
		if (visible) {
			this.x += speedX;
			this.y += speedY;
		}
	}
}
