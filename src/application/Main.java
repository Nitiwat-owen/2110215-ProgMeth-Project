package application;

import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.GameController;
import sharedObject.RenderableHolder;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class Main extends Application {

	private static Button playButton;
	private static Button exitButton;
	private static Button helpButton;
	private static Button creditButton;
	private static Button backButton;
	private static Button resumeButton;

	private VBox startPane = new VBox();
	private VBox endPane = new VBox();
	private static StackPane playPane = new StackPane();
	private Pane topPane = new Pane();
	private Pane playingPane = new Pane();
	private VBox gamePane = new VBox();
	private static VBox menuPane = new VBox();

	private static final int width = 540;
	private static final int height = 600;

	public static Canvas startCanvas = new Canvas(width, 200);
	public static GraphicsContext startGC = startCanvas.getGraphicsContext2D();

	public static GameScreen topCanvas = new GameScreen(width, 60);
	public static GraphicsContext topPaneGC = topCanvas.getGraphicsContext2D();

	public static GameScreen gameCanvas = new GameScreen(width, 540);
	public static GraphicsContext gameGC = gameCanvas.getGraphicsContext2D();

	public static Canvas endCanvas = new Canvas(width, 500);
	public static GraphicsContext endGC = endCanvas.getGraphicsContext2D();

	private Scene startScene;
	private Scene gameScene;
	private Scene endScene;

	private Stage window;

	private String[][] gameMap;

	public static AnimationTimer animation;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		startPane.setSpacing(20);
		startPane.setAlignment(Pos.CENTER);

		initializePlayButton();
		initializeHelpButton();
		initializeCreditButton();
		initializeExitButton();
		initializeBackButton();
		initializeResumeButton();

		drawNameText(startGC);
		drawBackground(startPane);
		startPane.getChildren().addAll(startCanvas, playButton, helpButton, creditButton, exitButton);
		startScene = new Scene(startPane, width, height);
		
		window.setScene(startScene);
		window.setTitle("Labyrinth Escape");
		window.setResizable(false);
		window.show();

		gameScene = new Scene(playPane, width, height);
		addListener(gameScene);
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

	public void drawEndText(GraphicsContext gc) {
		gc.setLineWidth(5);
		gc.setFill(Color.YELLOW);
		gc.setStroke(Color.RED);

		Font font = Font.font("Verdana", FontWeight.BOLD, 60);
		gc.setFont(font);

		gc.strokeText("Congratulations", 20, 150, 500);
		gc.fillText("Congratulations", 20, 150, 500);

		gc.strokeText("You Won!!", 120, 300, 500);
		gc.fillText("You Won!!", 120, 300, 500);

		gc.strokeText("Press SPACEBAR to Exit", 20, 450, 500);
		gc.fillText("Press SPACEBAR to Exit", 20, 450, 500);
	}

	public void drawBackground(Pane pane) {
		BackgroundImage Background = new BackgroundImage(new Image("firstScene_Background.png", 0, 0, false, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		pane.setBackground(new Background(Background));
	}

	public void initializePlayButton() {
		playButton = new Button("PLAY");
		playButton.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
		playButton.setPrefWidth(200);
		playButton.setPrefHeight(30);
		playButton.setStyle("-fx-background-color: #1E90FF");
		playButton.setCursor(Cursor.HAND);
		playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				gamingScreen();
				window.setScene(gameScene);
				animation = new AnimationTimer() {
					public void handle(long now) {
						topCanvas.drawTopPane(topPaneGC);
						gameCanvas.drawMap(gameGC);
						GameController.update();
						RenderableHolder.getInstance().update();
						if (GameController.isGameWin()) {
							endingScreen();
						}
					}
				};
				animation.start();
			}
		});
	}

	public void initializeExitButton() {
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
	}

	public void initializeHelpButton() {
		helpButton = new Button("HELP");
		helpButton.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
		helpButton.setPrefWidth(200);
		helpButton.setPrefHeight(30);
		helpButton.setStyle("-fx-background-color: #1E90FF");
		helpButton.setCursor(Cursor.HAND);
		helpButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				startPane.getChildren().clear();
				Canvas helpCanvas = new Canvas(width, 500);
				GraphicsContext helpGC = helpCanvas.getGraphicsContext2D();
				startPane.getChildren().addAll(helpCanvas, backButton);
				drawBackground(startPane);
				helpGC.setFont(Font.font("VERDENA", FontWeight.MEDIUM, 35));
				helpGC.setFill(Color.WHITE);
				helpGC.fillText("KEY CONTROLS", 150, 50);
				helpGC.fillText("W - MOVE UP", 50, 130);
				helpGC.fillText("A - TURN LEFT", 50, 180);
				helpGC.fillText("S - MOVE DOWN", 50, 230);
				helpGC.fillText("D - TURN RIGHT", 50, 280);
				helpGC.fillText("SPACE - SHOOT A BULLET", 50, 330);
				helpGC.fillText("Q - SWITCH TYPE OF BULLET", 50, 380);
				helpGC.fillText("B - PLANT A BOMB", 50, 430);
				helpGC.fillText("P - PAUSE THE GAME", 50, 480);
			}
		});
	}

	public void initializeCreditButton() {
		creditButton = new Button("CREDIT");
		creditButton.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
		creditButton.setPrefWidth(200);
		creditButton.setPrefHeight(30);
		creditButton.setStyle("-fx-background-color: #1E90FF");
		creditButton.setCursor(Cursor.HAND);
		creditButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				startPane.getChildren().clear();
				Canvas creditCanvas = new Canvas(width, 500);
				GraphicsContext creditGC = creditCanvas.getGraphicsContext2D();
				startPane.getChildren().addAll(creditCanvas, backButton);
				drawBackground(startPane);
				creditGC.setFont(Font.font("VERDENA", FontWeight.MEDIUM, 35));
				creditGC.setFill(Color.WHITE);
				creditGC.fillText("CREDITS", 200, 100);
				creditGC.fillText("Theerachot Dejsuwannakij", 50, 180);
				creditGC.fillText("Nitiwat Jongruktrakoon", 50, 270);
			}
		});
	}

	public void initializeBackButton() {
		backButton = new Button("BACK");
		backButton.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
		backButton.setPrefWidth(200);
		backButton.setPrefHeight(30);
		backButton.setStyle("-fx-background-color: #1E90FF");
		backButton.setCursor(Cursor.HAND);
		backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				startPane.getChildren().clear();
				drawNameText(startGC);
				drawBackground(startPane);
				startPane.getChildren().addAll(startCanvas, playButton, helpButton, creditButton, exitButton);
				window.setScene(startScene);
			}
		});
	}

	public void initializeResumeButton() {
		resumeButton = new Button("RESUME");
		resumeButton.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
		resumeButton.setPrefWidth(200);
		resumeButton.setPrefHeight(30);
		resumeButton.setStyle("-fx-background-color: #1E90FF");
		resumeButton.setCursor(Cursor.HAND);
		resumeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				playPane.getChildren().remove(menuPane);
				animation.start();
			}
		});
	}

	public void addListener(Scene scene) {
		scene.setOnKeyPressed((KeyEvent event) -> {
			KeyCode new_code = event.getCode();
			if (!InputUtility.getPressed()) {
				InputUtility.setTriggered(new_code, true);
			}
			InputUtility.setPressed(true);
		});
		scene.setOnKeyReleased((KeyEvent event) -> {
			InputUtility.setPressed(false);
		});
	}

	public static void creatingMenuPane() {
		menuPane.getChildren().clear();

		menuPane.setAlignment(Pos.CENTER);
		playPane.getChildren().add(menuPane);
		menuPane.setMaxHeight(150);
		menuPane.setMaxWidth(250);
		menuPane.setBorder(new Border(
				new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		menuPane.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
		menuPane.setSpacing(10);
		menuPane.getChildren().addAll(resumeButton, backButton, exitButton);
	}

	public void endingScreen() {
		drawEndText(endGC);
		drawBackground(endPane);
		endPane.getChildren().addAll(endCanvas);
		endScene = new Scene(endPane, width, height);

		window.setScene(endScene);
		animation.stop();
		endScene.setOnKeyPressed((event) -> {
			KeyCode code = event.getCode();
			if (code == KeyCode.SPACE) {
				System.exit(0);
			}
		});
	}

	public void gamingScreen() {
		gamePane.getChildren().clear();
		topPane.getChildren().clear();
		playingPane.getChildren().clear();
		playPane.getChildren().clear();

		RenderableHolder.getInstance().getEntities().clear();

		playPane.getChildren().add(gamePane);

		topPane.getChildren().add(topCanvas);
		gamePane.getChildren().add(topPane);

		playingPane.getChildren().add(gameCanvas);
		gamePane.getChildren().add(playingPane);

		gameMap = MapParser.readMap("map.csv");
		GameController.InitializeMap(gameMap, 1, 13);

		gameCanvas.requestFocus();
		topCanvas.requestFocus();
	}
}
