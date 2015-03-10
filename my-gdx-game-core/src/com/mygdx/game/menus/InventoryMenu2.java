package com.mygdx.game.menus;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.input.MyChangeListeners;

public class InventoryMenu2 extends Table {

	
	
	
	public InventoryMenu2() {
	super();
	Table itemTable = new Table();
	Table tabTable = new Table();
	final Table armorTable = new Table();
	final Table descriptionTable = new Table();
	
	final Table fullCraftTable = new Table();
	
	

	
	
	//toolScrollPane.layout();
	final Container<Actor> c = new Container<Actor>();
	//c.setFillParent(true);
	this.setVisible(false);
	this.setFillParent(true);
	
	
	
	TextButton description = new TextButton("Description", UIObjects.skin);
	TextButton crafting = new TextButton("Crafting", UIObjects.skin);
	TextButton armor = new TextButton("Armor", UIObjects.skin);
	armor.addListener(new  ChangeListener() {		
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			c.setActor(armorTable);			
		}
	});
	crafting.addListener(new  ChangeListener() {		
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			c.setActor(fullCraftTable);			
		}
	});
	description.addListener(new  ChangeListener() {		
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			c.setActor(descriptionTable);			
		}
	});
	
	
	ButtonGroup<Button> selectionTabs = new ButtonGroup<Button>(description,crafting,armor);
	
	selectionTabs.setMaxCheckCount(1);
	selectionTabs.setMinCheckCount(1);
	//tabTable.setFillParent(true);
	//tabTable.setVisible(true);
	tabTable.add(description).width(64*3);/*.width(tabTable.getWidth()/3).height(32);*/
	tabTable.add(crafting).width(64*3);/*.width(tabTable.getWidth()/3).height(32);*/
	tabTable.add(armor).width(64*3);/*.width(tabTable.getWidth()/3).height(32);*/
	this.add(tabTable);
	this.row();
	
	//armorTable.add(new TextButton("hue", UIObjects.skin)).width(64*9).height(64*3);
	TextButton head = new TextButton("head", UIObjects.skin);
	TextButton chest = new TextButton("chest", UIObjects.skin);
	TextButton legs = new TextButton("legs", UIObjects.skin);
	TextButton feet = new TextButton("feet", UIObjects.skin);
	TextButton hand1 = new TextButton("hand1", UIObjects.skin);
	TextButton hand2 = new TextButton("hand2", UIObjects.skin);
	TextButton neck = new TextButton("neck", UIObjects.skin);
	TextButton ring = new TextButton("ring", UIObjects.skin);
	TextButton gloves = new TextButton("gloves", UIObjects.skin);

	
	armorTable.add(ring).width(64).height(64);
	armorTable.add(head).width(64).height(64);
	armorTable.add(neck).width(64).height(64);
	armorTable.row();
	armorTable.add(hand1).width(64).height(64);
	armorTable.add(chest).width(64).height(64);
	armorTable.add(hand2).width(64).height(64);
	armorTable.row();
	armorTable.add(feet).width(64).height(64);
	armorTable.add(legs).width(64).height(64);
	armorTable.add(gloves).width(64).height(64);
	
	
	
	descriptionTable.add(new TextButton("item Description...", UIObjects.skin));
	
	
	TextButton combineButton1 = new TextButton("Combine1",UIObjects.skin);
	TextButton combineButton2 = new TextButton("Combine2",UIObjects.skin);
	TextButton result2 = new TextButton("result2", UIObjects.skin);
	TextButton result1 = new TextButton("result1", UIObjects.skin);
	TextButton craftButton= new TextButton("Craft", UIObjects.skin);
	TextButton labelButton = new TextButton("label Button", UIObjects.skin);
	
	
	
	Table cTable = new  Table();
	//cTable.setFillParent(true);
	Table dTable = new Table();
	//dTable.setFillParent(true);
	Table rTable = new Table();
	//rTable.setFillParent(true);
	cTable.add(combineButton1).width(64).height(64).pad(10);
	cTable.row();
	cTable.add(combineButton2).width(64).height(64).pad(10);
	
	dTable.add(labelButton).width(64*5).height(64*2);
	dTable.row();
	dTable.add(craftButton).width(64*4).height(32).pad(28, 0, 4, 0);
	
	rTable.add(result1).width(64).height(64).pad(10);
	rTable.row();
	rTable.add(result2).width(64).height(64).pad(10);
	


	
	fullCraftTable.add(cTable).height(64*3).width(64*2);	
	fullCraftTable.add(dTable).height(64*3).width(64*5);
	fullCraftTable.add(rTable).height(64*3).width(64*2);
//	fullCraftTable.add(combineButton1).width(64).height(64).pad(10);
//	fullCraftTable.add(labelButton).width(64*4).height(64*2);
//	fullCraftTable.add(result1).width(64).height(64).pad(10);
//	fullCraftTable.row();
//	fullCraftTable.add(combineButton2).width(64).height(64);
//	fullCraftTable.add(craftButton).width(64).height(32).pad(28, 0, 4, 0);
//	fullCraftTable.add(result2).width(64).height(64);
//	
	
	//fullCraftTable.debug();
	//fullCraftTable.setFillParent(true);
	
	
	//ScrollPane  foodScrollPane = new ScrollPane(toolList);
	
	

	//fullCraftTable.add(new Container<Actor>()).width(64*6).height(64*3);
	//fullCraftTable.add();
	
	c.setActor(fullCraftTable);
	this.add(c).width(64*9).height(64*3);
	this.row();
	
	
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 9; j++) {
			TextButton tb = new TextButton("test", UIObjects.skin);
			tb.addListener(MyChangeListeners.RESUMEGAME);
			tb.setSize(64, 64);
			itemTable.add(tb).width(64).height(64);
			
		}
		itemTable.row();
	}
	
	
	this.add(itemTable);
	//this.row();
	
	//this.add(toolScrollPane).width(64*9).height(64*3);	
	//this.debug();
	
	//this.debug();
	fullCraftTable.debug();
	
	
		
	}
	
	
	
	
	
}
