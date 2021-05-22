package exception;

import javafx.scene.control.Alert;

public class OutOfWeaponException extends Exception {
	public OutOfWeaponException(String bullet) {
		Alert error = new Alert(Alert.AlertType.WARNING);
		error.setContentText(bullet + " is outed!!! Find more in the map");
		error.show();
	}
}
