package com.mygdx.game.entities.buildings;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.resources.ResourcesLoader;

public class Wall extends Building {

	public Wall(int x, int y) {
		super(x, y);
		texture = ResourcesLoader.instance.getTexture("Wall.png");
	}
	
	@Override
	public void damage(int damage) {
		super.damage(damage);
	}

	@Override
	public boolean hit(Circle circle) {
		return super.hit(circle);	
	}

	@Override
	public boolean hit(Rectangle rectangle) {
		return false;
	}

}
