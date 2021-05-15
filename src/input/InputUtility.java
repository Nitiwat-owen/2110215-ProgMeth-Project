package input;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public class InputUtility {
	public void addEventListener(Scene sc, GraphicsContext gc) {
		sc.setOnKeyPressed((event) -> {
			KeyCode keycode = event.getCode();
			switch(keycode) {
			case ESCAPE:
				
			}
		})
	}
	
	public void escapeOnPressed(Scene sc) {
		if(sc. instanceof StackPane) {
			
		}
	}
}
