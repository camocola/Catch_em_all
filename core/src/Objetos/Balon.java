package Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Balon extends Objeto
{
	private int velY;
	
	public Balon(Sound s, Texture i, int vY) 
	{
		super(s, i);
		velY = vY;
	}
	
	public void move()
	{
		Rectangle r = getR();
		r.y -= velY * Gdx.graphics.getDeltaTime();
	}
	
	@Override
	/*
	 * Todos los balones colisionan y al impactar ganan una
	 * cierta cantidad de puntos, el cual cada subclase
	 * lo implementa a su manera, por otro lado cada
	 * objeto reproduce un sonido propio al ser atrapados.
	 */
	public void onColision(Arquero gk) 
	{
		points(gk);
		effect(gk);
		playSound();
	}
}
