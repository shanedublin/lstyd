package com.mygdx.game.generation;

import com.mygdx.game.GameScreen;
import com.mygdx.game.entities.MapTile;
import com.mygdx.game.entities.buildings.Building;
import com.mygdx.game.entities.buildings.OreMine;
import com.mygdx.game.entities.buildings.Wall;

public class Generate {

	
	public static MapTile[][] generateMap(int mapWidth, int mapHeight){
		
		
		MapTile[][] mapTiles = new MapTile[mapWidth][mapHeight];
		
		int x = 0;
		int y = 0;
		
		while (x < mapWidth){
			while(y < mapHeight){
				mapTiles[x][y] = new MapTile(x,y);
			
				if(x == 0 || x == mapWidth -1 || y ==0 || y == mapHeight -1){
					Building b = new Wall(x, y);					
					b.setWidth(GameScreen.tileSize);
					b.setHeight(GameScreen.tileSize);
					b.setPosition((x * GameScreen.tileSize) + (GameScreen.tileSize / 2)
							, (y * GameScreen.tileSize) + (GameScreen.tileSize / 2));					
					mapTiles[x][y].setBuilding(b);
					mapTiles[x][y].setOccupied(true);
				}
				
				if(x == 10 &&  y ==5 ){
					Building b = new OreMine(x, y);					
					b.setWidth(GameScreen.tileSize);
					b.setHeight(GameScreen.tileSize);
					b.setPosition((x * GameScreen.tileSize) + (GameScreen.tileSize / 2)
							, (y * GameScreen.tileSize) + (GameScreen.tileSize / 2));					
					mapTiles[x][y].setBuilding(b);
					mapTiles[x][y].setOccupied(true);
				}
				
				if(x == 20 &&  y ==5 ){
					Building b = new Wall(x, y);					
					b.setWidth(GameScreen.tileSize);
					b.setHeight(GameScreen.tileSize);
					b.setPosition((x * GameScreen.tileSize) + (GameScreen.tileSize / 2)
							, (y * GameScreen.tileSize) + (GameScreen.tileSize / 2));					
					mapTiles[x][y].setBuilding(b);
					mapTiles[x][y].setOccupied(true);
				}
				
				mapTiles[x][y].setX(x* GameScreen.tileSize);
				mapTiles[x][y].setY(y* GameScreen.tileSize);
				y ++;
				
			}
			y = 0;
			x ++;
			
		}
		
		
		
		return mapTiles;
		
		
	}
}
