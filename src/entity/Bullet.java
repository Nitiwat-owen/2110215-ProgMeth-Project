package entity;

import entity.base.*;
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

public class Bullet extends WeaponEntity implements Updatable{

	private ImageView imageView;

	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		this.damage = 20;
		this.speed = 36;
		this.visible = true;
		this.destroy = false;
		this.z = 5;
		imageView = new ImageView(new Image("bullet.png"));
	}

	@Override
	public int getIndex() {
		return -1;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setLineWidth(5);
		gc.setFill(Color.BLACK);
		gc.fillOval(x * 36 + 12, y * 36 + 9, 12, 12);
		Thread t = new Thread(() -> {
			while (visible) {
				try {
					Thread.sleep(100);
					int x = this.getX();
					int y = this.getY();
					move(dir);
//					if (GameController.getGameMap().getCellMap()[y][x].getEntity() instanceof Wall) {
//						Platform.runLater(new Runnable() {
//							@Override
//							public void run() {
////								WritableImage croppedImage = new WritableImage(
////										RenderableHolder.mapSprite.getPixelReader(), 5 * 36, 0, 36, 36);
////								gc.drawImage(croppedImage, x * 36, y * 36);
//								gc.setLineWidth(5);
//								gc.setFill(Color.BLACK);
//								gc.fillOval(x * 36 + 12, y * 36 + 9, 12, 12);
//							}
//						});
//					} else {
//						Platform.runLater(new Runnable() {
//							@Override
//							public void run() {
//								WritableImage croppedImage = new WritableImage(
//										RenderableHolder.mapSprite.getPixelReader(), 3 * 36, 0, 36, 36);
//								gc.drawImage(croppedImage, x * 36, y * 36);
//								gc.setLineWidth(5);
//								gc.setFill(Color.BLACK);
//								gc.fillOval(x * 36 + 12, y * 36 + 9, 12, 12);
//
//							}
//						});
//					}
//					Platform.runLater(new Runnable() {
//						@Override
//						public void run() {
//							gc.setLineWidth(5);
//							gc.setFill(Color.BLACK);
//							gc.fillOval(x * 36 + 12, y * 36 + 9, 12, 12);
//							try {
//								Thread.sleep(100);
//								Platform.runLater(new Runnable() {
//									@Override
//									public void run() {
//										WritableImage croppedImage = new WritableImage(
//												RenderableHolder.mapSprite.getPixelReader(), 3 * 36, 0, 36, 36);
//										gc.drawImage(croppedImage, x * 36, y * 36);
//									}
//								});
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//
//						}
//					});
					Thread drawBullet = new Thread(() -> {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								gc.setLineWidth(5);
								gc.setFill(Color.BLACK);
								gc.fillOval(x * 36 + 12, y * 36 + 9, 12, 12);
							}
						});
					});

					Thread AddBackground = new Thread(() -> {
						try {
							drawBullet.join();
							Thread.sleep(100);
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

//				Thread drawBullet = new Thread(() -> {
//					Platform.runLater(new Runnable() {
//						@Override
//						public void run() {
//							gc.setLineWidth(5);
//							gc.setFill(Color.BLACK);
//							gc.fillOval(x * 36 + 12, y * 36 + 9, 12, 12);
//						}
//					});
//				});
//
//				Thread AddBackground = new Thread(() -> {
//					try {
//						drawBullet.join();
//						Thread.sleep(100);
//						Platform.runLater(new Runnable() {
//							@Override
//							public void run() {
//								WritableImage croppedImage = new WritableImage(
//										RenderableHolder.mapSprite.getPixelReader(), 3 * 36, 0, 36, 36);
//								gc.drawImage(croppedImage, x * 36, y * 36);
//							}
//						});
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				});
//
//				drawBullet.start();
//				AddBackground.start();
			}

		});
//			Platform.runLater(new Runnable() {
//				@Override
//				public void run() {
//					while (visible) {
//						try {
////							WritableImage croppedImage = new WritableImage(RenderableHolder.mapSprite.getPixelReader(),
////									3 * 36, 0, 36, 36);
////							gc.drawImage(croppedImage, x * 36, y * 36);
//							Thread.sleep(100);
//							move(dir);
//							gc.setLineWidth(5);
//							gc.setFill(Color.BLACK);
//							gc.fillOval(x * 36 + 18, y * 36 + 18, 12, 12);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
////					WritableImage croppedImage = new WritableImage(RenderableHolder.mapSprite.getPixelReader(), 3 * 36, 0, 36,
////							36);
////					gc.drawImage(croppedImage, x * 36, y * 36);
//				}
//			});
//		});

		t.start();
	}
	
	@Override
	public void update() {
		
	}
	@Override
	public void update(GraphicsContext gc) {
//		Thread t = new Thread(() -> {
//			while (visible) {
//				try {
//					Thread.sleep(1000);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				move(dir);
				// draw(gc);
				System.out.println(visible);
			}
		});
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//
//			}
//		});

	}
}
