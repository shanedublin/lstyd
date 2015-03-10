package com.mygdx.game.menus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class UIObjects {

	// setting these to final may improve performance, not sure if i can do that
	// should be using an asset manager to do this instead of what im doing now..
	public static Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
	public static Skin skin = new Skin();
	public static TextButtonStyle textButtonStyle = new TextButtonStyle();
	public static ScrollPaneStyle scrollPaneStyle = new ScrollPaneStyle();
	
	
	
	private static void initiateTextButtonStyle(){
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);
	}
	
	private static void initiateSkin(){
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		skin.add("default", new BitmapFont());
		
		
	}
	public static void initiate(){
		initiateSkin();
		initiateTextButtonStyle();
	}
	
	
}
