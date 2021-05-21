package entity;

import entity.base.*;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Cell;
import logic.GameController;

public class Bomb extends WeaponEntity {

	private boolean flashing;
	private int flashingCount;

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
		damage = 100;
		this.visible = true;
		this.destroy = false;
		this.z = 6;
		flashing = false;
		flashingCount = 10;
	}

	@Override
	public int getIndex() {
		return -1;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setLineWidth(4);
		gc.setFill(Color.BLACK);
		gc.fillOval(x * 36 + 7, y * 36 + 6, 25, 25);
		flashing = true;

		Thread t = new Thread(() -> {
			while (visible) {
				while (flashingCount > 0) {
					try {
						Thread.sleep(500);
						if (flashing) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									gc.setStroke(Color.BLACK);
									gc.setFill(Color.RED);

									gc.strokeOval(x * 36 + 7, y * 36 + 6, 25, 25);
									gc.fillOval(x * 36 + 7, y * 36 + 6, 25, 25);
								}
							});
							flashing = false;
						} else {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									gc.setStroke(Color.BLACK);
									gc.setFill(Color.BLACK);
									gc.strokeOval(x * 36 + 7, y * 36 + 6, 25, 25);
									gc.fillOval(x * 36 + 7, y * 36 + 6, 25, 25);
								}
							});
							flashing = true;
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					flashingCount--;
				}
				visible = false;
				destroy = true;
				GameController.getGameMap().bombExplosion(this);
			}
		});
		t.start();
	}

	@Override
	public void update(GraphicsContext gc) {

	}
}
