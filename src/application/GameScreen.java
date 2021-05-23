package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.*;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class GameScreen extends Canvas {

	public GameScreen(int width, int height) {
		super(width, height);
	}

	public void drawTopPane(GraphicsContext gc) {
		gc.setLineWidth(2);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());

		int bulletCount = logic.GameController.getBulletCount();
		int penetBulletCount = logic.GameController.getPenetratedCount();
		int bombCount = logic.GameController.getBombCount();
		gc.setFont(Font.font("VERDANA", 40));
		if (GameController.isSimpleBullet()) {
			gc.setFill(Color.YELLOW);
			gc.setStroke(Color.RED);
			gc.fillText("BULLET : " + Integer.toString(bulletCount), 0, 50, 135);
			gc.strokeText("BULLET : " + Integer.toString(bulletCount), 0, 50, 135);
			gc.setFill(Color.AQUA);
			gc.setStroke(Color.BLUE);
			gc.fillText("PENETRATEDBULLET : " + Integer.toString(penetBulletCount), 135, 50, 270);
			gc.strokeText("PENETRATEDBULLET : " + Integer.toString(penetBulletCount), 135, 50, 270);
			gc.fillText("BOMB : " + Integer.toString(bombCount), 405, 50, 135);
			gc.strokeText("BOMB : " + Integer.toString(bombCount), 405, 50, 135);
		} else {
			gc.setFill(Color.AQUA);
			gc.setStroke(Color.BLUE);
			gc.fillText("BULLET : " + Integer.toString(bulletCount), 0, 50, 135);
			gc.strokeText("BULLET : " + Integer.toString(bulletCount), 0, 50, 135);
			gc.fillText("BOMB : " + Integer.toString(bombCount), 405, 50, 135);
			gc.strokeText("BOMB : " + Integer.toString(bombCount), 405, 50, 135);
			gc.setFill(Color.YELLOW);
			gc.setStroke(Color.RED);
			gc.fillText("PENETRATEDBULLET : " + Integer.toString(penetBulletCount), 135, 50, 270);
			gc.strokeText("PENETRATEDBULLET : " + Integer.toString(penetBulletCount), 135, 50, 270);
		}

	}

	public void drawMap(GraphicsContext gc) {
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
			}
		}
	}
}
