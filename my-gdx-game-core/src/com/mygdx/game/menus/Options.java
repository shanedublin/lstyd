package com.mygdx.game.menus;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.input.MyChangeListeners;

public class Options extends Table {

	
	
//	private Table table;
	
	private final Button back;
	
	public Options() {
		
		super();
//		table = new Table();
		//table.setFillParent(true);
		//table.debug();
		this.setFillParent(true);
		this.debug();
		back = new TextButton("Back", UIObjects.skin);
		back.addListener(MyChangeListeners.MAINMENUBACK);
		
		this.add(back).width(100).pad(10);
		
	}
	
}
