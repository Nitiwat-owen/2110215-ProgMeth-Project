package input;

import java.util.ArrayList;

import application.GameScreen;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.*;

public class InputUtility {
	//private static ArrayList<KeyCode> keyPressed = new ArrayList<>();
	private static KeyCode code = KeyCode.UNDEFINED ;

	public static KeyCode getCode() {
		return code;
	}

	public static void setCode(KeyCode code) {
		InputUtility.code = code;
	}

	private static boolean pressed = false;
	private static boolean triggered = false;

	public static boolean getPressed() {
		return pressed;
	}

	public static void setPressed(boolean pressed) {
		if (pressed) {
			InputUtility.pressed = true;
		} else {
			InputUtility.pressed = false;
		}
	}

	public static boolean getTriggered() {
		return triggered;
	}

	public static void setTriggered(KeyCode code, boolean pressed) {
		if (pressed) {
			triggered = true;
			if (!InputUtility.code.equals(code)) {
				InputUtility.code = code;
			}
		} else {
			triggered = false;
		}
	}

	public static void postUpdate() {
		triggered = false;
	}
}
