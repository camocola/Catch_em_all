package Objetos;

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
	
	// Constructor
	public Objeto (Sound s, Texture i)
	{
		catchSound = s;
		image = i;
	}
	
	@Override
	// Verifica que el objeto no salga de la pantalla
	public boolean outOfBounds()
	{
		if (r.y + 64 < 0)
		{
			return true;
		}
		return false;
	}
	
	// La cantidad de puntos que suma cada objeto.
	public abstract void points(Arquero gk);
	
	// Reproduce el sonido al atrapar el objeto.
	public void playSound ()
	{
		catchSound.play();
	}
	
	// Dibuja el objeto.
	public void drawImage(SpriteBatch batch)
	{
		/*Se dibuja el item en una posicion aleatoria a lo largo del eje x
		 * en el eje y se deja constante en 480 que es el tope de la ventana
		 */
		batch.draw(image, r.x, r.y);
	}
	
	@Override
	// Revisa si el objeto esta en colision con el arquero.
	public boolean checkColision(Arquero gk)
	{
		if (r.overlaps(gk.getArea()) == true)
		{
			return true;
		}
		return false;
	}
	
	// Inicia las dimensiones del objeto.
	public void setDimensions(float w, float h)
	{
		r = new Rectangle();
		r.x = MathUtils.random(0,800-64);
		r.y = 480;
		r.width = w;
		r.height = h;
	}
	
	// Retorna el rectangulo
	public Rectangle getR()
	{
		return r;
	}
	
}
 