package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.menus.UIObjects;
import com.mygdx.game.resources.ResourcesLoader;

public  class MyGdxGame extends Game {
	public static final int WIDTH = 1280;
	public static final int HEIGTH = 720;
	
	public static  SpriteBatch spriteBatch;
	public static SpriteBatch guiBatch;
	public static ShapeRenderer sr;
	
	public static BitmapFont font;
	// static reference to the game,  should probably have this set up somewhere else but whatever..
	public static MyGdxGame myGDXGame;
	@Override
	public void create () {
		ResourcesLoader.instance.loadAllAssets();
		spriteBatch = new SpriteBatch();
		guiBatch = new SpriteBatch();
		sr = new ShapeRenderer();
	
		font = new BitmapFont();
		UIObjects.initiate();
		myGDXGame = this;
	
		this.setScreen(new GameScreen());
	}

	@Override
	public void render () {
		super.render();
	}
	
	public void dispose(){
		guiBatch.dispose();
		spriteBatch.dispose();
		//img.dispose();
		
	}
}
