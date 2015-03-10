package com.mygdx.game.menus;



import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.input.MyChangeListeners;

public class PauseMenu extends Table{

	public boolean showing = false;
	final TextButton resume;
	final TextButton options;
	final TextButton exit;
	
	
	public PauseMenu() {
		super();

		exit = new TextButton("Quit Game", UIObjects.skin);
		options = new TextButton("Options", UIObjects.skin);
		resume = new TextButton("Resume",UIObjects.skin);
		
		
		exit.addListener(MyChangeListeners.EXITGAME);
		resume.addListener(MyChangeListeners.RESUMEGAME);
		
		this.setFillParent(true);
		this.debug();
		
		this.add(options).width(100).pad(10);
		this.row();
		this.add(resume).width(100).pad(10);
		this.row();
		this.add(exit).width(100).pad(10);
		this.setVisible(false);
		
		
	}
	
	
	
	
	
}
