package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class ItemManager
{
	private Array<Colisionable> objectPos;
	private Array<Integer> objectType;
    private long lastDropTime;
    private Texture soccerBall;
    private Texture basquetBall;
    private Texture bengala;
    private Sound dropSound;
    private Music stadiumMusic;
	   
    // Constructor
	public ItemManager(Texture soccerBall, Texture bengala, Texture basquetBall, Sound ss, Music mm) 
	{
		stadiumMusic = mm;
		dropSound = ss;
		this.soccerBall = soccerBall;
		this.basquetBall = basquetBall;
		this.bengala = bengala;
	}
	
	// Se inicializan los arreglos y musicas
	public void crear() 
	{
	  objectPos = new Array<Colisionable>();
	  objectType = new Array<Integer>();
	  createObject();
	  stadiumMusic.setLooping(true);
	  stadiumMusic.setVolume(0.1f);
	  stadiumMusic.play();
	}
	
	private void createObject() 
	{
	      // Ver el tipo de gota
	      /* Acá se tienen que generar los diversos tipos de balones
	       * u objetos que perjudican al jugador, tambien se pueden
	       * añadir powerups los cuales dan bonificaciones al jugador
	       */
	      int r = MathUtils.random(1,2);
	      switch(r)
	      {
	      	case 1:
	      	{
	      		Futbol obj = new Futbol(dropSound, soccerBall, 200);
	      		obj.setDimensions(64f, 64f);
	      		objectPos.add(obj);
	      		objectType.add(1);
	      		break;
	      	}
	      	case 2:
	      	{
		      	Bengala obj = new Bengala(dropSound, bengala, 200, 200);
	      		obj.setDimensions(64f, 64f);
	      		objectPos.add(obj);
	      		objectType.add(2);
	      		break;
	      	}
//	      	case 3:
//	      	{
//	      		objectType.add(3);
//	      		break;
//	      	}
	      }
	      
	      lastDropTime = TimeUtils.nanoTime();
	   }
	
	// Elimina los objetos del arreglo.
	public void deleteObj(int i)
	{
		objectPos.removeIndex(i); 
	  	objectType.removeIndex(i);
	}
	
   // Se actualiza el movimiento del arquero
   public boolean updateMovement(Arquero gk) 
   { 
	   // Se generan objetos.
	   if(TimeUtils.nanoTime() - lastDropTime > 100000000)  
	   {
		   createObject();
	   }
	  
	   // Recorremos el arreglo de colisionables.
	   for (int i=0; i < objectPos.size; i++ ) 
	   {
		   Colisionable obj = objectPos.get(i);
		   // Se verifica si el arquero colisono con algún objeto.
		   if (obj.onColision(gk) == true)
		   {
			   deleteObj(i);
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
	  for (int i=0; i < objectPos.size; i++ ) 
	  {
		  // Se verifica el tipo de objeto
		  if (objectType.get(i) == 1)
		  {
			  Futbol obj = (Futbol)objectPos.get(i);
			  obj.drawImage(batch);
		  }
		  else
		  {
			  Bengala obj = (Bengala)objectPos.get(i);
			  obj.drawImage(batch);
		  }
	   }
   }
   
   // Si se le acabaron las vidas al jugador.
   public void destruir() 
   {
      dropSound.dispose();
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