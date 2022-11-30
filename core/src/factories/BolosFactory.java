package factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import Objetos.Bolos;
import Objetos.Colisionable;

public class BolosFactory implements AbstractFactory
{
	private Texture bolos;
    private Sound bonk;
    
    public BolosFactory()
    {
    	this.bonk = Gdx.audio.newSound(Gdx.files.internal("bonk.ogg"));
		this.bolos = new Texture(Gdx.files.internal("bolos.png"));
    }

	@Override
	public Colisionable createObject() 
	{
		Bolos obj = new Bolos(bonk, bolos, 400);
  		obj.setDimensions(80f, 80f);
		return obj;
	}
}
