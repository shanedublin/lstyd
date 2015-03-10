package com.mygdx.game.entities.logic;

public interface Renderable  {

	/**
	 * must be called in between a batch .begin and batch.end call
	 * renders everything for the class
	 */
	public abstract void renderThis();
}
