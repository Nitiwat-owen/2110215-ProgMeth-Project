package item;

import entity.base.*;
import logic.*;
public class BombItem extends Entity implements Interactable {
	public BombItem(int x, int y) {
		this.x = x*48;
		this.y = y*48;
	}
	public boolean interact(Entity e) {
		this.remove();
		GameController.addBombCount();
		return true;
	}
	@Override
	public int getIndex() {
		return 2;
	}
}
