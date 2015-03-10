package com.mygdx.game.menus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.resources.ResourcesLoader;

public class InventoryMenu {

	public int xSelection = -1;
	public int ySelection = -1;
	private int width = 512;
	private int height = 512;
	
	public boolean open;
	
	
	public Sprite inventorySprite = new Sprite((Texture) ResourcesLoader.instance.getTexture("InventoryCrafting.png"));
	public Sprite inventorySelector = new Sprite((Texture) ResourcesLoader.instance.getTexture("InventorySeloctor.png"));
	
	
	public InventoryMenu(){
		//inventorySprite.setSize(512, 512);
		inventorySprite.setBounds(MyGdxGame.WIDTH/2 - width/2, MyGdxGame.HEIGTH /2 - height/2 , width, height);
		inventorySelector.setBounds(MyGdxGame.WIDTH/2 - width/2, MyGdxGame.HEIGTH /2 - height/2 , width/8, height/8);
	}


	public void setSelected(int screenX, int screenY) {
		screenX -= inventorySprite.getX();
		screenY -= inventorySprite.getY();
		
		xSelection = screenX / 64;
		xSelection = MathUtils.clamp(xSelection, 0, 7);
		ySelection = screenY / 64;
		ySelection = MathUtils.clamp(ySelection, 0, 6);
	}
	
	
	
	
	
	
	
}
