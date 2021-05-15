package item;

import entity.base.*;
import logic.GameController;

public class PenetratedBulletItem extends Entity implements Interactable{
	public PenetratedBulletItem(int x, int y) {
		this.x = x*48;
		this.y = y*48;
	}
	public boolean interact(Entity e) {
		this.remove();
		GameController.addPenetratedCount();
		return true;
	}
	@Override
	public int getIndex() {
		return 1;
	}
}
