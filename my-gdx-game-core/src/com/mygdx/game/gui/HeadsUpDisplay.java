package com.mygdx.game.gui;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.items.ItemSlot;
import com.mygdx.game.entities.logic.Renderable;
import com.mygdx.game.entities.player.Player;
import com.mygdx.game.entities.player.PlayerItems;
import com.mygdx.game.resources.ResourcesLoader;

public class HeadsUpDisplay implements Renderable{

	
	
	ItemSlot rightHand;
	ItemSlot leftHand;
	
	Player p;
	
	HeadsUpDisplay(Player p){
		this.p = p;
		
		rightHand.bounds = new Rectangle(0, 0, 64, 64);
		rightHand.slotID = PlayerItems.RIGHTHAND;
		
	}
	
	
	
	@Override
	public void renderThis() {
		// render player health, 
		// right equipped
		// ammo , magic, energy w/e 
		// left equipped 
		MyGdxGame.guiBatch.draw(ResourcesLoader.instance.getTexture("InventoryCrafting.png"),
				rightHand.bounds.x, rightHand.bounds.y);
		if(p.inventory.getItem(rightHand.slotID) != null){			
		MyGdxGame.guiBatch.draw(p.inventory.getItem(rightHand.slotID).texture,
				rightHand.bounds.x, rightHand.bounds.y);
		}
		
	}
	
/// this class displays important info like health  and items in the hot bar

}
