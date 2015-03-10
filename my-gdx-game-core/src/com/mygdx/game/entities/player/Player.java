package com.mygdx.game.entities.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.collisions.Intersect;
import com.mygdx.game.entities.items.Inventory;
import com.mygdx.game.entities.items.ItemList;
import com.mygdx.game.resources.ResourcesLoader;

public class Player extends Entity {

	
	
	
	private Circle attackRange;
	
	public Inventory inventory = new Inventory();
	private Texture texture = ResourcesLoader.instance.getTexture(ResourcesLoader.PLAYER);
	private boolean checkAttack = false;;
	
	
	
	
	
	public Player(){
		
		inventory.setItem("0", ItemList.Food.get());
		inventory.setItem("1", ItemList.Sword.get());
		inventory.setItem("2", ItemList.Helmet.get());
		inventory.setItem("3", ItemList.Ore.get());
		
	}
	
	
	
	
	public boolean isCheckAttack() {
		return checkAttack;
	}
	public void setCheckAttack(boolean checkAttack) {
		this.checkAttack = checkAttack;
	}




	public Circle getAttackRange() {
		return attackRange;
	}


	public void attack(Vector3 touch){
		attackRange.setPosition(touch.x,touch.y);
		checkAttack = true;
	}




	public void setAttackRange(Circle attackRange) {
		this.attackRange = attackRange;
		attackRange.setPosition(this.getPosition());
	}


	public void attackBuildings(){
		
	}
	
	public void attackEnemies(Array<Enemy> enemies){
		attackRange.setPosition(this.getPosition());
		for (Enemy e : enemies) {
			if(Intersect.CircleRectangleIntersection(attackRange, e.getBounds())){
				e.takeDamage(100);
			}
		}
		
	}


	@Override
	public void renderThis() {
		MyGdxGame.spriteBatch.draw(texture, getBounds().x, getBounds().y, getBounds().width, getBounds().height);
		
	}
	
	
	
	
}
