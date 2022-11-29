package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import Objetos.Bengala;
import Objetos.Colisionable;

public class ColeccionColisionable 
{
	private Array<Colisionable> objectPos;
	private static ColeccionColisionable c;
	private long lastDropTime;
	private Texture bengala;
    private Sound FWsound;
	
	private ColeccionColisionable()
	{
		objectPos = new Array<Colisionable>();
		this.bengala = new Texture(Gdx.files.internal("bengala.png"));
		this.FWsound = Gdx.audio.newSound(Gdx.files.internal("fuegoArtificial.wav"));
	}
	
	// Crea distintos tipos de objetos de manera aleatoria
	public void createObject() 
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
	
	public int getSize()
	{
		return objectPos.size;
	}
	
	public Colisionable getObj(int i)
	{
		return objectPos.get(i);
	}
	
	public long getDropTime()
	{
		return lastDropTime;
	}
	
	public static ColeccionColisionable getInstance()
	{
		if (c == null)
		{
			c = new ColeccionColisionable();
		}
		return c;
	}
}
