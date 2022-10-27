package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bolos extends Balon
{

	public Bolos(Sound s, Texture i, int vY) 
	{
		super(s, i, vY);
	}

	@Override
	public boolean onColision(Arquero gk) 
	{
		Rectangle r = getR();
		move();
		if (outOfBounds() == true)
		{
			return true;
		}
		//Si colisionaron se reproduce el sonido y se aturde al arquero
		if (r.overlaps(gk.getArea()) == true)
		{
			effect(gk);
			playSound();
			return true;
		}
		return false;
	}

	//Se aturde al arquero
	@Override
	public void effect(Arquero gk) 
	{
		gk.aturdir();
	}

}
