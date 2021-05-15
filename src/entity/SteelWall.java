package entity;

import entity.base.*;
public class SteelWall extends Entity{
	public SteelWall(int x,int y) {
		this.x = x*48;
		this.y = y*48;
	}
	@Override
	public int getIndex() {
		return 4;
	}
}
