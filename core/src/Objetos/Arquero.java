package Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class Arquero 
{
   private Sprite arquero;
   private Texture imagenArquero;
   private Sound abucheo;
   private int vidas = 3;
   private int puntos = 0;
   private float velx = 400;
   private boolean aturdido = false;
   private boolean buffed = false;
   private boolean debuffed = false;
   private int tiempoEfectoMax = 50;
   private int tiempoEfecto;
   
   // Constructor
   public Arquero(Texture tex, Sound ss) 
   {
	   imagenArquero = tex;
	   abucheo = ss;
	   arquero = new Sprite(imagenArquero);
   }
   
   	// Retorna las vidas del arquero.
	public int getVidas() 
	{
		return vidas;
	}

	// Retorna los puntos del arquero.
	public int getPuntos() 
	{
		return puntos;
	}
	
	// Retorna el area del arquero
	public Rectangle getArea() 
	{
		return arquero.getBoundingRectangle();
	}
	
	// Suma puntos al arquero
	public void sumarPuntos(int pp) 
	{
		puntos+=pp;
	}
	
	// Resta puntos al arquero
	public void restarPuntos(int pp) 
	{
		puntos-=pp;
	}
	
	// Setea al arquero en el centro de la pantalla.
    public void crear() 
    {
       arquero.setCenterX(800 / 2 - 64 / 2);
       arquero.setY(20);
    }
   
   // Se aturde al arquero.
   public void aturdir() 
   {
	  aturdido = true;
	  tiempoEfecto = tiempoEfectoMax;
   }
   
   // Se buffea al arquero
   public void buffear(float percent)
   {
	   // No se puede meter doble buff
	   if (buffed == true)
	   {
		   buffed = true;
		   velx = velx * percent;
		   tiempoEfecto = tiempoEfectoMax;
	   }
   }
   
   // Se debuffea al arquero
   public void debuff()
   {
	   /*
	    *  No se puede tener un doble debuff.
	    *  Se reinicia la velocidad a la normal si le entra un debuff
	    *  para evitar que la velocidad se vuelva irregular.
	    */
	   if (debuffed == false)
	   {
		   velx = 400;
		   velx = velx * 0.7f;
		   tiempoEfecto = tiempoEfectoMax;
		   debuffed = true;
	   }
   }
   
   // Se reproduce sonido de abucheo y se le resta una vida.
   public void dañar()
   {
	   abucheo.play();
	   vidas--;
   }
   
   // Dibuja al arquero.
   public void dibujar(SpriteBatch batch) 
   {
	 // Si no esta aturdido
	 if (!aturdido)  
	 {
		 arquero.draw(batch);
	 }
	 // Se hace el efecto de wiggle
	 else if (aturdido == true)
	 {
	   arquero.setX(arquero.getX()+MathUtils.random(-5,5));
	   arquero.draw(batch);
	   tiempoEfecto--;
	   if (tiempoEfecto<=0) 
	   {
		   aturdido = false;
	   }
	 }
	 
	 // Se verifica si el arquero tiene algún efecto.
	 if (buffed == true)
	 {
		 tiempoEfecto--;
		 // Si ya paso el tiempo de efecto se vuelve a la velocidad original.
		 if (tiempoEfecto<=0) 
		 {
			 velx = 400;
			 buffed= false;
		 }	
	 }		
	 else
	 {
		 tiempoEfecto--;
		 // Si ya paso el tiempo de efecto se vuelve a la velocidad original.
		 if (tiempoEfecto<=0) 
		 {
			 velx = 400;
			 debuffed= false;
		 }
	 }
   } 
   
   // Verificar que el arquero no salga de los bordes (izquierda/derecha)
   public void outOfBounds()
   {
	   if(arquero.getX() < 0) 
	   {
		   arquero.setX(0);
	   }
	   if(arquero.getX() > 800 - 64) 
	   {
		   arquero.setX(800 - 64);
	   }
   }
   
   public void actualizarMovimiento() 
   { 
	   // Movimiento desde teclado
	   if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) 
	   {
		   arquero.setX(arquero.getX() - velx * Gdx.graphics.getDeltaTime());
	   }
	   if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
	   {
		   arquero.setX(arquero.getX() + velx * Gdx.graphics.getDeltaTime());
	   }
	   
	   outOfBounds();
   }
	    

	public void destruir() 
	{
		imagenArquero.dispose();
	}
	
   public boolean estaAturdido() 
   {
	   return aturdido;
   }
	   
}
