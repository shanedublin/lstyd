package com.mygdx.game.entities.items;

import java.util.HashMap;

import com.mygdx.game.entities.player.PlayerItems;

public class Inventory {	
	
	HashMap<String , Item> items = new HashMap<String, Item>(32);	
	
	public Item getItem(String item){
		return items.get(item);
	}
	public boolean setItem(String slotname, Item item){		
		items.put(slotname, item);		
		return false;
	}
	
	/**
	 * will atempt to add slot1 to slot 2 if the items are the same
	 * @param slot1name
	 * @param slot2name
	 */
	public void swapItems(String slot1name, String slot2name){
		if(slot1name.equals(slot2name)){
			return;
		}
		System.out.println("swapped: " + slot1name +" and "+ slot2name);
		if(items.get(slot1name) == null || items.get(slot2name) == null){
			Item temp = items.get(slot1name);
			items.put(slot1name, items.get(slot2name));
			items.put(slot2name, temp);
			return;
		}
		if(items.get(slot1name).name.equals(items.get(slot2name).name)){
			items.get(slot2name).quantity += items.get(slot1name).quantity;
			items.put(slot1name, null);
			return;
		}		
		Item temp = items.get(slot1name);
		items.put(slot1name, items.get(slot2name));
		items.put(slot2name, temp);
		

	}
	/**
	 * returns true if the item is sucessfully added.
	 * will try to stack items currently no stack cap is used
	 * @param i
	 * @return
	 */
	public boolean addItem(Item i) {
		// find 1st open item in list
		// add item to this list
		// if item is added return true 
		// else return false
		for(int j =0; j < 24;j++){
			if(items.get(j+"") == null ){
				items.put(j+"", i);
				return true;
			}
			if(i.stackable && items.get(j+"").name.equals(i.name)){
				items.get(j+"").quantity += i.quantity;
				return true;
			}
		}
		
		
		return false;
	}
	
	
	
	
	
}
