package com.mygdx.game.resources;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class ResourcesLoader {

	
	
	public static final String PLAYER = "robot.png";
	public static final String LONGGRASS = "LongGrass2.png";
	public static final String OREMINE = "OreMine.png";
	
	
	public static final ResourcesLoader instance = new ResourcesLoader();
	
	private AssetManager assetManager = new AssetManager();
	
	public boolean loadAssets(){
		return assetManager.update();
	}
	public void loadAllAssets(){
		assetManager.finishLoading();
	}
	public Texture getTexture(String textureName){
		return assetManager.get(textureName, Texture.class);
	}
	
	private  ResourcesLoader(){
		assetManager.load("Item.png",Texture.class );
		assetManager.load("Inventory.png",Texture.class);
		assetManager.load("InventorySelector.png",Texture.class);
		assetManager.load("InventoryCrafting.png",Texture.class);
		assetManager.load("Food.png", Texture.class);
		assetManager.load("Helmet.png", Texture.class);
		assetManager.load("Ore.png", Texture.class);
		assetManager.load("LongGrass.png", Texture.class);
		assetManager.load("LongGrass2.png", Texture.class);
		assetManager.load("Sword.png", Texture.class);
		assetManager.load("Axe.png", Texture.class);
		assetManager.load("robot.png", Texture.class);
		assetManager.load("OreMine.png",Texture.class);
		assetManager.load("Tree.png",Texture.class);
		assetManager.load("Wall.png",Texture.class);
		
		System.out.println("loading assets");
		assetManager.finishLoading();
		System.out.println("finished Loading assets");
	}
	
}
