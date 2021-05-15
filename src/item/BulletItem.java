package item;

import entity.base.*;
import logic.GameController;

public class BulletItem extends Entity implements Interactable {
	public BulletItem(int x, int y) {
		this.x = x*48;
		this.y = y*48;
	}
	public boolean interact(Entity e) {
		this.remove();
		GameController.addBulletCount();
		return true;
	}
	@Override
	public int getIndex() {
		return 0;
	}
}
