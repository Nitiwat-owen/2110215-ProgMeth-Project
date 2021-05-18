package application;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class LoadingScreen extends Scene {

	private static Pane pane = new StackPane();

	public LoadingScreen(int width, int height) {
		super(pane, width, height);

		Canvas canvas = new Canvas(width, height);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		pane.getChildren().add(canvas);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, width, height);

		gc.setFont(Font.font("VERDENA", FontWeight.BOLD, 50));
		gc.setFill(Color.WHITE);
		gc.fillText("LOADING...", 300, 400);

		FadeTransition fade = new FadeTransition();
		
		fade.setDuration(Duration.seconds(1));
		
		fade.setFromValue(1);
		fade.setToValue(0.2);
		
		fade.setCycleCount(100);
		
		fade.setAutoReverse(true);
		
		fade.setNode(canvas);
		
		fade.play();
	}
//	private static Pane pane = new StackPane();
//	private ProgressBar prog;
//	private Label loading;
//	private double value;
//	public LoadingScreen(int width, int height) {
//		super(pane,width,height);
//		value = 0;
//		prog = new ProgressBar();
//		loading = new Label("Loading...");
//		pane.getChildren().addAll(prog,loading);
//
//		Thread thread = new Thread(){
//		    public void run(){
//		    	value = 0d;
//				while(value<3d) {
//					try {
//						Thread.sleep(100);
//						
//						Platform.runLater(new Runnable(){
//							@Override
//							public void run() {
//							prog.setProgress(value);
//							}
//							});
//						  
//						
//						value+=0.01d;
//						
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//		    }
//		  };
//		  thread.start();
//	}
}
