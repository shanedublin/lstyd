package com.mygdx.game.pathfinding;

import java.util.Iterator;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.entities.MapTile;
import com.sun.glass.ui.View.Capability;

public class Pathfinding {

	public static Array<Tile> aStarPath(MapTile[][] map, MapTile start,
			MapTile end) {

		Tile[][] convertedMap = new Tile[map.length][map[0].length];

		int x = 0;
		int y = 0;
		while (x < map.length) {
			while (y < map[0].length) {
				convertedMap[x][y] = new Tile(x, y);
				convertedMap[x][y].setDistGoal(Math.abs(x - end.getX())
						+ Math.abs(y - end.getY()));
				// if something is in this tile then set it to occpied
				// for now you would just change the dist cost to whatevel the
				// building is
				if (map[x][y].isOccupied()) {
					convertedMap[x][y].setOpen(false);

				}
				y++;
			}
			y = 0;
			x++;
		}

		x = 0;
		y = 0;

		while (x < convertedMap.length) {
			while (y < convertedMap[0].length) {
				// System.out.println(y);
				try {
					convertedMap[x][y].adjNodes.add(convertedMap[x][y + 1]);
				} catch (ArrayIndexOutOfBoundsException e) {
					// e.printStackTrace();
				}
				try {
					convertedMap[x][y].adjNodes.add(convertedMap[x][y - 1]);
				} catch (ArrayIndexOutOfBoundsException e) {
					// e.printStackTrace();
				}
				try {
					convertedMap[x][y].adjNodes.add(convertedMap[x + 1][y]);
				} catch (ArrayIndexOutOfBoundsException e) {
					// e.printStackTrace();
				}
				try {
					convertedMap[x][y].adjNodes.add(convertedMap[x - 1][y]);
				} catch (ArrayIndexOutOfBoundsException e) {
					// e.printStackTrace();
				}
				y++;
			}
			y = 0;
			x++;

		}

		return findPath(convertedMap, convertedMap[start.getX()][start.getY()],
				convertedMap[end.getX()][end.getY()]);
	}

	private static Array<Tile> findPath(Tile[][] map, Tile start, Tile end) {
		// search closed list

		int i = 0;
		Array<Tile> closedList = new Array<Tile>();
		closedList.add(start);
		start.setDistfromStart(0);
		start.setOpen(false);
		Tile nextClosedNode = null;
		boolean keepSearching = true;
		// System.out.println("looking for path...");
		// System.out.println("Start node:" + start + " End node: " + end);
		while (keepSearching) {
			// System.out.println();
			// System.out.println("Starting closed list loop, number of nodes in the list: "
			// + closedList.size());

			Iterator<Tile> closedItor = closedList.iterator();
			nextClosedNode = null;
			int distToclosestNode = Integer.MAX_VALUE;
			while (closedItor.hasNext()) {

				Tile t = closedItor.next();
				// System.out.println();
				// System.out.println("Closed node: " + t.toString());

				Iterator<Tile> adjItor = t.adjNodes.iterator();

				while (adjItor.hasNext()) {
					Tile a = adjItor.next();
					// System.out.println("Node on closed list: " + t +
					// " Node adjacent: " + a);
					
					// check to see if the adj are valid
					if (!a.isValid()) {
						// System.out.println("Node was invalid removing from list: "
						// + a);
						// adjItor.remove();					
						continue;
					}

					// find next closest node to all the closed nodes...
					// if dist to this node is shorter than dist toclosest node
					if (a.getNodeCost() + t.getDistfromStart()
							+ a.getDistGoal() < distToclosestNode
							&& a.isOpen()) {
						distToclosestNode = a.getNodeCost();
						a.setDistfromStart(t.getDistfromStart()
								+ a.getNodeCost());
						a.setParentTile(t);
						a.setDistfromStart(t.getDistfromStart()
								+ distToclosestNode);
						nextClosedNode = a;

						// System.out.println("Dist is: " + (a.getNodeCost() +
						// t.getDistfromStart()));
						// System.out.println("Next temp closest node is : " +
						// nextClosedNode);

					}
				}
			}
			// System.out.println("Adding this node into closed nodes: " +
			// nextClosedNode);

			i++;
			if (i > 1000) {
				keepSearching = false;
				System.out.println("Reached cap..");
				System.out.println("next node..." + nextClosedNode );
			}
			
			if(nextClosedNode == null){
				//System.out.println("nextclosed node was null");
				// cant find the end
				keepSearching = false;
			}
			
			if (nextClosedNode != null) {
				

				//System.out.println(nextClosedNode);
				nextClosedNode.setOpen(false);
				closedList.add(nextClosedNode);

				if (end.getX() == nextClosedNode.getX()
						&& end.getY() == nextClosedNode.getY()) {
					keepSearching = false;
					// System.out.println("You found the node!!!");
				}
			}
			// keepSearching = true;

			
			
			/**
			 * 
			 * 
			 * while end not found{
			 * 
			 * next closest node dist to next closest node while closed nodes{
			 * 
			 * loop adjacent nodes{ to that closed node save closest node to the
			 * the all closest node. }
			 * 
			 * 
			 * } add next closest node to closed nodes and save the previous
			 * parent node if next closest node is end node then exit }
			 * 
			 * 
			 */

		}
		
		Tile tempTile  = closedList.first();
		if(nextClosedNode == null){
			tempTile  = closedList.first();
			for (Tile tile : closedList) {
				if(tile.getDistGoal() < tempTile.getDistGoal()){
						tempTile = tile;
				}
			}
		}else{
			tempTile = end;
			
		}
		
		Array<Tile> tiles = new Array<Tile>();
		
		while (tempTile.getX() != start.getX()
				|| tempTile.getY() != start.getY()) {
			// System.out.println(temptile);
			tiles.add(tempTile);
			tempTile = tempTile.getParentTile();
			if (tempTile == null) {
				break;
			}
		}
		// System.out.println(temptile);
		// System.out.println(start);

		tiles.reverse();
		return tiles;

	}

}
