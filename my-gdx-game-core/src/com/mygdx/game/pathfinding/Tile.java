package com.mygdx.game.pathfinding;

import java.util.ArrayList;
import java.util.Iterator;

public class Tile {
	
	public ArrayList<Tile> adjNodes;
	
	private boolean open;
	private int nodeCost;
	private int distfromStart;
	private int distGoal;
	private Tile parentTile;
	
	private int x;
	private int y;
	
	
	public Tile(int x, int y){
		this.open = true;
		nodeCost = 1;
		this.x = x;
		this.y = y;
		distfromStart = Integer.MAX_VALUE;
		adjNodes = new ArrayList<Tile>();		
	}	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getDistGoal() {
		return distGoal;
	}


	public void setDistGoal(int distGoal) {
		this.distGoal = distGoal;
	}

	
	
	public boolean isOpen() {
		return open;
	}


	public void setOpen(boolean open) {
		this.open = open;
	}


	public Tile getParentTile() {
		return parentTile;		
	}
	
	public void setParentTile(Tile parentTile) {
		//System.out.println(this + " has the parent of : " + parentTile);
		this.parentTile = parentTile;
	}
	
	public int getDistfromStart() {
		return distfromStart;
	}
	
	public void setDistfromStart(int distfromStart) {
		this.distfromStart = distfromStart;
	}	
	
	public int getNodeCost() {
		return nodeCost;
	}
	
	public void setNodeCost(int nodeCost) {
		this.nodeCost = nodeCost;
	}
	
	public boolean isValid(){		
		// if the node is open  return true
		// or if you can traverse it
		return open;
	}
	
	@Override
	public String toString() {
		String s = "X: " + x + " Y: " + y;
		return s;
	}
}
