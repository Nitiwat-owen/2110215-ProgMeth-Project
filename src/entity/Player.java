package entity;

import entity.base.*;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import logic.Cell;
import logic.GameController;
import sharedObject.RenderableHolder;

public class Player extends Entity implements Updatable {

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 50;
		this.radius = 16;
		this.centerX = x * 36 + radius;
		this.centerY = y * 36 + radius;
		this.dir = "W";
		this.visible = true;
		this.destroy = false;
		this.speed = 36.0;
	}

	@Override
	public int getIndex() {
		switch (dir) {
		case "W":
			return 0;
		case "D":
			return 1;
		case "S":
			return 2;
		case "A":
			return 3;
		default:
			break;
		}
		return -1;
	}

	@Override
	public void draw(GraphicsContext gc) {
		WritableImage tankImage = new WritableImage(RenderableHolder.tankSprite.getPixelReader(), this.getIndex() * 36,
				0, 36, 36);
		gc.drawImage(tankImage, x * 36, y * 36);
	}

	@Override
	public void update() {
		int playerX = this.getX();
		int playerY = this.getY();
		if (playerX == 14 && playerY == 2) {
			GameController.getGameMap().exitGate();
		}
		KeyCode code = InputUtility.getCode();
		switch (code) {
		case W:
			this.move("W");
			break;
		case A:
			this.move("A");
			break;
		case S:
			this.move("S");
			break;
		case D:
			this.move("D");
			break;
		default:
			break;

		}

	}
}
