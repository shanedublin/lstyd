package com.mygdx.game.entities.buildings;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameScreen;
import com.mygdx.game.entities.collisions.Intersect;
import com.mygdx.game.entities.items.Item;
import com.mygdx.game.entities.items.ItemList;
import com.mygdx.game.entities.logic.Hittable;
import com.mygdx.game.resources.ResourcesLoader;

public class OreMine extends Building implements Hittable {

	public OreMine(int x, int y) {
		super(x, y);
		texture = ResourcesLoader.instance.getTexture("OreMine.png");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void damage(int damage) {
		super.damage(damage);
		// TODO Auto-generated method stub
	}

	@Override
	public boolean hit(Circle circle) {
		//return super.hit(circle);
		if(super.hit(circle)){
			spawnThing();
			return true;
		}
		return false;
	}

	
	@Override
	public boolean hit(Rectangle rectangle) {
		// TODO Auto-generated method stub
		return false;
	}

	
	private void spawnThing(){
		Item tempOre = ItemList.Ore.get();
		tempOre.setPosition(200,200);
		
		GameScreen.gameScreen.getGroundItems().add(tempOre);
	}
	
	
}
