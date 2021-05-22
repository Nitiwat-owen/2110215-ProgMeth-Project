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

	public void drawBulletCount(GraphicsContext gc) {
		gc.setLineWidth(2);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, 135, 60);
		if (GameController.isSimpleBullet()) {
			gc.setFill(Color.YELLOW);
			gc.setStroke(Color.RED);
		} else {
			gc.setFill(Color.AQUA);
			gc.setStroke(Color.BLUE);
		}
		gc.setFont(Font.font("VERDANA", 40));
		int bulletCount = logic.GameController.getBulletCount();
		gc.fillText("BULLET : " + Integer.toString(bulletCount), 0, 50, 135);
		gc.strokeText("BULLET : " + Integer.toString(bulletCount), 0, 50, 135);
	}

	public void drawPenetBulletCount(GraphicsContext gc) {
		gc.setLineWidth(2);
		gc.setFill(Color.BLACK);
		gc.fillRect(135, 0, 270, 60);
		if (!GameController.isSimpleBullet()) {
			gc.setFill(Color.YELLOW);
			gc.setStroke(Color.RED);
		} else {
			gc.setFill(Color.AQUA);
			gc.setStroke(Color.BLUE);
		}
		gc.setFont(Font.font("VERDANA", 40));
		int penetBulletCount = logic.GameController.getPenetratedCount();
		gc.fillText("PENETRATEDBULLET : " + Integer.toString(penetBulletCount), 135, 50, 270);
		gc.strokeText("PENETRATEDBULLET : " + Integer.toString(penetBulletCount), 135, 50, 270);
	}

	public void drawBombCount(GraphicsContext gc) {
		gc.setLineWidth(2);
		gc.setFill(Color.BLACK);
		gc.fillRect(405, 0, 135, 60);

		gc.setFill(Color.AQUA);
		gc.setStroke(Color.BLUE);
		gc.setFont(Font.font("VERDANA", 40));
		int bombCount = logic.GameController.getBombCount();
		gc.fillText("BOMB : " + Integer.toString(bombCount), 405, 50, 135);
		gc.strokeText("BOMB : " + Integer.toString(bombCount), 405, 50, 135);
	}

	public void drawMap(GraphicsContext gc) {
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
			}
		}
	}
}
