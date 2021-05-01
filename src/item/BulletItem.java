package item;

import entity.base.*;
import logic.GameController;

public class BulletItem extends Entity implements Interactable {
	public boolean interact(Entity e) {
		this.remove();
		GameController.addBulletCount();
		return true;
	}
}
