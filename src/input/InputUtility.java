package input;

import javafx.scene.input.KeyCode;

public class InputUtility {
	//private static ArrayList<KeyCode> keyPressed = new ArrayList<>();
	private static KeyCode code = KeyCode.UNDEFINED ;

	public static KeyCode getCode() {
		return code;
	}

	public static void setCode(KeyCode code) {
		InputUtility.code = code;
	}

	private static boolean isPressed = false;
	private static boolean isTriggered = false;

	public static boolean getPressed() {
		return isPressed;
	}

	public static void setPressed(boolean isPressed) {
		InputUtility.isPressed = isPressed;
	}

	public static boolean getTriggered() {
		return isTriggered;
	}

	public static void setTriggered(KeyCode code, boolean isPressed) {
		if (isPressed) {
			isTriggered = true;
			if (!InputUtility.code.equals(code)) {
				InputUtility.code = code;
			}
		} else {
			isTriggered = false;
		}
	}

	public static void postUpdate() {
		isTriggered = false;
	}
}
