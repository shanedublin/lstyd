package com.mygdx.game.entities.collisions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public abstract class Intersect {

	
	
	
	public static boolean CircleRectangleIntersection(Circle c , Rectangle r){

		//System.out.println("checking collsion");
		if(c == null || r == null){
			Gdx.app.log("intersect", "null pointer");
			return false;
		}
		
		float distX = Math.abs( c.x - (r.x + r.width/2));
		float distY = Math.abs(c.y - r.y + r.height/2);
		
		
		if(distX > r.width/2 +c.radius) return false;
		if(distY > r.height/2 +c.radius) return false;
		
		if(distX <= r.width/2) return true;
		if(distY <= r.height/2) return true;
		
		float cornerdist = (float) (Math.pow((distX - r.width/2),2) +
         Math.pow((distY - r.height/2),2));

		return (cornerdist <= (c.radius) * c.radius);
		
		
		
	
		
		
	}
	
}
