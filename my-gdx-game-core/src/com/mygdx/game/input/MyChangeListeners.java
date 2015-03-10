package com.mygdx.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.mygdx.game.GameScreen;
import com.mygdx.game.MainMenuScreen;
import com.mygdx.game.GameScreen.GameMenuState;
import com.mygdx.game.MainMenuScreen.MainMenuState;
import com.mygdx.game.MyGdxGame;

public abstract class  MyChangeListeners extends ChangeListener{

	public static final MyChangeListeners STARTGAME = new MyChangeListeners() {
		public void changed(ChangeEvent event, Actor actor) {
			System.out.println("test");
			MyGdxGame.myGDXGame.setScreen(new GameScreen());	
		}	
	};
	public static final MyChangeListeners EXITGAME  = new MyChangeListeners() {		
		public void changed(ChangeEvent event, Actor actor) {		
			Gdx.app.exit();	
		}
	};	
	public static final MyChangeListeners RELOADMAINMENU = new MyChangeListeners() {
		public void changed(ChangeEvent event, Actor actor) {
			MyGdxGame.myGDXGame.setScreen(new MainMenuScreen());			
		}
	};	
	public static final MyChangeListeners MAINMENUBACK = new MyChangeListeners() {		
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			MainMenuScreen.mms = MainMenuState.MAIN;	
			if(actor instanceof TextButton){
				((TextButton)actor).setChecked(false);
			}
		}
	};
	public static final MyChangeListeners MAINMENUOPTIONS = new MyChangeListeners() {		
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			MainMenuScreen.mms = MainMenuState.OPTIONS;
			if(actor instanceof TextButton){
				((TextButton)actor).setChecked(false);
			}
		}
	};
	public static final MyChangeListeners RESUMEGAME = new MyChangeListeners() {	
		
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			GameScreen.gameScreen.setGms(GameMenuState.PLAYING);
			if(actor instanceof TextButton){
				((TextButton)actor).setChecked(false);
			}
		}
	};
	
	
}
