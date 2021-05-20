package entity;

import entity.base.*;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import logic.Cell;
import logic.GameController;
import sharedObject.RenderableHolder;

public class PenetratedBullet extends Weapon {

	public PenetratedBullet(int x, int y) {
		this.x = x;
		this.y = y;
		this.damage = 50;
		this.speed = 36;
		this.visible = true;
		this.destroy= false;
		this.z = 5;
	}

	@Override
	public int getIndex() {
		return -1;
	}

	@Override
	public void draw(GraphicsContext gc) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gc.setLineWidth(5);
				gc.setFill(Color.RED);
				gc.fillOval(x * 36 + 12, y * 36 + 9, 12, 12);
			}
		});
		Thread t = new Thread(() -> {
			while (visible) {
				try {
					Thread.sleep(50);
					int x = this.getX();
					int y = this.getY();
					move(dir);
					Thread drawBullet = new Thread(() -> {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								gc.setLineWidth(5);
								gc.setFill(Color.RED);
								gc.fillOval(x * 36 + 12, y * 36 + 9, 12, 12);
							}
						});
					});

					Thread AddBackground = new Thread(() -> {
						try {
							drawBullet.join();
							Thread.sleep(50);
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									WritableImage croppedImage = new WritableImage(
											RenderableHolder.mapSprite.getPixelReader(), 3 * 36, 0, 36, 36);
									gc.drawImage(croppedImage, x * 36, y * 36);
								}
							});
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});

					drawBullet.start();
					AddBackground.start();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		t.start();
	}

	@Override
	public void update(GraphicsContext gc) {
		if (visible) {
		}
	}
}
