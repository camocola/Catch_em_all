package Objetos;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Basquetbol extends Balon implements Effect 
{
	
	public Basquetbol(Sound s, Texture i, int vY) 
	{
		super(s, i, vY);
	}

	@Override
	// Si el arquero atrapa la pelota suma 10 puntos
	public void points(Arquero gk) 
	{
		gk.sumarPuntos(5);
	}

	@Override
	public void effect(Arquero gk) 
	{
		gk.buffear(1.2f);
	}
}
