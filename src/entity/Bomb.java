package entity;

import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Cell;
import logic.GameController;

public class Bomb extends Weapon {

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
		damage = 100;
	}

	@Override
	public int getIndex() {
		return -1;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillOval(x + 18, y + 18, 15, 15);
	}

	@Override
	public void update() {

	}
}
