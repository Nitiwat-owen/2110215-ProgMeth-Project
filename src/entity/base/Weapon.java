package entity.base;

public abstract class Weapon extends Entity{
	protected int damage;

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public abstract void update();	

}
