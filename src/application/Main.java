package application;

import entity.*;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.Cell;
import logic.GameController;
import sharedObject.RenderableHolder;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Main extends Application {

	private Button playButton;
	private Button exitButton;
	private Button helpButton;
	private Button creditButton;
	private VBox startPane = new VBox();
	public static Pane gamePane = new VBox();
	private static final int width = 540;
	private static final int height = 600;
	public static Canvas startCanvas = new Canvas(width, 200);
	public static GraphicsContext startGC = startCanvas.getGraphicsContext2D();

	public static GameScreen topCanvas = new GameScreen(width, 60);
	public static GraphicsContext topPaneGC = topCanvas.getGraphicsContext2D();

	public static GameScreen gameCanvas = new GameScreen(width, 540);
	public static GraphicsContext gameGC = gameCanvas.getGraphicsContext2D();

	private static Image background = new Image("firstScene_Background.png");
	private Scene startScene;
	private Scene gameScene;
	private LoadingScreen loadingScene;
	private Stage window;

	private String[][] gameMap;
	private Thread thread;
	private Thread drawingThread;
	private Thread drawingWeapon;
	private GameScreen gameScreen;

	private AnimationTimer animation;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		startPane.setSpacing(20);
		startPane.setAlignment(Pos.CENTER);

		InitializeButton();

		drawNameText(startGC);
		drawBackground();
		startPane.getChildren().addAll(startCanvas, playButton, helpButton, creditButton, exitButton);

		startScene = new Scene(startPane, width, height);
		window.setScene(startScene);
		window.setTitle("Labyrinth Escape");
		window.setResizable(false);
		window.show();

		loadingScene = new LoadingScreen(width, height);

		Pane topPane = new Pane();
		topPane.getChildren().add(topCanvas);

		gamePane.getChildren().add(topPane);

		gameScene = new Scene(gamePane, width, height);
		drawGameBackground();

		Pane playPane = new Pane();
		playPane.getChildren().add(gameCanvas);
		gamePane.getChildren().add(playPane);

		gameMap = MapParser.readMap("map.csv");
		GameController.InitializeMap(gameMap, 1, 6);

		gameCanvas.requestFocus();
		topCanvas.requestFocus();

		Thread t = new Thread(() -> {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					// gameCanvas.drawBackground(gameGC);
					topCanvas.drawBulletCount(topPaneGC);
					topCanvas.drawPenetBulletCount(topPaneGC);
					topCanvas.drawBombCount(topPaneGC);
					gameCanvas.drawMap(gameGC);
				}
			});
		});
		t.start();
		// addListener(gameScene);
		addEventListener(gameScene);
	}

	public void addListener(Scene scene) {
		scene.setOnKeyPressed((KeyEvent event) -> {
			String new_code = event.getCode().toString();
			if (!InputUtility.getPressed()) {
				InputUtility.setTriggered(new_code, true);
				System.out.println(new_code);
			}
			InputUtility.setPressed(true);
		});
		scene.setOnKeyReleased((KeyEvent event) -> {
			InputUtility.setPressed(false);
		});
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		// this.topPaneThread.interrupt();
		thread.interrupt();
		drawingThread.interrupt();
		drawingWeapon.interrupt();
		// animation.stop();
	}

	public void drawNameText(GraphicsContext gc) {
		gc.setLineWidth(5);
		gc.setFill(Color.YELLOW);
		gc.setStroke(Color.RED);

		Font font = Font.font("Verdana", FontWeight.BOLD, 60);
		gc.setFont(font);

		gc.strokeText("Labyrinth Escape", 20, 100, 500);
		gc.fillText("Labyrinth Escape", 20, 100, 500);
	}

	public void drawBackground() {
		BackgroundImage Background = new BackgroundImage(new Image("firstScene_Background.png", 0, 0, false, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		startPane.setBackground(new Background(Background));
	}

	public void drawGameBackground() {
		BackgroundImage gameBackground = new BackgroundImage(new Image("fieldBackground.png", 0, 0, false, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		gamePane.setBackground(new Background(gameBackground));
	}

	public void InitializeButton() {
		playButton = new Button("PLAY");
		playButton.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
		playButton.setPrefWidth(200);
		playButton.setPrefHeight(30);
		playButton.setStyle("-fx-background-color: #1E90FF");
		playButton.setCursor(Cursor.HAND);
		playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Thread loading = new Thread(() -> {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							window.setScene(loadingScene);
						}
					});
					System.out.println("Loading SCene finish");
				});
				// window.setScene(loadingScene);
				Thread changingScene = new Thread(() -> {
					try {
						Thread.sleep(1000);
						loading.join();
						System.out.println("Start changing Scene");
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								window.setScene(gameScene);
							}
						});
					} catch (InterruptedException e) {

					}

				});
				loading.start();
				changingScene.start();
			}
		});

		exitButton = new Button("EXIT");
		exitButton.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
		exitButton.setPrefWidth(200);
		exitButton.setPrefHeight(30);
		exitButton.setStyle("-fx-background-color: #1E90FF");
		exitButton.setCursor(Cursor.HAND);
		exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.exit(0);
			}
		});

		helpButton = new Button("HELP");
		helpButton.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
		helpButton.setPrefWidth(200);
		helpButton.setPrefHeight(30);
		helpButton.setStyle("-fx-background-color: #1E90FF");
		helpButton.setCursor(Cursor.HAND);

		creditButton = new Button("CREDIT");
		creditButton.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
		creditButton.setPrefWidth(200);
		creditButton.setPrefHeight(30);
		creditButton.setStyle("-fx-background-color: #1E90FF");
		creditButton.setCursor(Cursor.HAND);
	}

	public void addEventListener(Scene scene) {
		scene.setOnKeyPressed((event) -> {
			KeyCode keycode = event.getCode();
			switch (keycode) {
			case W:
				GameController.movePlayer("W");

				break;
			case A:
				GameController.movePlayer("A");

				break;
			case S:
				GameController.movePlayer("S");

				break;
			case D:
				GameController.movePlayer("D");

				break;
			case SPACE:
				GameController.shoot(gameGC);
				break;
			case B:
				// GameController.plantedBomb();
				break;
			default:
				break;
			}
			RenderableHolder.getInstance().update();
			drawingThread = new Thread(() -> {
				topCanvas.drawBulletCount(topPaneGC);
				topCanvas.drawPenetBulletCount(topPaneGC);
				topCanvas.drawBombCount(topPaneGC);
				gameCanvas.drawMap(gameGC);
			});

//			drawingWeapon = new Thread(() -> {
//				try {
//					drawingThread.join();
//					gameCanvas.drawWeapon(gameGC);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			});

			drawingThread.start();
			//drawingWeapon.start();
		});

	}

	public void loadingScreen() {
		startPane.getChildren().clear();

//		StackPane loadingPane = new StackPane();
		Canvas loadingCanvas = new Canvas(800, 800);
		GraphicsContext loadingGC = loadingCanvas.getGraphicsContext2D();

		startPane.getChildren().add(loadingCanvas);
		loadingGC.setLineWidth(5);
		loadingGC.setFill(Color.BLACK);
		loadingGC.fillRect(0, 0, 800, 800);

		loadingGC.setFont(Font.font("VERDENA", FontWeight.BOLD, 100));
		loadingGC.setFill(Color.WHITE);
		loadingGC.fillText("LOADING...", 150, 400);
//		
//		Scene loadingScene = new Scene(loadingPane,width,height);

//		StackPane loadingScreen = new StackPane();
//		loadingScreen.setPrefWidth(800);
//		loadingScreen.setPrefHeight(800);

//		FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), startPane);
//		fadeIn.setFromValue(0);
//		fadeIn.setToValue(1);
//		fadeIn.setCycleCount(1);
//
//		FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), startPane);
//		fadeOut.setFromValue(1);
//		fadeOut.setToValue(1);
//		fadeOut.setCycleCount(1);
//
//		fadeIn.play();
//		Thread loadingMap = new Thread(() -> {
//			Platform.runLater(new Runnable() {
//				@Override
//				public void run() {
//					window.setScene(gameScene);
//				}
//			});
//
//		});
//		loadingMap.start();
//
//		Thread finishedMap = new Thread(() -> {
//			try {
//				loadingMap.join();
//				fadeIn.setOnFinished((e) -> {
//					fadeOut.play();
//				});
//			} catch (InterruptedException e) {
//				throw new RuntimeException(e);
//			}
//		});
//		finishedMap.start();
//
//		fadeOut.setOnFinished((e) -> {
//		});
	}
}
