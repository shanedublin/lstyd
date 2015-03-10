package com.mygdx.game.map;

import com.mygdx.game.GameScreen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.MapTile;
import com.mygdx.game.entities.logic.Renderable;
import com.mygdx.game.generation.Generate;

public class GameMap implements Renderable {

	private int width = 50;
	private int height = 25;
	private MapTile[][] map = Generate.generateMap(width, height);
	
	
	
	
	public MapTile[][] getMap() {
		return map;
	}
	public void setMap(MapTile[][] map) {
		this.map = map;
	}



	@Override
	public void renderThis() {
	
		
		for(int y =0; y < height; y++){
			for(int x = 0; x <  width; x++){
				MyGdxGame.spriteBatch.draw(
						map[x][y].getBackgroundTexture(),
						map[x][y].getX(),
						map[x][y].getY(), 
						GameScreen.tileSize,
						GameScreen.tileSize);				
			}
		}
		
		for(int y =0; y < height; y++){
			for(int x = 0; x <  width; x++){
				if(map[x][y].getBuilding() != null){					
				MyGdxGame.spriteBatch.draw(
						map[x][y].getForGroundTexture(),
						map[x][y].getX(),
						map[x][y].getY(), 
						GameScreen.tileSize,
						GameScreen.tileSize);
				}
				
			}
		}
		
	}

	
	
}
