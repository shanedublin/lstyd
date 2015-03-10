package com.mygdx.game.entities.logic;

import java.util.Iterator;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.Projectile;
import com.mygdx.game.entities.buildings.Building;
import com.mygdx.game.entities.player.Player;

public class MoveEntities {

	Player p = null;
	
	Array<Projectile> enemyProjectiles;
	Array<Projectile> playerProjectiles;	
	Array<Enemy> enemies; 
	
	
	
	public MoveEntities() {
	
			// TODO Auto-generated constructor stub
		
		
		
	}
	
	
	
	
	public Player getP() {
		return p;
	}




	public void setP(Player p) {
		this.p = p;
	}




	public Array<Projectile> getEnemyProjectiles() {
		return enemyProjectiles;
	}




	public void setEnemyProjectiles(Array<Projectile> enemyProjectiles) {
		this.enemyProjectiles = enemyProjectiles;
	}




	public Array<Projectile> getPlayerProjectiles() {
		return playerProjectiles;
	}




	public void setPlayerProjectiles(Array<Projectile> playerProjectiles) {
		this.playerProjectiles = playerProjectiles;
	}




	public Array<Enemy> getEnemies() {
		return enemies;
	}




	public void setEnemies(Array<Enemy> enemies) {
		this.enemies = enemies;
	}




	public void moveStuff(){		
		movePlayer();
		moveEnemies();
		moveProjectiles();
	}
	
	private void moveProjectiles(){
		
	}
	
	private void moveEnemies(){
		
	}
	
	private void movePlayer(){
		
		
	}
	
	
}
