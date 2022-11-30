package factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import Objetos.Colisionable;
import Objetos.Futbol;

public class FutbolFactory implements AbstractFactory
{
	private Texture soccerBall;
	private Sound Fsound;
	
	public FutbolFactory()
	{
		this.Fsound = Gdx.audio.newSound(Gdx.files.internal("futbol.wav"));
    	this.soccerBall = new Texture(Gdx.files.internal("drop.png"));
	}
	@Override
	public Colisionable createObject() 
	{
		Futbol obj = new Futbol(Fsound, soccerBall, 200);
  		obj.setDimensions(60f, 60f);
		return obj;
	}

}
