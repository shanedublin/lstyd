package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameScreen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.buildings.Building;
import com.mygdx.game.resources.ResourcesLoader;

public class MapTile {

	public boolean occupied;
	private Building building;
	private int x;
	private int y;
	private Texture backgroundTexture = ResourcesLoader.instance.getTexture(ResourcesLoader.LONGGRASS);
	private Texture forGroundTexture;
	
	
	
	
	public Texture getBackgroundTexture() {
		return backgroundTexture;
	}
	public void setBackgroundTexture(Texture backgroundTexture) {
		this.backgroundTexture = backgroundTexture;
	}
	public Texture getForGroundTexture() {
		return building.getBuildingTexture();
	}
	public void setForGroundTexture(Texture forGroundTexture) {
		this.forGroundTexture = forGroundTexture;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public MapTile(int x, int y){
		this.x = x;
		this.y = y;
		occupied = false;
		building = null;
	}
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building building) {
		if(building != null){
			this.occupied = true;
		}
		
		this.building = building;
		
	}
	
	
	
}
