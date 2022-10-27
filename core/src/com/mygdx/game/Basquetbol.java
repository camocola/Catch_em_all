package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Basquetbol extends Balon {
	
	public Basquetbol(Sound s, Texture i, int vY) 
	{
		super(s, i, vY);
	}

	@Override
	public boolean onColision(Arquero gk) 
	{
		Rectangle r = getR();
		
		//Si colisionaron se reproduce el sonido y se aumentan los puntos 
		if (r.overlaps(gk.getArea()) == true)
		{
			effect(gk);
			playSound();
			return true;
		}
		return false;
	}

	@Override
	//Si el arquero atrapa la pelota suma 10 puntos
	public void effect(Arquero gk) 
	{
		gk.sumarPuntos(5);
	}
		

}
