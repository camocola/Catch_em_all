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
	
}
