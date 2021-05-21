package entity.base;

public abstract class CollidableEntity extends Entity implements Interactable {
	public boolean isCollide(Entity e) {
		double distance = Math.hypot(e.getCenterX() - this.getCenterX(), e.getCenterX() - this.getCenterY());
		return distance <= e.getRadius() + this.getRadius();
	}
}
