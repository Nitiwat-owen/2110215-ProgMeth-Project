package entity;

import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Cell;
import logic.GameController;

public class PenetratedBullet extends Weapon {

	public PenetratedBullet(int x, int y) {
		this.x = x;
		this.y = y;
		this.damage = 50;
		this.speed = 108;
	}

	@Override
	public int getIndex() {
		return -1;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillOval(x + 18, y + 18, 12, 12);
	}

	@Override
	public void update() {
		if (visible) {

		}
	}
}
