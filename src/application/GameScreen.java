package application;

import javafx.animation.AnimationTimer;
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

public class GameScreen extends Scene {

	private static Pane pane = new VBox();
	Canvas canvas = new Canvas(800, 50);
	Canvas gameCanvas = new Canvas(800, 750);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	GraphicsContext gameGC = gameCanvas.getGraphicsContext2D();
	private int time = 180;
	private Thread timerThread;
	private String[][] gameMap;

	public GameScreen(int width, int height) {
		super(pane, width, height);
		Pane topPane = new Pane();
		topPane.getChildren().add(canvas);
		pane.getChildren().add(topPane);
		
		drawBulletCount(gc);
		drawPenetBulletCount(gc);
		drawBombCount(gc);

		timerThread = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(1000);
					time--;
					drawTimeOut(gc);
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
			}
		});
		timerThread.start();

		Pane gamePane = new Pane();
		gamePane.getChildren().add(gameCanvas);
		pane.getChildren().add(gamePane);
		
		gameMap = MapParser.readMap("map.csv");
		GameController.InitializeMap(gameMap, 2, 12);
		/*AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				drawMap(gc);
			}
		};
		//drawMap(gameGC);
		animation.start();*/
		drawMap(gameGC);
	}

	public void drawBulletCount(GraphicsContext gc) {
		gc.setLineWidth(2);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, 200, 100);

		gc.setFill(Color.AQUA);
		gc.setStroke(Color.BLUE);

		gc.setFont(Font.font("VERDANA", 50));
		int bulletCount = logic.GameController.getBullet_count();
		gc.fillText("BULLET : " + Integer.toString(bulletCount), 0, 50, 200);
		gc.strokeText("BULLET : " + Integer.toString(bulletCount), 0, 50, 200);
	}

	public void drawPenetBulletCount(GraphicsContext gc) {
		gc.setLineWidth(2);
		gc.setFill(Color.BLACK);
		gc.fillRect(200, 0, 300, 100);

		gc.setFill(Color.YELLOW);
		gc.setStroke(Color.RED);
		gc.setFont(Font.font("VERDANA", 50));
		int penetBulletCount = logic.GameController.getPenetrated_count();
		gc.fillText("PENETRATEDBULLET : " + Integer.toString(penetBulletCount), 200, 50, 300);
		gc.strokeText("PENETRATEDBULLET : " + Integer.toString(penetBulletCount), 200, 50, 300);
	}

	public void drawBombCount(GraphicsContext gc) {
		gc.setLineWidth(2);
		gc.setFill(Color.BLACK);
		gc.fillRect(500, 0, 200, 100);

		gc.setFill(Color.AQUA);
		gc.setStroke(Color.BLUE);
		gc.setFont(Font.font("VERDANA", 50));
		int bombCount = logic.GameController.getBomb_count();
		gc.fillText("BOMB : " + Integer.toString(bombCount), 500, 50, 200);
		gc.strokeText("BOMB : " + Integer.toString(bombCount), 500, 50, 200);
	}

	public void drawTimeOut(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(700, 0, 200, 100);
		int minute = time / 60;
		int second = time % 60;

		// int digitSecond = Integer.toString(second).length();

		gc.setFont(Font.font("VERDENA", 40));
		gc.setFill(Color.WHITE);

		String currentTime = "0" + minute + ":";
		if (Integer.toString(second).length() < 2) {
			currentTime = currentTime + "0" + second;
		} else {
			currentTime = currentTime + second;
		}
		gc.fillText(currentTime, 700, 50, 100);
	}

	public void drawMap(GraphicsContext gc) {
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
			}
		}
	}
}