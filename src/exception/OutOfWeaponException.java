package exception;

import javafx.scene.control.Alert;

public class OutOfWeaponException extends Exception {

	public OutOfWeaponException(String weapon) {
		Alert error = new Alert(Alert.AlertType.WARNING);
		error.setContentText(weapon + " is outed!!! Find more in the map");
		error.show();
	}
}
