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
    private long lastDropTime;
    private Texture soccerBall;
    private Texture basquetBall;
    private Texture bengala;
    private Texture tenis;
    private Texture bolos;
    private Sound dropSound;
    private Music stadiumMusic;
	   
    // Constructor
	public ItemManager(Texture soccerBall, Texture bengala, Texture basquetBall, Texture tenis, Texture bolos, Sound ss, Music mm) 
	{
		stadiumMusic = mm;
		dropSound = ss;
		this.soccerBall = soccerBall;
		this.basquetBall = basquetBall;
		this.bengala = bengala;
		this.tenis = tenis;
		this.bolos = bolos;
	}
	
	// Se inicializan los arreglos y musicas
	public void crear() 
	{
	  objectPos = new Array<Colisionable>();
	  createObject();
	  stadiumMusic.setLooping(true);
	  stadiumMusic.setVolume(0.1f);
	  stadiumMusic.play();
	}
	
	private void createObject() 
	{
	      int r = MathUtils.random(1,3);
	      switch(r)
	      {
	      	case 1:
	      	{
	      		Futbol obj = new Futbol(dropSound, soccerBall, 200);
	      		obj.setDimensions(60f, 60f);
	      		objectPos.add(obj);
	      		break;
	      	}
	      	case 2:
	      	{
		      	Bengala obj = new Bengala(dropSound, bengala, 200, 200);
	      		obj.setDimensions(30f, 30f);
	      		objectPos.add(obj);
	      		break;
	      	}
	      	case 3:
	      	{
	      		Basquetbol obj = new Basquetbol(dropSound, basquetBall, 200);
	      		obj.setDimensions(70f, 70f);
	      		objectPos.add(obj);
	      	}
	      	case 4:
	      	{
	      		Tenis obj = new Tenis(dropSound, tenis, 200);
	      		obj.setDimensions(40f, 40f);
	      		objectPos.add(obj);
	      	}
	      	case 5:
	      	{
	      		Bolos obj = new Bolos(dropSound, bolos, 400);
	      		obj.setDimensions(80f, 80f);
	      		objectPos.add(obj);
	      	}
	      }
	      
	      lastDropTime = TimeUtils.nanoTime();
	   }
	
	// Elimina los objetos del arreglo.
	public void deleteObj(int i)
	{
		objectPos.removeIndex(i); 
	}
	
   // Se actualiza el movimiento del arquero
   public boolean updateMovement(Arquero gk) 
   { 
	   // Se generan objetos.
	   if(TimeUtils.nanoTime() - lastDropTime > 1000000000)  
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
		  Colisionable obj = objectPos.get(i);
		  obj.drawImage(batch);
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