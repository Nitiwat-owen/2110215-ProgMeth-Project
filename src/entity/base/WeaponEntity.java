package entity.base;

import javafx.scene.canvas.GraphicsContext;

public abstract class WeaponEntity extends Entity{
	protected int damage;

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public abstract void update(GraphicsContext gc);	

}
