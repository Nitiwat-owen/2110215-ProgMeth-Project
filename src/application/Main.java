package application;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	private VBox startPane = new VBox();
	// private StackPane gamePane = new StackPane();
	private static final int width = 800;
	private static final int height = 800;
	Canvas canvas = new Canvas(800, 200);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	private static Image background = new Image("firstScene_Background.png");
	private Scene startScene;
	private GameScreen gameScene;
	private LoadingScreen loadingScene;
	private Stage window;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;

		startPane.setSpacing(50);
		startPane.setAlignment(Pos.CENTER);

		InitializeButton();

		drawNameText(gc);
		drawBackground();
		startPane.getChildren().addAll(canvas, playButton, exitButton);

//		Scene scene = new Scene(pane, width, height);
//		stage.setScene(scene);
//		stage.setTitle("Labyrinth Escape");
//		stage.setResizable(false);
//		stage.show();
		gameScene = new GameScreen(width, height);
		//loadingScene = new LoadingScene(width,height);
		startScene = new Scene(startPane, width, height);
		window.setScene(startScene);
		window.setTitle("Labyrinth Escape");
		window.setResizable(false);
		window.show();

	}

	public void drawNameText(GraphicsContext gc) {
		gc.setLineWidth(5);
		gc.setFill(Color.YELLOW);
		gc.setStroke(Color.RED);

		Font font = Font.font("Verdana", FontWeight.BOLD, 60);
		gc.setFont(font);

		gc.strokeText("Labyrinth Escape", 100, 100);
		gc.fillText("Labyrinth Escape", 100, 100);
	}

	public void drawBackground() {
		BackgroundImage Background = new BackgroundImage(new Image("firstScene_Background.png", 0, 0, false, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		startPane.setBackground(new Background(Background));
	}

	public void InitializeButton() {
		playButton = new Button("PLAY");
		playButton.setFont(Font.font("Verdana", FontWeight.LIGHT, 40));
		playButton.setPrefWidth(200);
		playButton.setPrefHeight(50);
		playButton.setStyle("-fx-background-color: #1E90FF");
		playButton.setCursor(Cursor.HAND);
		playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				window.setScene(gameScene);
			}
		});
		exitButton = new Button("EXIT");
		exitButton.setFont(Font.font("Verdana", FontWeight.LIGHT, 40));
		exitButton.setPrefWidth(200);
		exitButton.setPrefHeight(50);
		exitButton.setStyle("-fx-background-color: #1E90FF");
		exitButton.setCursor(Cursor.HAND);
		exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.exit(0);
			}
		});
	}
}
