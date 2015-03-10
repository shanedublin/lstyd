package com.mygdx.game.entities.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.collisions.Intersect;
import com.mygdx.game.entities.logic.Hittable;
import com.mygdx.game.resources.ResourcesLoader;

/**
 * this is a building.. should be abstract in the future.
 * @author shane
 *
 */
public abstract class Building extends Entity implements Hittable
{
	

	float slope;
	boolean slopeSet;// dirtyboolean hue hue
	protected Texture texture;
	
	
	
	
	
	//MapTile tileInfo;
	public Building(int x, int y){
		super();
	//	tileInfo = new MapTile(x,y);
		slopeSet = false;
		texture = ResourcesLoader.instance.getTexture("Wall.png");
	}
	
	
	public Texture getBuildingTexture() {
		return texture;
	}


	@Override
	public void damage(int damage) {
		
		System.out.println(this.getClass().getSimpleName() + " Object was hit");
	}

	@Override
	public boolean hit(Circle circle) {
		
		return Intersect.CircleRectangleIntersection(circle, getBounds());
	}

	public void setBuildingTexture(Texture buildingTexture) {
		this.texture = buildingTexture;
	}




	public void findSlope(){
		
		float xb = this.getBounds().x;
		float xc = this.getPosition().x;
		
		float yb = this.getBounds().y;
		float yc = this.getPosition().y;
		
		this.slope = (yc-yb) / (xc-xb);
		slopeSet = true;
		//System.out.println(slope);
		
				
	}
	
	
	public float slopePos(float x){				
		if(!slopeSet){ findSlope();}
		float b = this.getPosition().y - slope * this.getPosition().x;
		return slope * x + b;	
	}
	
	public float slopeNeg(float x){
		if(!slopeSet){ findSlope();}
		float b = this.getPosition().y + slope * this.getPosition().x;
		
		return  -slope * x +b;
	}

	@Override
	public void renderThis() {
		// TODO Auto-generated method stub
		
	}
	
	
}
