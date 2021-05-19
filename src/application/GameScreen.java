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

//	private static Pane pane = new VBox();
//	Canvas canvas = new Canvas(800, 50);
//	Canvas gameCanvas = new Canvas(800, 750);
//	GraphicsContext gc = canvas.getGraphicsContext2D();
//	GraphicsContext gameGC = gameCanvas.getGraphicsContext2D();
//	private int time = 180;
//	private Thread topPaneThread;
//	private String[][] gameMap;
//	

	public GameScreen(int width, int height) {
		super(width, height);
		addListener();
	}

//	public GameScreen(int width, int height) {
//		super(pane, width, height);
//		//addListener();
//		Pane topPane = new Pane();
//		topPane.getChildren().add(canvas);
//
//		pane.getChildren().add(topPane);
//
//		topPaneThread = new Thread(() -> {
//			while (true) {
//				System.out.println("A");
//				try {
//					Thread.sleep(1000);
//					time--;
//					drawTimeOut(gc);
//					drawBulletCount(gc);
//					drawPenetBulletCount(gc);
//					drawBombCount(gc);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//					break;
//				}
//				System.out.println("B");
//			}
//		});
//		topPaneThread.start();
//		
//
//		Pane gamePane = new Pane();
//		gamePane.getChildren().add(gameCanvas);
//		pane.getChildren().add(gamePane);
//
//		gameMap = MapParser.readMap("map_test.csv");
//		GameController.InitializeMap(gameMap, 1, 6);
//		gameCanvas.requestFocus();
//
////		Thread drawBG = new Thread(() -> {
////			Platform.runLater(new Runnable() {
////				@Override
////				public void run() {
//					drawBackground(gameGC);
//					//drawMap(gameGC);
////				}
////			});
////			
////		});
////		drawBG.start();
////		Platform.runLater(new Runnable() {
////			@Override
////			public void run() {
////				drawBackground(gameGC);
////				drawMap(gameGC);
////			}
////		});
//
//		AnimationTimer animation = new AnimationTimer() {
//			public void handle(long now) {
//				System.out.println("X");
//				drawMap(gameGC);
//				System.out.println("Y");
//				RenderableHolder.getInstance().update();
//				System.out.println("Z");
//				//GameController.update();
//			}
//		};
//		animation.start();
////		
////		Thread gameThread = new Thread(() -> {
////			Platform.runLater(new Runnable() {
////				@Override
////				public void run() {
////					drawMap(gameGC);
////					RenderableHolder.getInstance().update();
////				}
////			});
////
////		});
////		gameThread.start();
////		
//		 InputUtility.addEventListener(this, gameGC);
//	}
//
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

	public void drawPenetBulletCount(GraphicsContext gc) {
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

	public void drawBombCount(GraphicsContext gc) {
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

	public void drawTimeOut(GraphicsContext gc, int time) {
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

	public void drawBackground(GraphicsContext gc) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				for (IRenderable entity : RenderableHolder.getInstance().getBackground()) {
					entity.draw(gc);
				}
			}
		});
	}
}
