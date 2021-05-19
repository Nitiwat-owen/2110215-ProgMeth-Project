package entity;

import entity.base.*;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import logic.Cell;
import logic.GameController;
import sharedObject.RenderableHolder;

public class Player extends Entity {
//	private int bullets;
//	private int penetratedBullets;
//	private int bombs;
	// private int angle;
	// private String dir;

	public Player(int x, int y) {
//		bullets = 10;
//		penetratedBullets = 0;
//		bombs = 0;
		this.x = x;
		this.y = y;
		this.z = 50;
		// this.angle = 0;
		this.dir = "W";
		this.visible = true;
		this.destroy = false;
		this.speed = 36;
	}

//	public int getAngle() {
//		return angle;
//	}
//
//	public void setAngle(int angle) {
//		this.angle = angle;
//	}
//
//	public boolean move(String dir) {
//		int targetx = x;
//		int targety = y;
//
//		switch (dir) {
//		case "W":
//			targety -= 1;
//			this.setAngle(0);
//			break;
//		case "A":
//			targetx -= 1;
//			this.setAngle(270);
//			break;
//		case "S":
//			targety += 1;
//			this.setAngle(180);
//			break;
//		case "D":
//			targetx += 1;
//			this.setAngle(90);
//			break;
//		default:
//			break;
//		}
//		if (GameController.getGameMap().isMovable(targetx, targety, this)) {
////			this.destroy = true;
////			RenderableHolder.getInstance().update();
////			RenderableHolder.getInstance().add(this);
//			if (targetx != x || targety != y) {
//				RenderableHolder.getInstance().add(new Cell(x, y));
//			}
//			this.remove();
//			this.x = targetx;
//			this.y = targety;
//			return true;
//		}
//		return false;
//	}

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

	public void update() {
		if (InputUtility.getCode().equals("W")) {
			this.move("W");
		}
		if (InputUtility.getCode().equals("A")) {
			this.move("A");
		}
		if (InputUtility.getCode().equals("S")) {
			this.move("S");
		}
		if (InputUtility.getCode().equals("D")) {
			this.move("D");
		}
		if (InputUtility.getCode().equals(" ")) {

		}
	}
}
