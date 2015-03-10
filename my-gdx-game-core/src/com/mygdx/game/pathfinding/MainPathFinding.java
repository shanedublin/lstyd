package com.mygdx.game.pathfinding;

@Deprecated
public class MainPathFinding {

	
	
	public static int TileSize = 6;
	public static int FinalX = 3;
	public static int FinalY = 4;
	
	
	public static void main(String[] args) {
		
		Tile[][] map = new Tile[TileSize][TileSize];
		
		
		
		int x  =0;
		int y = 0;
		
		while(x < TileSize){
			while(y < TileSize){
				map[x][y] = new Tile(x,y);
				map[x][y].setDistGoal(Math.abs(x - FinalX) + Math.abs(y - FinalY));
				y ++;
			}
			y = 0;
			x ++;
			//System.out.println(x);
		}
		
		
		System.out.println("Filling adjacent node...");
		System.out.println(map[0].length);
		x = 0;
		y = 0;
		while(x < TileSize){
			while(y < TileSize){
				//System.out.println(y);
				try {map[x][y].adjNodes.add(map[x][y+1]);				
				} catch (ArrayIndexOutOfBoundsException e) {
					//e.printStackTrace();
				}try {map[x][y].adjNodes.add(map[x][y-1]);				
				} catch (ArrayIndexOutOfBoundsException e) {	
					//e.printStackTrace();
				}try {map[x][y].adjNodes.add(map[x+1][y]);				
				} catch (ArrayIndexOutOfBoundsException e) {
					//e.printStackTrace();
				}try {map[x][y].adjNodes.add(map[x-1][y]);				
				} catch (ArrayIndexOutOfBoundsException e) {
					//e.printStackTrace();
				}			
				y ++;
			}
			y =0;
			x ++;
			
		}
		System.out.println("Starting to find path...");
		
		//Pathfinding.findPath(map, map[2][0], map[FinalX][FinalY] );
		
	}
}
