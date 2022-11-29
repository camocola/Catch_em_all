package Objetos;

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
	public void onColision(Arquero gk) 
	{
		points(gk);
		playSound();
	}

	//Se aturde al arquero
	@Override
	public void points(Arquero gk) 
	{
		gk.restarPuntos(10);
	}

	@Override
	public void effect(Arquero gk) 
	{
		gk.aturdir();
	}

}
