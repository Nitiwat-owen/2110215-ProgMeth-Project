package entity;

import entity.base.*;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Cell;
import logic.GameController;

public class Bomb extends WeaponEntity implements Updatable {

	private boolean flashing;
	private int flashingCount;

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 6;
		this.centerX = x * 36 + 12;
		this.centerY = y * 36 + 12;
		this.radius = 15;
		damage = 100;
		this.visible = true;
		this.destroy = false;

		flashing = false;
		flashingCount = 10000;
	}

	@Override
	public int getIndex() {
		return -1;
	}

	@Override
	public void draw(GraphicsContext gc) {
		while (flashingCount > 0) {
			if (flashing) {
				gc.setLineWidth(2);
				gc.setStroke(Color.BLACK);
				gc.setFill(Color.RED);

				gc.strokeOval(centerX, centerY, radius, radius);
				gc.fillOval(centerX, centerY, radius, radius);
				flashing = false;
				System.out.println("flash");
			} else {
				gc.setLineWidth(2);
				gc.setStroke(Color.BLACK);
				gc.setFill(Color.BLACK);

				gc.strokeOval(centerX, centerY, radius, radius);
				gc.fillOval(centerX, centerY, radius, radius);
				flashing = true;
				System.out.println("not flash");
			}
			update();
		}
		visible = false;
		destroy = true;
		System.out.println("BOMB!!!");

//		gc.setLineWidth(2);
//		gc.setStroke(Color.BLACK);
//		gc.setFill(Color.RED);
//
//		gc.strokeOval(centerX, centerY, radius, radius);
//		gc.fillOval(centerX, centerY, radius, radius);
//		flashing = true;
//
//		Thread t = new Thread(() -> {
//			while (visible) {
//				while (flashingCount > 0) {
//					try {
//						Thread.sleep(500);
//						if (flashing) {
//							Platform.runLater(new Runnable() {
//								@Override
//								public void run() {
//									gc.setLineWidth(2);
//									gc.setStroke(Color.BLACK);
//									gc.setFill(Color.RED);
//					
//									gc.strokeOval(centerX, centerY, radius, radius);
//									gc.fillOval(centerX, centerY, radius, radius);
//								}
//							});
//							flashing = false;
//						} else {
//							Platform.runLater(new Runnable() {
//								@Override
//								public void run() {
//									gc.setLineWidth(2);
//									gc.setStroke(Color.BLACK);
//									gc.setFill(Color.BLACK);
//					
//									gc.strokeOval(centerX, centerY, radius, radius);
//									gc.fillOval(centerX, centerY, radius, radius);
//								}
//							});
//							flashing = true;
//						}
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					flashingCount--;
//				}
//				visible = false;
//				destroy = true;
//				System.out.println("BOMB");
//				GameController.getGameMap().bombExplosion(this);
//			}
//		});
//		t.start();

	}

	@Override
	public void update() {
		flashingCount--;
	}
}
