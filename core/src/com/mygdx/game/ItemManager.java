package com.mygdx.game;

import com.badlogic.gdx.Gdx;
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
    private Texture bengala;
    private Sound FWsound;
    private Music stadiumMusic;
	   
    // Se cargan todas las texturas y sonidos de los objetos.
	public ItemManager() 
	{
		stadiumMusic = Gdx.audio.newMusic(Gdx.files.internal("colo colo.wav"));
		this.bengala = new Texture(Gdx.files.internal("bengala.png"));
		this.FWsound = Gdx.audio.newSound(Gdx.files.internal("fuegoArtificial.wav"));
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
	
	// Crea distintos tipos de objetos de manera aleatoria
	private void createObject() 
	{
	      int r = MathUtils.random(1,5);
	      BallFactory f = BallFactory.getFactory();
	      switch(r)
	      {
	      	case 1:
	      	{
	      		objectPos.add(f.createSoccer());
	      		break;
	      	}
	      	case 2:
	      	{
		      	Bengala obj = new Bengala(FWsound, bengala, 200, 200);
	      		obj.setDimensions(30f, 30f);
	      		objectPos.add(obj);
	      		break;
	      	}
	      	case 3:
	      	{
	      		objectPos.add(f.createBasquet());
	      		break;
	      	}
	      	case 4:
	      	{
	      		objectPos.add(f.createTenis());
	      		break;
	      	}
	      	case 5:
	      	{
	      		objectPos.add(f.createBowl());
	      		break;
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
	   // Se generan 1 objeto cada 0.5 segundos.
	   if(TimeUtils.nanoTime() - lastDropTime > 500000000)  
	   {
		   createObject();
	   }
	  
	   // Recorremos el arreglo de colisionables.
	   for (int i=0; i < objectPos.size; i++ ) 
	   {
		   Colisionable obj = objectPos.get(i);
		   // Se mueve el objeto
		   obj.move();
		   
		   /* Se verifica si el arquero colisono con algún objeto.
		    * de chocar llama a oncolision y se elimina el objeto.
		    */
		   if (obj.checkColision(gk) == true)
		   {
			   obj.onColision(gk);
			   deleteObj(i);
		   }	
		   
		   // Si el objeto salio del area visible se elimina
		   if (obj.outOfBounds() == true)
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
		  /*
		   * Se utiliza el metodo drawImage de la clase abstracta para
		   * dibujar las imagenes de los objetos.
		   */
		  Objeto obj = (Objeto)objectPos.get(i);
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