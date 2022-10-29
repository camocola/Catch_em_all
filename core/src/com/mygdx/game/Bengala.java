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
	public void onColision(Arquero gk) 
	{
		effect(gk);
	}

	@Override
	// Si atrapa una bengala el arquero, este pierde 25 puntos
	public void effect(Arquero gk) 
	{
		playSound();
		gk.restarPuntos(25);
	}

}
