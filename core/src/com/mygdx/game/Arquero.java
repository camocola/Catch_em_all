package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class Arquero 
{
   private Rectangle arquero;
   private Texture imagenArquero;
   private Sound sonidoHerido;
   private int vidas = 3;
   private int puntos = 0;
   private int velx = 400;
   private boolean aturdido = false;
   private int tiempoHeridoMax = 50;
   private int tiempoHerido;
   
   // Constructor
   public Arquero(Texture tex, Sound ss) 
   {
	   imagenArquero = tex;
	   sonidoHerido = ss;
   }
   
   
	public int getVidas() 
	{
		return vidas;
	}

	public int getPuntos() 
	{
		return puntos;
	}
	public Rectangle getArea() 
	{
		return arquero;
	}
	public void sumarPuntos(int pp) 
	{
		puntos+=pp;
	}
	
	
	
   public void crear() 
   {
	      arquero = new Rectangle();
	      arquero.x = 800 / 2 - 64 / 2;
	      arquero.y = 20;
	      arquero.width = 64;
	      arquero.height = 64;
   }
   
   // Se aturde al arquero.
   public void dañar() 
   {
	  vidas--;
	  aturdido = true;
	  tiempoHerido = tiempoHeridoMax;
	  sonidoHerido.play();
   }
   
   public void dibujar(SpriteBatch batch) 
   {
	 // Si no esta herido
	 if (!aturdido)  
	 {
		 batch.draw(imagenArquero, arquero.x, arquero.y); 
	 }
	 else 
	 {
	   batch.draw(imagenArquero, arquero.x, arquero.y+ MathUtils.random(-5,5));
	   tiempoHerido--;
	   if (tiempoHerido<=0) 
	   {
		   aturdido = false;
	   }
	 }
   } 
   
   
   public void actualizarMovimiento() 
   { 
	   // Movimiento desde teclado
	   if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) 
	   {
		   arquero.x -= velx * Gdx.graphics.getDeltaTime();
	   }
	   if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
	   {
		   arquero.x += velx * Gdx.graphics.getDeltaTime();
	   }
	   
	   // Que no se salga de los bordes izq y der
	   if(arquero.x < 0) 
	   {
		   arquero.x = 0;
	   }
	   if(arquero.x > 800 - 64) 
	   {
		   arquero.x = 800 - 64;
	   }
   }
	    

	public void destruir() 
	{
		imagenArquero.dispose();
	}
	
   public boolean estaHerido() 
   {
	   return aturdido;
   }
	   
}
