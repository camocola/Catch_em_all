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

public class ItemManager implements Colisionable
{
	private Array<Rectangle> rainDropsPos;
	private Array<Integer> rainDropsType;
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
		rainDropsPos = new Array<Rectangle>();
		rainDropsType = new Array<Integer>();
		crearGotaDeLluvia();
	      // start the playback of the background music immediately
	      stadiumMusic.setLooping(true);
	      stadiumMusic.setVolume(0.1f);
	      stadiumMusic.play();
	}
	
	private void crearGotaDeLluvia() 
	{
	      Rectangle raindrop = new Rectangle();
	      raindrop.x = MathUtils.random(0, 800-64);
	      raindrop.y = 480;
	      raindrop.width = 64;
	      raindrop.height = 64;
	      rainDropsPos.add(raindrop);
	      // Ver el tipo de gota
	      /* Acá se tienen que generar los diversos tipos de balones
	       * u objetos que perjudican al jugador, tambien se pueden
	       * añadir powerups los cuales dan bonificaciones al jugador
	       */
	      int r = MathUtils.random(1,10);
	      switch(r)
	      {
	      	case 1:
	      	{
	      		rainDropsType.add(1);
	      		break;
	      	}
	      	case 2:
	      	{
	      		rainDropsType.add(2);
	      		break;
	      	}
	      	case 3:
	      	{
	      		rainDropsType.add(3);
	      		break;
	      	}
	      	default:
	      	{
	      		rainDropsType.add(2);
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
		   crearGotaDeLluvia();
	   }
	  
	   /* Recorremos todas las gotas que se han generado y se revisa si estas
	    * Fueron atrapadas o cayeron al suelo.
	    */
	   for (int i=0; i < rainDropsPos.size; i++ ) 
	   {
		  Rectangle raindrop = rainDropsPos.get(i);
	      raindrop.y -= 300 * Gdx.graphics.getDeltaTime();
	      /*Si cae al suelo se elimina la gota.
	       *Acá habría que revisar si la pelota que cayó al suelo es de futbol.
	       */
	      if(raindrop.y + 64 < 0) 
	      {
	    	  rainDropsPos.removeIndex(i); 
	    	  rainDropsType.removeIndex(i);
	      }
	      if (verColision(raindrop, goalkeeper, i) == false)
	      {
	    	  return false;
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
	  for (int i=0; i < rainDropsPos.size; i++ ) 
	  {
		  Rectangle raindrop = rainDropsPos.get(i);
		  // gota dañina
		  if(rainDropsType.get(i)==1)
		  {
	         batch.draw(ladrillo, raindrop.x, raindrop.y); 
		  }
		  // Balon de futbol
		  else if (rainDropsType.get(i)==2)
		  {
			 batch.draw(soccerBall, raindrop.x, raindrop.y); 
		  }
		  else
		  {
			  batch.draw(basquetBall, raindrop.x, raindrop.y); 
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

	@Override
	public boolean verColision(Rectangle obj, Arquero goalkeeper, int i) 
	{
	  // La gota choca con el tarro
      // Overlaps (sobrepone a la gota) chocan
      if(obj.overlaps(goalkeeper.getArea())) 
      {
    	// Si es un petardo/
    	if(rainDropsType.get(i)==1) 
    	{ 
    		goalkeeper.dañar();
    	  
    	  if (goalkeeper.getVidas()<=0) 
    	  { 
    		  return false;
    	  }
    	  rainDropsPos.removeIndex(i);
          rainDropsType.removeIndex(i);
      	}
    	/*Se suman los puntos correspondientes, ya que no era un objeto dañino.
    	 *Era un balon de futbol/basquet/
    	 */
    	else 
    	{ 
    		goalkeeper.sumarPuntos(10);
          dropSound.play();
          rainDropsPos.removeIndex(i);
          rainDropsType.removeIndex(i);
      	}
      }
      return true;
	}
	// TODO Auto-generated method stub
	

}
