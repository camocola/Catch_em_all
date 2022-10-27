package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Proyectiles extends Objeto
{
	private int velX;
	private int velY;
	
	public Proyectiles(Sound s, Texture i, int vX, int vY) 
	{
		super(s, i);
		velX = vX;
		velY = vY;
	}
	
	public void move()
	{
		Rectangle r = getR();
		r.y -= velY * Gdx.graphics.getDeltaTime();
		r.x -= velX * Gdx.graphics.getDeltaTime();
	}
}
