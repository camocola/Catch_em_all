package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import Objetos.Basquetbol;
import Objetos.Bolos;
import Objetos.Futbol;
import Objetos.Tenis;

public class BallFactory implements AbstractFactory
{
	private static BallFactory b;
	private Texture soccerBall;
    private Texture basquetBall;
    private Texture tenis;
    private Texture bolos;
    private Sound Tsound;
    private Sound Fsound;
    private Sound bonk;
    private Sound BBsound;
    
    // Se cargan todas las texturas y sonidos.
    private BallFactory ()
    {
		this.Tsound = Gdx.audio.newSound(Gdx.files.internal("tenis.wav"));
		this.Fsound = Gdx.audio.newSound(Gdx.files.internal("futbol.wav"));;
    	this.bonk = Gdx.audio.newSound(Gdx.files.internal("bonk.ogg"));
		this.BBsound = Gdx.audio.newSound(Gdx.files.internal("basketball.ogg"));
    	this.soccerBall = new Texture(Gdx.files.internal("drop.png"));
		this.basquetBall = new Texture(Gdx.files.internal("basquetBall.png"));
		this.tenis = new Texture(Gdx.files.internal("tenis.png"));
		this.bolos = new Texture(Gdx.files.internal("bolos.png"));
    }
    
	@Override
	// Se crea una pelota de tenis.
	public Tenis createTenis() 
	{
		Tenis obj = new Tenis(Tsound, tenis, 200);
  		obj.setDimensions(40f, 40f);
		return obj;
	}

	@Override
	// Se crea una pelota de futbol.
	public Futbol createSoccer() 
	{
		Futbol obj = new Futbol(Fsound, soccerBall, 200);
  		obj.setDimensions(60f, 60f);
		return obj;
	}

	@Override 
	// Se crea una pelota de basquet.
	public Basquetbol createBasquet() 
	{
		Basquetbol obj = new Basquetbol(BBsound, basquetBall, 200);
  		obj.setDimensions(70f, 70f);
		return obj;
	}

	@Override
	//Se crea una pelota de bolos.
	public Bolos createBowl() 
	{
		Bolos obj = new Bolos(bonk, bolos, 400);
  		obj.setDimensions(80f, 80f);
		return obj;
	}
	
	// Metodo para devolver la instancia del objeto.
	public static BallFactory getFactory()
	{
		if (b == null)
		{
	    	b = new BallFactory();
		}
		return b;
	}
}
