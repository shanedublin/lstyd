package com.mygdx.game.entities;

import java.util.Iterator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Path;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameScreen;
import com.mygdx.game.pathfinding.Tile;

public class Enemy extends Entity {

	
	
	
	Array<Vector2> path;
	
	Iterator<Vector2> itor;
	Vector2 destNode;
	Vector2 currentNode;
	boolean canMove;
	public Enemy(Vector2 destination){
		super();
		path = new Array<Vector2>();
		//path.add(new Vector2(5, 5));
		//path.add(new Vector2(4, 4));
		//path.add(new Vector2(3, 3));
		//path.add(new Vector2(7, 3));
		//path.add(new Vector2(1, 1));
		
		//itor = path.iterator();
		//destNode = itor.next();
		canMove = false;
		currentNode = new Vector2((this.getPositon().x / GameScreen.tileSize), this.getPositon().y / GameScreen.tileSize);
		//System.out.println("current: " + currentNode +" dest:" + destNode);
		

	}
	
	
	
	public void SetPath(Array<Tile> tilePath){
		if(tilePath.size <= 0){
			
			return;
		}
		
		Array<Vector2> convertedPath = new Array<Vector2>();
		
		for (Tile t : tilePath) {
			convertedPath.add(new Vector2(t.getX(),t.getY()));
		}
		this.path = convertedPath;
		itor = path.iterator();
		destNode = itor.next();
		canMove = true;
	}
	
	
	public void MoveEnemy(){
		
		if(!canMove){
			return;
		}
		
		int pathX ;
		int pathY ;
	
		
		currentNode = new Vector2( (int)(this.getPositon().x / GameScreen.tileSize), (int) this.getPositon().y / GameScreen.tileSize);
		//System.out.println("current: " + currentNode +" dest:" + destNode);
		// move toward next node
			// current node - destination node
		if(currentNode.x == destNode.x && currentNode.y == destNode.y ){
			
			if(itor.hasNext()){
				destNode = itor.next();		
				
				
				//System.out.println("new Destination:" + destNode);
				
			}else{
				return;
			}
		}
		//System.out.println(destNode);
		Vector2 moveDir =	FindMoveDirection(this.getPositon(), destNode);
		this.movePosition(moveDir);
		//System.out.println("moved");
		// if reached node change to next node		
		// if reached final node stop moving
	}
	
	
public Vector2 FindMoveDirection(Vector2 currentPos, Vector2 desiredPos){
		
		Vector2 desiredDirection = new Vector2();		
		desiredDirection.x = desiredPos.x * GameScreen.tileSize + GameScreen.tileSize/2 - currentPos.x;
		desiredDirection.y = desiredPos.y * GameScreen.tileSize + GameScreen.tileSize/2 - currentPos.y;
		
		if(Math.abs(desiredDirection.x) > 1){
			desiredDirection.x = Math.signum(desiredDirection.x);			
		}else{
			desiredDirection.x = 0;
		}
		if(Math.abs(desiredDirection.y) > 1){
			desiredDirection.y = Math.signum(desiredDirection.y);
		}else{
			desiredDirection.y = 0;
		}
		
		
		
		
		return desiredDirection;
	}
	
	public Vector2 FindCloseNode(Vector2 currentPos){
		Vector2 goalpos = new Vector2();
		goalpos.x  = (int) (currentPos.x / GameScreen.tileSize);
		goalpos.y  = (int) (currentPos.y / GameScreen.tileSize);
		goalpos.x *= GameScreen.tileSize;
		goalpos.y *= GameScreen.tileSize;
		
		goalpos.x += GameScreen.tileSize /2;
		goalpos.y += GameScreen.tileSize /2;
		
		return goalpos;
		
	}



	@Override
	public void renderThis() {
		// TODO Auto-generated method stub
		
	}
	
	
}
