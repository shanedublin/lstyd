package com.mygdx.game.entities.collisions;

import java.util.Iterator;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.MapTile;
import com.mygdx.game.entities.Projectile;
import com.mygdx.game.entities.buildings.Building;
import com.mygdx.game.entities.items.Item;
import com.mygdx.game.entities.player.Player;
import com.mygdx.game.map.GameMap;

public class CollisionDetector {
	
	private Array<Enemy> enemies;
	private MapTile[][] buildings;
	private Array<Projectile> playerProjectiles;
	private Array<Projectile> enemyProjectiles;
	private Player player;
	private Array<Item> groundItems;
	private GameMap gameMap;
	
	
	
	
	
	public GameMap getGameMap() {
		return gameMap;
	}

	public void setGameMap(GameMap gameMap) {
		this.gameMap = gameMap;
	}

	public Array<Item> getGroundItems() {
		return groundItems;
	}

	public void setGroundItems(Array<Item> groundItems) {
		this.groundItems = groundItems;
	}

	public MapTile[][] getBuildings() {
		return buildings;
	}

	public void setBuildings(MapTile[][] buildings) {
		this.buildings = buildings;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Array<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(Array<Enemy> enemies) {
		this.enemies = enemies;
	}


	public Array<Projectile> getPlayerProjectiles() {
		return playerProjectiles;
	}

	public void setPlayerProjectiles(Array<Projectile> playerProjectiles) {
		this.playerProjectiles = playerProjectiles;
	}

	public Array<Projectile> getEnemyProjectiles() {
		return enemyProjectiles;
	}

	public void setEnemyProjectiles(Array<Projectile> enemyProjectiles) {
		this.enemyProjectiles = enemyProjectiles;
	}

	private void checkEnemyCollisions(){
		//Stream.of(e).parallel().forEach(enemy -> enemy.d);
		
		// loop through enemies
		
		
		Enemy e = null;
		Building b = null;
		Projectile p = null;
		
		Iterator<Enemy> enemyIterator = enemies.iterator();
		Iterator<Projectile> projectileIterator = playerProjectiles.iterator();
		//Iterator<Building> buildingIterator = buildings.iterator();
		
		while(enemyIterator.hasNext()){
			
			e = enemyIterator.next();
			while(projectileIterator.hasNext()){
				p = projectileIterator.next();
				checkEnemyProjectileCollisions(e, p);
			}
			//while(buildingIterator.hasNext()){
			//	b= buildingIterator.next();
				checkEnemyWallCollisions(e, b);
			//}	
			
		}
	
		
		checkEnemyWallCollisions(e,b);
		checkEnemyProjectileCollisions(e,p);
		
	
	}
	
	private void checkEnemyWallCollisions(Enemy e, Building b){
		//TODO
	}
	
	private void checkEnemyProjectileCollisions(Enemy e, Projectile p){
		//TODO
	}
	
	private void checkPlayerAttackBuilding(Player player, Building building){
		
		if(building.hit(player.getAttackRange())){
			building.damage(1);
		}
		
	}
	
	private void checkPlayerCollisions(){
		
		//Player player = null;
		//Building b = null;
		Projectile p = null;
		//Iterator<Building> buildingIterator = buildings.iterator();
 	//	Iterator<Projectile> projectileiterator = enemyProjectiles.iterator();
 		
 		for(int x = 0; x < buildings.length; x++){
 			for(int y = 0; y < buildings[x].length; y ++){ 				
 				if(buildings[x][y].getBuilding() == null){
 					continue;
 				}
 				checkPlayerWallCollisions(player,buildings[x][y].getBuilding());
 				if(player.isCheckAttack())
 				checkPlayerAttackBuilding(player,buildings[x][y].getBuilding());
 				
 			}
 		}
 		player.setCheckAttack(false);
		checkPlayerProjectileCollisions(player, p);
		checkItemCollisions();
	}
	
	private void checkItemCollisions(){
		
		Iterator<Item> itor = groundItems.iterator();
		while(itor.hasNext()){
			Item i = itor.next();
			if(i.getPosition().dst(player.getPosition()) < player.getBounds().width){
				
				if(player.inventory.addItem(i)){					
				itor.remove();
				}
			}
		}
		
		
	}
	
	private void checkPlayerProjectileCollisions(Player player, Projectile p){
		//TODO
	}
	
	private void checkPlayerWallCollisions(Player p, Building b){
		//TODO
		if(b == null) return;
		
		if(p.getBounds().overlaps(b.getBounds())){
			// if true then move the person back...
			// get the velocity and move them back
			// in the opposite direction........
			// for fast things this wont work
			// dont make the player go more than one width of a tile in 1 frame.
			
			boolean abovePosLine;
			boolean aboveNegLine;
			
			if(p.getPosition().y > b.slopePos( p.getPosition().x) ){
				abovePosLine = true;
			}else {
				abovePosLine = false;
			}
			if(p.getPosition().y > b.slopeNeg( p.getPosition().x)){
				aboveNegLine = true;
			}else {
				aboveNegLine = false;
			}
			//System.out.println("above pos:" + abovePosLine + " above neg: " + aboveNegLine);

			//System.out.println("slope pos y at x: "+b.slopeNeg( p.getPosition().x)  );
			//System.out.println("slope neg y at x: "+b.slopePos( p.getPosition().x)  );

			
			//System.out.println( "player pos: " + p.getPosition() + " sqare pos: " + b.getPosition());
			if(abovePosLine && aboveNegLine){
				// north quad
				p.setPositionY(b.getPosition().y + b.getHeight()/2 + p.getHeight()/2 );
				//System.out.println("moved north");
			}else if (abovePosLine && !aboveNegLine) {
				// west quad
				p.setPositionX(b.getPosition().x - b.getWidth()/2 - p.getWidth()/2 );
				//System.out.println("moved west");

			}else if(!abovePosLine && aboveNegLine){
				// east quad
				p.setPositionX(b.getPosition().x + b.getWidth()/2 + p.getWidth()/2 );
				//System.out.println("moved east");

			}else{
				// south quad
				p.setPositionY(b.getPosition().y - b.getHeight()/2 - p.getHeight()/2 );
				//System.out.println("moved south");

			}
			
			
			
			
		}
	}
	
	
	/// check collisions between 
	

	public void handleCollisions(){
		checkPlayerCollisions();
		//checkEnemyCollisions();
	}
}
