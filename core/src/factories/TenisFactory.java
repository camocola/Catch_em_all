package factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import Objetos.Colisionable;
import Objetos.Tenis;

public class TenisFactory implements AbstractFactory
{
	private Texture tenis;
    private Sound Tsound;
    
    public TenisFactory()
    {
    	this.Tsound = Gdx.audio.newSound(Gdx.files.internal("tenis.wav"));
		this.tenis = new Texture(Gdx.files.internal("tenis.png"));
    }
	@Override
	public Colisionable createColisionableObject() 
	{
		Tenis obj = new Tenis(Tsound, tenis, 200);
  		obj.setDimensions(40f, 40f);
		return obj;
	}

}
