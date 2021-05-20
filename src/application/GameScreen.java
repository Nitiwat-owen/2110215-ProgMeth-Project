package application;

import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.*;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import javafx.scene.input.KeyEvent;

public class GameScreen extends Canvas {

	public GameScreen(int width, int height) {
		super(width, height);
	}

	public void addListener() {
		this.setOnKeyPressed((KeyEvent event) -> {
			String new_code = event.getCode().toString();
			if (!InputUtility.getPressed()) {
				InputUtility.setTriggered(new_code, true);
			}
			InputUtility.setPressed(true);
		});
		this.setOnKeyReleased((KeyEvent event) -> {
			InputUtility.setPressed(false);
		});
	}

	public void drawBulletCount(GraphicsContext gc) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gc.setLineWidth(2);
				gc.setFill(Color.BLACK);
				gc.fillRect(0, 0, 135, 60);

				gc.setFill(Color.AQUA);
				gc.setStroke(Color.BLUE);

				gc.setFont(Font.font("VERDANA", 40));
				int bulletCount = logic.GameController.getBullet_count();
				gc.fillText("BULLET : " + Integer.toString(bulletCount), 0, 50, 135);
				gc.strokeText("BULLET : " + Integer.toString(bulletCount), 0, 50, 135);
			}
		});
	}

	public void drawPenetBulletCount(GraphicsContext gc) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gc.setLineWidth(2);
				gc.setFill(Color.BLACK);
				gc.fillRect(135, 0, 270, 60);

				gc.setFill(Color.YELLOW);
				gc.setStroke(Color.RED);
				gc.setFont(Font.font("VERDANA", 40));
				int penetBulletCount = logic.GameController.getPenetrated_count();
				gc.fillText("PENETRATEDBULLET : " + Integer.toString(penetBulletCount), 135, 50, 270);
				gc.strokeText("PENETRATEDBULLET : " + Integer.toString(penetBulletCount), 135, 50, 270);
			}
		});
	}

	public void drawBombCount(GraphicsContext gc) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				gc.setLineWidth(2);
				gc.setFill(Color.BLACK);
				gc.fillRect(405, 0, 135, 60);

				gc.setFill(Color.AQUA);
				gc.setStroke(Color.BLUE);
				gc.setFont(Font.font("VERDANA", 40));
				int bombCount = logic.GameController.getBomb_count();
				gc.fillText("BOMB : " + Integer.toString(bombCount), 405, 50, 135);
				gc.strokeText("BOMB : " + Integer.toString(bombCount), 405, 50, 135);
			}
		});

	}

	public static void drawMap(GraphicsContext gc) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
					if (entity.isVisible() && !entity.isDestroyed()) {
						entity.draw(gc);
					}
				}
			}
		});
	}

	public void drawWeapon(GraphicsContext gc) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				for (IRenderable entity : RenderableHolder.getInstance().getWeapon()) {
					while (entity.isVisible() && !entity.isDestroyed()) {
						entity.draw(gc);
					}
				}
			}
		});
	}
}
