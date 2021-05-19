package entity;

import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Cell;
import logic.GameController;

public class Bullet extends Weapon {

	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		this.damage = 20;
		this.speed = 96;
	}

	@Override
	public int getIndex() {
		return -1;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillOval(x + 24, y + 24, 12, 12);
	}

	@Override
	public void update() {
		if (visible) {
		}
	}
}
