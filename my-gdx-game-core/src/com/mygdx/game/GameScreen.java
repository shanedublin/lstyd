package com.mygdx.game;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.buildings.Building;
import com.mygdx.game.entities.buildings.OreMine;
import com.mygdx.game.entities.collisions.CollisionDetector;
import com.mygdx.game.entities.items.Item;
import com.mygdx.game.entities.items.ItemList;
import com.mygdx.game.entities.player.Player;
import com.mygdx.game.input.GameInputHandler;
import com.mygdx.game.map.GameMap;
import com.mygdx.game.menus.CraftingMenu;
import com.mygdx.game.menus.PauseMenu;
import com.mygdx.game.pathfinding.Pathfinding;
import com.mygdx.game.resources.ResourcesLoader;




public class GameScreen implements Screen, InputProcessor {

	public enum GameMenuState {
		PLAYING, PAUSED, TECHTREEE, INVENTORY, STATPAGE
	}

	private static GameMenuState gms = GameMenuState.PLAYING;
	private static InputMultiplexer inputMultiplexer;
	public GameInputHandler gameInputHandler;

	public static int tileSize = 32;
	private int numTilesWide = 40;
	private int numTilesHeigh = 23;

	private static Stage stage;
	private Player player;

	private Array<Enemy> enemies;
	private Array<Item> groundItems = new Array<Item>();
	//private MapTile[][] gameMap;
	private OrthographicCamera camera;
	private CollisionDetector collisionDetector;
	private static PauseMenu pMenu;	
	private static CraftingMenu craftingMenu ;	
	public Vector3 touchpos = new Vector3(0,0,0);	
	public static GameScreen gameScreen;
	private GameMap gameMap = new GameMap();
	
	
	public GameScreen() {
		System.out.println("test1");
		gameScreen = this;
		System.out.println("test2");
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		camera.translate(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics
				.getHeight() / 2));
		camera.update();

		stage = new Stage(new ScreenViewport());
		pMenu = new PauseMenu();		
		stage.addActor(pMenu);
		player = new Player();
		player.setPosition(50, 50);
		player.setAttackRange(new Circle(player.getPosition(), 20));
		player.setWidth(tileSize * 1);
		player.setHeight(tileSize * 1);
		
		
		//gameMap = Generate.generateMap(numTilesWide, numTilesHeigh);
		enemies = new Array<Enemy>();

		collisionDetector = new CollisionDetector();
		collisionDetector.setBuildings(gameMap.getMap());
		// collisionDetector.setPlayerProjectiles(player);
		// collisionDetector.setEnemies(enemies);
		collisionDetector.setPlayer(player);
		collisionDetector.setGroundItems(groundItems);
		collisionDetector.setGameMap(gameMap);
		
		Item testItem1 = ItemList.Food.get();
		testItem1.setPosition(120,160);
		Item testItem2 = ItemList.Ore.get();
		testItem2.setPosition(160,120);
		groundItems.add(testItem1);
		groundItems.add(testItem2);
		inputMultiplexer = new InputMultiplexer();
		// gameInputHandler = new GameInputHandler(camera);
		inputMultiplexer.addProcessor(this);

		inputMultiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(inputMultiplexer);
		craftingMenu = new CraftingMenu();
		craftingMenu.open = false;
		craftingMenu.setPlayerInventory(player.inventory);
		

	}

	@Override
	public void show() {}

	public void gameLogic() {

		player.physicsMove();
		Iterator<Enemy> enemyItor = enemies.iterator();
		while (enemyItor.hasNext()) {
			Enemy e = enemyItor.next();
			e.MoveEnemy();
			if (!e.isAlive()) {
				enemyItor.remove();
			}
		}
		
		
		
		collisionDetector.handleCollisions();
	}

	@Override
	public void render(float delta) {
		checkInput();
		gameLogic();

		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		
		MyGdxGame.spriteBatch.begin();
		gameMap.renderThis();
		renderGroundItems();
		player.renderThis();
		
		//renderMouseSelection();
		MyGdxGame.spriteBatch.end();
		MyGdxGame.guiBatch.begin();
		renderInventory();
		renderText();
		MyGdxGame.guiBatch.end();
		
		MyGdxGame.sr.setProjectionMatrix(camera.combined);
		MyGdxGame.spriteBatch.setProjectionMatrix(camera.combined);
		MyGdxGame.sr.begin(ShapeType.Line);
		MyGdxGame.sr.setColor(Color.RED);
//		MyGdxGame.sr.rect(player.getBounds().x, player.getBounds().y,
//				player.getBounds().width, player.getBounds().height);
		// drawEnemies

		for (Entity e : enemies) {
			MyGdxGame.sr.rect(e.getBounds().x, e.getBounds().y, e.getWidth(),
					e.getHeight());
		}
		renderHitBounds();

		MyGdxGame.sr.end();
		

		switch (gms) {
		case PAUSED:			
			stage.act();
			//stage.getActors().items[0].act(Gdx.graphics.getDeltaTime());
			stage.draw();
			//stage.getActors().items[0].draw(batch, parentAlpha);
			break;

		case INVENTORY:
			//stage.act();
			//stage.draw();
			//pMenu.setVisible(false);
			break;
		case PLAYING:
			//pMenu.setVisible(false);
			break;
		default:
			//pMenu.setVisible(false);
			break;
		}
	}
	public void renderHitBounds(){
		
		for (Entity e : groundItems) {
			MyGdxGame.sr.rect(e.getBounds().x, e.getBounds().y, e.getWidth(),
					e.getHeight());
		}
	}
	
	public void renderMouseSelection(){
		MyGdxGame.spriteBatch.draw(ResourcesLoader.instance.getTexture("InventorySelector.png"), 
				(int)((int)touchpos.x / tileSize) * tileSize, (int)((int)touchpos.y / tileSize) * tileSize, tileSize, tileSize);
	}
	public void renderGroundItems(){
		for (Item i : groundItems) {
			i.renderThis();
		}
	}
	
	public void renderInventory() {
		if (craftingMenu.open) {
			craftingMenu.renderThis();
		}
	}
	
	public void renderText(){
		// just rendes fps
				MyGdxGame.font.draw(MyGdxGame.guiBatch,
						"" + Gdx.graphics.getFramesPerSecond(), 0,
						Gdx.graphics.getHeight());
				String s = String.format(" Velocity X: %6.2f Y: %6.2f ",
						(player.getVelocity().x / player.getFriction()),
						(player.getVelocity().y / player.getFriction()));
				MyGdxGame.font.draw(MyGdxGame.guiBatch, s,
						Gdx.graphics.getWidth() - s.length() * 6,
						Gdx.graphics.getHeight() - 20);
				// game.font.draw(game.batch, ""+player.getAttackRange(),
				// Gdx.graphics.getWidth()-100, Gdx.graphics.getHeight()-40);
		
	}
	

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	/**
	 * all the default inputs for the game
	 */
	void checkInput() {
		touchpos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(touchpos);

		int x = 0;
		int y = 0;
		if (Gdx.input.isKeyPressed(Keys.UP)) {y++;}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {y--;}
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {x--;}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {x++;}
		player.setMoveForceVector(new Vector2(x, y));

		if (Gdx.input.isKeyPressed(Keys.COMMA)) {
			// move camera up
			camera.translate(new Vector2(0, 1));
		}

		if (Gdx.input.isKeyPressed(Keys.O)) {
			// move camera down
			camera.translate(new Vector2(0, -1));
		}
		if (Gdx.input.isKeyPressed(Keys.E)) {
			// move camera right
			camera.translate(new Vector2(1, 0));
		}

		if (Gdx.input.isKeyPressed(Keys.A)) {
			// move camera left
			camera.translate(new Vector2(-1, 0));
		}

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub

		if (keycode == Keys.S) {
		//	System.out.println("Spawing enemy");
			//SpawnEnemy();
		}
		if (keycode == Keys.ENTER) {
			
		}

		if (keycode == Keys.BACKSPACE) {
			// show pause menu
		}
		if (keycode == Keys.ESCAPE) {
			if(GameScreen.gms == GameMenuState.PLAYING){
				System.out.println("playing");
				setGms(GameMenuState.PAUSED);				
			}else{
				//System.out.println("playing now..");
				setGms(GameMenuState.PLAYING);
			}
		}
		if(keycode == Keys.PERIOD){
			setGms(GameMenuState.INVENTORY);
		}
		

		return false;
	}

	public void SpawnEnemy() {
		Enemy e = new Enemy(new Vector2(1, 1));
		e.setWidth(tileSize* 0.5f);
		e.setHeight(tileSize * 0.5f);
		
		
		e.setPosition(touchpos.x, touchpos.y);
		e.SetPath(Pathfinding.aStarPath(gameMap.getMap(), gameMap.getMap()[(int) touchpos.x
				/ tileSize][(int) touchpos.y / tileSize], gameMap.getMap()[1][1]));

		enemies.add(e);
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {


		// System.out.println("touched at" + touchpos);

		if(gms == GameMenuState.PLAYING){			
		if (button == 0) {
			player.attack(touchpos);
			player.attackBuildings();
		}
		if (button == 1) {
			//removeBlock(touchpos);
		}
		}
		//System.out.println(touchpos);
		if(craftingMenu.open){			
		craftingMenu.touchDown(touchpos);
		}
		
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		
		if(craftingMenu.open){
			craftingMenu.touchUp(touchpos);
		}
			
		
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
	
		if(craftingMenu.open){
			if(craftingMenu.inventorySprite.getBoundingRectangle().contains(screenX,screenY)){			
				craftingMenu.setShowTooltip(true);			
			return false;
			}
			craftingMenu.setShowTooltip(true);
			return false;
		}
		craftingMenu.setShowTooltip(true);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public void addBlock(Vector3 touchpos) {

		int x, xSquare;
		int y, ySquare;
		x = (int) touchpos.x;
		y = (int) touchpos.y;
		if (x < 0) {
			x -= tileSize;
			//System.out.println("not a valid tile location: x less than 0");
			return;
		}
		if (y < 0) {
			y -= tileSize;
			//System.out.println("not a valid tile location: y less than 0");
			return;
		}

		x = x / tileSize;
		y = y / tileSize;
		//System.out.println("x:" + x + " y:" + y);
		xSquare = x;
		ySquare = y;
		if (gameMap.getMap()[xSquare][ySquare].isOccupied()) {
			//System.out.println("Something is in this square already");
			return;
		}
		x = (x * tileSize) + (tileSize / 2);
		y = (y * tileSize) + (tileSize / 2);

		Building tile = new OreMine(xSquare, ySquare);
		
		tile.setWidth(tileSize);
		tile.setHeight(tileSize);
		tile.setPosition(x, y);
//		System.out.println(tile.getPosition() + " BX: " + tile.getBounds().x
//				+ " BY: " + tile.getBounds().y);

		gameMap.getMap()[xSquare][ySquare].occupied = true;
		gameMap.getMap()[xSquare][ySquare].setBuilding(tile);

	}

	public void removeBlock(Vector3 touchpos) {

		int x, xSquare;
		int y, ySquare;
		x = (int) touchpos.x;
		y = (int) touchpos.y;
		if (x < 0) {
			x -= tileSize;
			//System.out.println("not a valid tile location: x less than 0");
			return;
		}
		if (y < 0) {
			y -= tileSize;
			//System.out.println("not a valid tile location: y less than 0");
			return;
		}
		x = x / tileSize;
		y = y / tileSize;
		//System.out.println("x:" + x + " y:" + y);
		xSquare = x;
		ySquare = y;

		x = (x * tileSize) + (tileSize / 2);
		y = (y * tileSize) + (tileSize / 2);

		gameMap.getMap()[xSquare][ySquare].occupied = false;
		gameMap.getMap()[xSquare][ySquare].setBuilding(null);

	}

	public static GameMenuState getGms() {
		return gms;
	}
	

	public  void setGms(GameMenuState gms) {
		GameScreen.gms = gms;
		switch (gms) {
		case PAUSED:
			//Gdx.input.setInputProcessor(stage);
			pMenu.setVisible(true);
			craftingMenu.open = false;
			break;
			
		case INVENTORY:
			//Gdx.input.setInputProcessor(inputMultiplexer);
			//im2.setVisible(true);
			//craftingMenu.loadSlots(player);
			pMenu.setVisible(false);
			craftingMenu.open = true;
			break;

		default:
			//Gdx.input.setInputProcessor(inputMultiplexer);
			pMenu.setVisible(false);
			craftingMenu.open =false;
			break;
		}

	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
		
	}

	public Player getPlayer() {
		return player;
	}

	public Array<Item> getGroundItems() {
		return groundItems;
	}
	
	
	

}
