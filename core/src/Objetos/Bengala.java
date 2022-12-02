package Objetos;

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
	// Si atrapa una bengala el arquero, este pierde 25 puntos
	public void points(Arquero gk) 
	{
		gk.restarPuntos(25);
	}

	@Override
	public void effect(Arquero gk) 
	{
		gk.debuff();		
	}

}
