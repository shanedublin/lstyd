package com.mygdx.game.entities.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.logic.Renderable;
import com.mygdx.game.resources.ResourcesLoader;

public class Item extends Entity implements Renderable {

	// quantity
	// type
	// weapon, consumable,

	int quantity = 1;
	boolean stackable = false;
	int stackCap = 255;
	String name;
	public Sprite sprite;
	public Texture texture = ResourcesLoader.instance.getTexture("Item.png");
	public final int itemId;

	/**
	 * 
	 * @param name
	 *            displayed in game
	 * @param stackable
	 *            can multiple items fit in on item slot
	 */
	public Item(int itemId, String name, boolean stackable, String textureName) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.stackable = stackable;
		texture = getTexture(textureName);
	}

	public Texture getTexture(String textureName) {
		try {
			return ResourcesLoader.instance.getTexture(textureName);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResourcesLoader.instance.getTexture("Item.png");
	}

	@Override
	public String toString() {
		if (stackable) {
			return name + " : " + quantity;
		} else {
			return name;
		}
	}

	@Override
	public void renderThis() {
		MyGdxGame.spriteBatch.draw(texture, this.getBounds().x,
				this.getBounds().y, this.getBounds().width,
				this.getBounds().height);
	}

}
