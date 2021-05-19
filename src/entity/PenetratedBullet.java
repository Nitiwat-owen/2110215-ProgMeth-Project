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
		this.speed = 144;
	}

	@Override
	public int getIndex() {
		return -1;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillOval(x + 24, y + 24, 12, 12);
	}

	@Override
	public void update() {
		if (visible) {

		}
	}
}
