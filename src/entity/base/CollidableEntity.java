package entity.base;

public abstract class CollidableEntity extends Entity implements Interactable {
	public boolean isCollide(Entity e) {
		return Math.hypot(e.getCenterX() - this.getCenterX(),
				e.getCenterY() - this.getCenterY()) <= (e.getRadius() + this.getRadius());
	}
}
