package input;

import application.GameScreen;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.*;

public class InputUtility {
	public static void addEventListener(Scene sc, GraphicsContext gc) {
		sc.setOnKeyPressed((event) -> {
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
			default:
				break;
			}
			Thread t = new Thread(() -> {
				((GameScreen) sc).drawMap(gc);
			});
			t.start();

		});
	}

}
