package com.mygdx.game;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Tenis extends Balon
{
	public Tenis(Sound s, Texture i, int vY)
	{
		super(s,i,vY);
	}
	
	@Override
	public boolean verColision(Arquero gk) 
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
	//Si el arquero atrapa la pelota suma 15 puntos
	public void effect(Arquero gk) 
	{
		gk.sumarPuntos(15);
	}
	
}
