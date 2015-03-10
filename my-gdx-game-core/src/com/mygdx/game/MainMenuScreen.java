package com.mygdx.game;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.input.MyChangeListeners;
import com.mygdx.game.menus.Options;
import com.mygdx.game.menus.UIObjects;

public class MainMenuScreen implements Screen {

	public enum MainMenuState{
		MAIN, OPTIONS
	}
	public static MainMenuState mms = MainMenuState.MAIN;
	
	//final MyGdxGame game;
	OrthographicCamera cam;
	Stage stage;
	
	final TextButton start;
	final TextButton exit;
	final TextButton options;
	final TextButton refresh;
	private Options optionMenu;
	private Table mainTable;
	
	
//	Skin skin;
	//TextButtonStyle tbStyle;
	
	public  MainMenuScreen() {
		// TODO Auto-generated constructor stub
		//this.game = game;
		stage = new Stage(new ScreenViewport());
		
		optionMenu = new Options();
		
		Gdx.input.setInputProcessor(stage);
		
		mainTable = new Table();
		mainTable.setFillParent(true);
		mainTable.debug();
		stage.addActor(mainTable);
		stage.addActor(optionMenu);
		//stage.getActors();
		
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//tbStyle = new TextButtonStyle(up, down, checked, font);
		start = new TextButton("Start", UIObjects.skin);
		exit = new TextButton("Exit", UIObjects.skin);
		options = new TextButton("Options", UIObjects.skin);
		refresh = new TextButton("Refresh",UIObjects.skin);
		
		refresh.addListener(MyChangeListeners.RELOADMAINMENU);		
		start.addListener(MyChangeListeners.STARTGAME);
		exit.addListener(MyChangeListeners.EXITGAME);
		options.addListener(MyChangeListeners.MAINMENUOPTIONS);
		
		mainTable.add(start).width(100).pad(10);
		mainTable.row();
		mainTable.add(options).width(100).pad(10);
		mainTable.row();
		mainTable.add(refresh).width(100).pad(10);
		mainTable.row();
		mainTable.add(exit).width(100).pad(10);
		
		
	}
	
	
	@Override
	public void show() {}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0,0,1);
		 Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		 
		 switch (mms) {
		case MAIN:
			mainTable.setVisible(true);
			//mainTable.setTouchable(true);
			optionMenu.setVisible(false);
			//optionMenu.
			
			break;
		case OPTIONS:
			mainTable.setVisible(false);
			optionMenu.setVisible(true);
			break;

		default:
			break;
		}
		 stage.act(delta);
		 stage.draw();	
	}
	
	

	@Override
	public void resize(int width, int height) {
			 stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {}
	@Override
	public void resume() {}
	@Override
	public void hide() {}
	@Override
	public void dispose() {		
		stage.dispose();		
	}

	
}
