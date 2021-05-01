package item;

import entity.base.*;
import logic.*;
public class BombItem extends Entity implements Interactable {
	public boolean interact(Entity e) {
		this.remove();
		GameController.addBombCount();
		return true;
	}
}
