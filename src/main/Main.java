package main;

import javafx.application.Application;
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

public class Main extends Application {

	private Button playButton;
	private Button exitButton;
	private Text nameText;
	private VBox pane = new VBox();
	private static final int width = 800;
	private static final int height = 800;
	Canvas canvas = new Canvas(width, height);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	private static Image background = new Image("firstScene_Background.png");

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		pane.setSpacing(20);
		pane.setAlignment(Pos.CENTER);
//		Text nameText = new Text("Labyrinth Escape");
//		nameText.setFont(new Font("Verdana", 50));
//		pane.getChildren().add(nameText);
		Button playButton = new Button("Play");
		Button exitButton = new Button("Exit");

		exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.exit(0);
			}
		});
		drawNameText(gc);
		drawPlayButton(gc);
		drawBackground();
		pane.getChildren().addAll(canvas ,playButton, exitButton);

		Scene scene = new Scene(pane, 800, 800);
		stage.setScene(scene);
		stage.setTitle("Labyrinth Escape");
		stage.setResizable(false);
		stage.show();
	}
	
	public void drawNameText(GraphicsContext gc) {
		gc.setLineWidth(5);
		gc.setFill(Color.YELLOW);
		gc.setStroke(Color.RED);
		
		Font font = Font.font("Verdana", FontWeight.BOLD, 60);
		gc.setFont(font);
		
		gc.strokeText("Labyrinth Escape", 100, 200);
		gc.fillText("Labyrinth Escape", 100, 200);
	}
	
	public void drawPlayButton(GraphicsContext gc) {
		gc.setLineWidth(10);
		gc.setFill(Color.AQUA);
		gc.setStroke(Color.BLUEVIOLET);
		
		gc.strokeRect(300, 300, 200, 50);
		gc.fillRect(300, 300, 200, 50);
		
		gc.setFill(Color.BLUE);
		Font font = Font.font("Verdana", FontWeight.LIGHT, 40);
		gc.setFont(font);
		gc.fillText("PLAY", 350, 340);
	}
	

	public void drawBackground() {
		BackgroundImage Background = new BackgroundImage(new Image("firstScene_Background.png", 0, 0, false, true),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		pane.setBackground(new Background(Background));
	}
}
