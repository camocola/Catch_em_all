package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bengala extends Proyectiles
{

	public Bengala(Sound s, Texture i, int vX, int vY) 
	{
		super(s, i, vX, vY);
	}

	@Override
	// 
	public boolean onColision(Arquero gk) 
	{
		Rectangle r = getR();
		updateDirection(r);
		move();
		
		if (outOfBounds() == true)
		{
			return true;
		}
		//Si colisionaron se reproduce el sonido y se aumentan los puntos 
		if (r.overlaps(gk.getArea()) == true)
		{
			effect(gk);
			return true;
		}
		return false;
	}

	@Override
	// Si atrapa una bengala el arquero, este pierde 25 puntos
	public void effect(Arquero gk) 
	{
		playSound();
		gk.restarPuntos(25);
	}

}
