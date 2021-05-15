package entity;
import entity.base.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import sharedObject.RenderableHolder;
import logic.*;
public class Field extends Entity{
	@Override
	public int getIndex() {
		return 3;
	}
	@Override
	public boolean isDestroyed() {
		return false;
	}
	@Override
	public boolean isVisible() {
		return true;
	}
	@Override
	public int getZ() {
		return 0;
	}
	@Override
	public void draw(GraphicsContext gc) {
		for (int x = 0; x <= GameController.getGameMap().getWidth(); x++) {
			for (int y = 0; y <= GameController.getGameMap().getHeight(); y++) {
				WritableImage croppedImage = new WritableImage(RenderableHolder.mapSprite.getPixelReader(),
						this.getIndex() * 24, 0, 24, 24);
				gc.drawImage(croppedImage, x * 24, y * 24);
			}
		}
	}
	
}
