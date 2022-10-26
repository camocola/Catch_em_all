package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class ItemManager
{
	private Array<Colisionable> objectPos;
	private Array<Integer> objectType;
    private long lastDropTime;
    private Texture soccerBall;
    private Texture basquetBall;
    private Texture ladrillo;
    private Sound dropSound;
    private Music stadiumMusic;
	   
	public ItemManager(Texture gotaBuena, Texture ladrillo, Texture basquetBall, Sound ss, Music mm) 
	{
		stadiumMusic = mm;
		dropSound = ss;
		this.soccerBall = gotaBuena;
		this.basquetBall = basquetBall;
		this.ladrillo = ladrillo;
	}
	
	public void crear() 
	{
		objectPos = new Array<Colisionable>();
		objectType = new Array<Integer>();
		createObject();
	  // start the playback of the background music immediately
	  stadiumMusic.setLooping(true);
	  stadiumMusic.setVolume(0.1f);
	  stadiumMusic.play();
	}
	
	private void createObject() 
	{
	      Futbol obj = new Futbol(dropSound, soccerBall, 300);
//		  raindrop.x = MathUtils.random(0, 800-64);
//		  raindrop.y = 480;
//		  raindrop.width = 64;
//		  raindrop.height = 64;
	      obj.setDimensions(64f, 64f);
	      objectPos.add(obj);
	      // Ver el tipo de gota
	      /* Acá se tienen que generar los diversos tipos de balones
	       * u objetos que perjudican al jugador, tambien se pueden
	       * añadir powerups los cuales dan bonificaciones al jugador
	       */
	      int r = MathUtils.random(1,1);
	      switch(r)
	      {
	      	case 1:
	      	{
	      		
	      		objectType.add(1);
	      		break;
	      	}
//	      	case 2:
//	      	{
//	      		objectType.add(2);
//	      		break;
//	      	}
//	      	case 3:
//	      	{
//	      		objectType.add(3);
//	      		break;
//	      	}
	      	default:
	      	{
	      		objectType.add(1);
	      		break;
	      	}
	      }
	      
	      lastDropTime = TimeUtils.nanoTime();
	   }
	
   public boolean actualizarMovimiento(Arquero goalkeeper) 
   { 
	   // Generar gotas de lluvia 
	   if(TimeUtils.nanoTime() - lastDropTime > 100000000)  
	   {
		   createObject();
	   }
	  
	   /* Recorremos todas las gotas que se han generado y se revisa si estas
	    * Fueron atrapadas o cayeron al suelo.
	    */
	   for (int i=0; i < objectPos.size; i++ ) 
	   {
		  Futbol obj = (Futbol)objectPos.get(i);
	      obj.move();
	      /*Si cae al suelo se elimina la gota.
	       *Acá habría que revisar si la pelota que cayó al suelo es de futbol.
	       */
	      if(obj.outOfBounds() == true) 
	      {
	    	  objectPos.removeIndex(i); 
	    	  objectType.removeIndex(i);
	      }
	      if(obj.verColision(goalkeeper) == true)
	      {
	    	  objectPos.removeIndex(i); 
	    	  objectType.removeIndex(i);
	      }
	   } 
	  return true; 
   }
   
   public void actualizarDibujoLluvia(SpriteBatch batch) 
   { 
	  /* En esta sección hay que revisar de que tipo es el objeto
	   * generado, para poder dibujarlo con su imagen correspondiente
	   * 
	   */
	  for (int i=0; i < objectPos.size; i++ ) 
	  {
		  Futbol obj = (Futbol)objectPos.get(i);
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
	// TODO Auto-generated method stub
	

}
