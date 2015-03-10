package com.mygdx.game.entities.buildings;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Tree extends Building {

	public Tree(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void damage(int damage) {
		super.damage(damage);
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hit(Circle circle) {
		return super.hit(circle);
	}

	@Override
	public boolean hit(Rectangle rectangle) {
		// TODO Auto-generated method stub
		return false;
	}

}
