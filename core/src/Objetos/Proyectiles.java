package Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Proyectiles extends Objeto
{
	private int velX;
	private int velY;
	
	public Proyectiles(Sound s, Texture i, int vX, int vY) 
	{
		super(s, i);
		velX = vX;
		velY = vY;
	}
	
	// Mueve a los proyectiles a lo largo del eje x e y
	public void move()
	{
		Rectangle r = getR();
		r.y -= velY * Gdx.graphics.getDeltaTime();
		r.x -= velX * Gdx.graphics.getDeltaTime();
		updateDirection(r);
	}
	
	// Si el proyectil se sale del area visible se cambia su direccion.
	public void updateDirection(Rectangle r) 
	{
		if (r.x > 800 - 64 || r.x < 0)
		{
			velX *= -1;
		}
	}
}
