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
	private int bullets;
	private int penetratedBullets;
	private int bombs;
	private int angle;

	public Player(int x, int y) {
		bullets = 10;
		penetratedBullets = 0;
		bombs = 0;
		this.x = x;
		this.y = y;
		z = 20;
		this.angle = 0;
		this.visible = true;
		this.destroy = false;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public boolean move(String dir) {
		int targetx = x;
		int targety = y;

		switch (dir) {
		case "W":
			targety -= 1;
			this.setAngle(0);
			break;
		case "A":
			targetx -= 1;
			this.setAngle(270);
			break;
		case "S":
			targety += 1;
			this.setAngle(180);
			break;
		case "D":
			targetx += 1;
			this.setAngle(90);
			break;
		default:
			break;
		}
		if (GameController.getGameMap().isMovable(targetx, targety, this)) {
//			this.destroy = true;
//			RenderableHolder.getInstance().update();
//			RenderableHolder.getInstance().add(this);
			if (targetx != x || targety != y) {
				RenderableHolder.getInstance().add(new Cell(x, y));
			}
			this.remove();
			this.x = targetx;
			this.y = targety;
			return true;
		}
		return false;
	}

	@Override
	public int getIndex() {
		switch (angle) {
		case 0:
			return 0;
		case 90:
			return 1;
		case 180:
			return 2;
		case 270:
			return 3;
		default:
			break;
		}
		return -1;
	}

	@Override
	public int getZ() {
		return 50;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public boolean isDestroyed() {
		return destroy;
	}

	@Override
	public void draw(GraphicsContext gc) {
		WritableImage tankImage = new WritableImage(RenderableHolder.tankSprite.getPixelReader(), this.getIndex() * 48,
				0, 48, 48);
		gc.drawImage(tankImage, x * 48, y * 48);
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
		if(InputUtility.getCode().equals(" ")) {
			
		}
	}
}
