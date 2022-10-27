package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public abstract class Objeto implements Colisionable
{
	private Sound catchSound;
	private Texture image;
	private Rectangle r;
	
	public Objeto (Sound s, Texture i)
	{
		catchSound = s;
		image = i;
	}
	
	boolean outOfBounds()
	{
		if (r.y + 64 < 0)
		{
			return true;
		}
		return false;
	}
	
	public abstract void effect(Arquero gk);
	
	public void playSound ()
	{
		catchSound.play();
	}
	
	public void drawImage(SpriteBatch batch)
	{
		/*Se dibuja el item en una posicion aleatoria a lo largo del eje x
		 * en el eje y se deja constante en 480 que es el tope de la ventana
		 */
		batch.draw(image, r.x, r.y);
	}
	
	public void setDimensions(float w, float h)
	{
		r = new Rectangle();
		r.x = MathUtils.random(0,800-64);
		r.y = 480;
		r.width = w;
		r.height = h;
	}
	
	public Rectangle getR()
	{
		
		return r;
		
	}
	
}
 