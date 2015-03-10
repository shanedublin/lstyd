package com.mygdx.game.entities.items;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class ItemSlot  {

	
	public Item item = null;
	public Rectangle bounds;
	
	public String s = "no item";
	
	public String slotID;
	public boolean contains(Vector3 touch){
		return bounds.contains(touch.x, touch.y);
	}
	
	
}
