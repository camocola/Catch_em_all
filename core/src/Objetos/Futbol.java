package Objetos;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Futbol extends Balon
{

	public Futbol(Sound s, Texture i, int vY) 
	{
		super(s, i, vY);
	}

	@Override
	public void onColision(Arquero gk) 
	{
		points(gk);
		playSound();
	}

	@Override
	//Si el arquero atrapa la pelota suma 10 puntos
	public void points(Arquero gk) 
	{
		gk.sumarPuntos(10);
	}
	
	@Override
	/*
	 * Se verifica que el arquero atrape la pelota, de no atraparla
	 * se comprueba que la pelota no haya salido del area de vision,
	 * de salir se le quita una vida al arquero.
	 */
	public boolean checkColision (Arquero gk)
	{
		if (getR().overlaps(gk.getArea()) == true)
		{
			return true;
		}
		
		if (outOfBounds() == true)
		{
			gk.dañar();
		}
		return false;
	}

	@Override
	public void effect(Arquero gk)
	{
		gk.buffear(1.5f);	
	}
}
