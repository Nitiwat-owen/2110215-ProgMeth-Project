package input;

import java.util.ArrayList;

import application.GameScreen;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.*;

public class InputUtility {
	private static ArrayList<KeyCode> keyPressed = new ArrayList<>();
	private static String code = "";
	public static String getCode() {
		return code;
	}

	public static void setCode(String code) {
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

	public static void setTriggered(String code, boolean pressed) {
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

//	public static boolean getKeyPressed(KeyCode keyCode) {
//		return keyPressed.contains(keyCode);
//	}
//
//	public static void setKeyPressed(KeyCode keyCode, boolean pressed) {
//		if (pressed) {
//			if (!keyPressed.contains(keyCode)) {
//				keyPressed.add(keyCode);
//			} else {
//				keyPressed.remove(keyCode);
//			}
//			System.out.println(keyPressed);
//		}
//	}
//
//	public static void addEventListener(Scene sc, GraphicsContext gc) {
//		sc.setOnKeyPressed((event) -> {
//			KeyCode keycode = event.getCode();
//			switch (keycode) {
//			case W:
//				GameController.movePlayer("W");
//				break;
//			case A:
//				GameController.movePlayer("A");
//				break;
//			case S:
//				GameController.movePlayer("S");
//				break;
//			case D:
//				GameController.movePlayer("D");
//				break;
//			default:
//				break;
//			}
////			Thread t = new Thread(() -> {
////				((GameScreen) sc).drawMap(gc);
////			});
////			t.start();
//
//		});
//	}

}
