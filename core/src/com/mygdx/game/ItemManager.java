package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import Objetos.Arquero;
import Objetos.Bengala;
import Objetos.Colisionable;
import Objetos.Objeto;

public class ItemManager
{
    private long lastDropTime;
    private Music stadiumMusic;
	   
    // Se cargan todas las texturas y sonidos de los objetos.
	public ItemManager() 
	{
		stadiumMusic = Gdx.audio.newMusic(Gdx.files.internal("colo colo.wav"));
	}
	
	// Se inicializan los arreglos y musicas
	public void crear() 
	{
		ColeccionColisionable objectPos = ColeccionColisionable.getInstance();
	    objectPos.createObject();
	    stadiumMusic.setLooping(true);
	    stadiumMusic.setVolume(0.1f);
	    stadiumMusic.play();
	}
	
   // Se actualiza el movimiento del arquero
   public boolean updateMovement(Arquero gk) 
   { 
	   ColeccionColisionable objectPos = ColeccionColisionable.getInstance();
	   // Se generan 1 objeto cada 0.5 segundos.
	   if(TimeUtils.nanoTime() - objectPos.getDropTime() > 500000000)  
	   {
		   objectPos.createObject();
	   }
	  
	   // Recorremos el arreglo de colisionables.
	   for (int i=0; i < objectPos.getSize(); i++ ) 
	   {
		   Colisionable obj = objectPos.getObj(i);
		   // Se mueve el objeto
		   obj.move();
		   
		   /* Se verifica si el arquero colisono con algún objeto.
		    * de chocar llama a oncolision y se elimina el objeto.
		    */
		   if (obj.checkColision(gk) == true)
		   {
			   obj.onColision(gk);
			   objectPos.deleteObj(i);
		   }	
		   
		   // Si el objeto salio del area visible se elimina
		   if (obj.outOfBounds() == true)
		   {
			   objectPos.deleteObj(i);
		   }
		   
		   // Se verifica que el arquero siga vivo.
		   if (gk.getVidas() == 0)
		   {
			   return false;
		   }
	   } 
	  return true; 
   }
   
   // Se actualiza el dibujo de los objetos.
   public void updateDraw(SpriteBatch batch) 
   { 
	  ColeccionColisionable objectPos = ColeccionColisionable.getInstance();
	  for (int i=0; i < objectPos.getSize(); i++ ) 
	  {
		  /*
		   * Se utiliza el metodo drawImage de la clase abstracta para
		   * dibujar las imagenes de los objetos.
		   */
		  Objeto obj = (Objeto)objectPos.getObj(i);
		  obj.drawImage(batch);
	   }
   }
   
   // Si se le acabaron las vidas al jugador.
   public void destruir() 
   {
      stadiumMusic.dispose();
   }
   public void pausar() 
   {
	  stadiumMusic.stop();
   }
   public void continuar() 
   {
	  stadiumMusic.play();
   }
}