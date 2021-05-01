package logic;

import entity.base.*;
public class Cell {
	private Entity entity;
	private boolean isEmpty;
	
	public Cell() {
		isEmpty = true;
		entity = null;
	}
	
	public boolean IsEmpty() {
		return isEmpty;
	}
	public boolean setEntity(Entity e) {
		if (isEmpty) {
			isEmpty = false;
			entity = e;
			return true;
		}else {
			if(e instanceof Weapon) {
				Interactable e1 =(Interactable) entity;
				return e1.interact(e);
			}else {
				return false;
			}
		}
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public void removeEntity() {
		entity = null;
		isEmpty = true;
	}
}
