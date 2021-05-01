package item;

import entity.base.*;
import logic.GameController;

public class PenetratedBulletItem extends Entity implements Interactable{
	public boolean interact(Entity e) {
		this.remove();
		GameController.addPenetratedCount();
		return true;
	}
}
