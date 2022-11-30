package factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import Objetos.Bengala;
import Objetos.Colisionable;

public class BengalaFactory implements AbstractFactory
{
	private Texture bengala;
    private Sound FWsound;
    
    public BengalaFactory()
    {
		this.bengala = new Texture(Gdx.files.internal("bengala.png"));
		this.FWsound = Gdx.audio.newSound(Gdx.files.internal("fuegoArtificial.wav"));
    }
    
	@Override
	public Colisionable createObject() 
	{
		Bengala obj = new Bengala(FWsound, bengala, 200, 200);
		obj.setDimensions(30f, 30f);
		return obj;
	}


}
