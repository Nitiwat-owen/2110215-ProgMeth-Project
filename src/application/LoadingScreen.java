package application;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LoadingScreen extends Scene {
	private static Pane pane = new StackPane();
	private ProgressBar prog;
	private Label loading;
	private double value;

	public LoadingScreen(int width, int height) {
		super(pane, width, height);
		value = 0;
		prog = new ProgressBar();
		loading = new Label("Loading...");
		pane.getChildren().addAll(prog, loading);

		Thread thread = new Thread() {
			public void run() {
				value = 0d;
				while (value < 3d) {
					try {
						Thread.sleep(100);

						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								prog.setProgress(value);
							}
						});

						value += 0.01d;

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}
}
