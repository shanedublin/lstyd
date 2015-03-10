package com.mygdx.game.entities.logic;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public interface Hittable {

	public void damage(int damage);
	public boolean hit(Circle circle);
	public boolean hit(Rectangle rectangle);
}
