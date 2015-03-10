package com.mygdx.game.menus;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameScreen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.items.Inventory;
import com.mygdx.game.entities.items.ItemSlot;
import com.mygdx.game.entities.logic.Renderable;
import com.mygdx.game.resources.ResourcesLoader;

public class CraftingMenu implements Renderable {

	
	public Array<ItemSlot> slots = new Array<ItemSlot>();
	public boolean open;
	public Sprite inventorySprite = new Sprite((Texture) ResourcesLoader.instance.getTexture("InventoryCrafting.png"));
	private int width = 512;
	private int height = 512;
	private Inventory playerInventory;
	//private boolean showtooltip;
	private OrthographicCamera camera;
	
	private String touchDown = "";
	public void setShowTooltip( boolean b){
		//showtooltip =b;
	}
	
	public void setPlayerInventory(Inventory i){
		playerInventory = i;
	}
	public 	CraftingMenu(){
		camera = GameScreen.gameScreen.getCamera();
			inventorySprite.setBounds(MyGdxGame.WIDTH/2 - width/2, MyGdxGame.HEIGTH /2 - height/2 , width, height);
			int x = 0;
			int y = 0;
			int i = 0;
			while(y < 3){
			while (x < 8){				
				ItemSlot is = new ItemSlot();
				is.bounds = new Rectangle(x*64+ inventorySprite.getX(),y*64 +inventorySprite.getY(),64,	64);
				//System.out.println(is.bounds);
				is.s = i+"";
				is.slotID = i+"";
				slots.add(is);
				i++;
				x++;
				}
				x = 0;
				y++;				
			}
			ItemSlot s1 = new ItemSlot();
			s1.bounds = new Rectangle(.5f*64+ inventorySprite.getX(), 3.5f*64+inventorySprite.getY(), 64,64);
			s1.s = "slot1";
			s1.slotID = "craft1";
			ItemSlot s2 = new ItemSlot();
			s2.bounds = new Rectangle(.5f*64+ inventorySprite.getX(), 5f*64+inventorySprite.getY(), 64,64);
			s2.s = "slot2";
			s2.slotID = "craft2";
			ItemSlot s3 = new ItemSlot();
			s3.bounds = new Rectangle(6.5f*64+ inventorySprite.getX(), 3.5f*64+inventorySprite.getY(), 64,64);
			s3.s = "slot3";
			s3.slotID = "craft3";
			ItemSlot s4 = new ItemSlot();
			s4.bounds = new Rectangle(6.5f*64+ inventorySprite.getX(), 5f*64+inventorySprite.getY(), 64,64);
			s4.s = "slot4";
			s4.slotID = "craft";
			slots.add(s1);
			slots.add(s2);
			slots.add(s3);
			slots.add(s4);
		
	}
	
	
	
	
	public void touchDown(Vector3 touchpos){
		touchpos.x = touchpos.x - camera.position.x + camera.viewportWidth/2;
		touchpos.y = touchpos.y - camera.position.y + camera.viewportHeight/2;
		//System.out.println("touched down at :" + touchpos);
		
		for (ItemSlot itemSlot : slots) {
			if(itemSlot.contains(touchpos)){
				touchDown  = itemSlot.slotID;
			}
		}
	}
	
	
	public void touchUp(Vector3 touchpos){
		touchpos.x = touchpos.x - camera.position.x + camera.viewportWidth/2;
		touchpos.y = touchpos.y - camera.position.y + camera.viewportHeight/2;
		for (ItemSlot itemSlot : slots) {
			if(itemSlot.contains(touchpos)){
				//System.out.println("touched up at: "+ itemSlot.slotID);
				playerInventory.swapItems(touchDown,itemSlot.slotID);
				//System.out.println("ending..");
				touchDown  = "";
			}
		}
	}
	
	
	
	
	@Override
	public void renderThis() {
		inventorySprite.draw(MyGdxGame.guiBatch);
		for(int i = 0; i < slots.size; i++){
			if(playerInventory.getItem(i+"")!= null){
			MyGdxGame.guiBatch.draw(playerInventory.getItem(i+"").texture,
					slots.get(i).bounds.x, slots.get(i).bounds.y, 64, 64);
			}
		}
		for (ItemSlot i : slots) {
			// sloppy math, just shifts the focus 
			if (i.bounds.contains(GameScreen.gameScreen.touchpos.x - camera.position.x + camera.viewportWidth/2
					, GameScreen.gameScreen.touchpos.y - camera.position.y + camera.viewportHeight/2)) {
				
				if(playerInventory.getItem(i.slotID + "") == null){ continue;}
				
				MyGdxGame.font.draw(MyGdxGame.guiBatch,
						playerInventory.getItem(i.slotID + "").toString(),
						GameScreen.gameScreen.touchpos.x - camera.position.x + camera.viewportWidth/2+ 10,
						GameScreen.gameScreen.touchpos.y - camera.position.y + camera.viewportHeight/2);
				
				break;
			}
		}
		
	}
}
