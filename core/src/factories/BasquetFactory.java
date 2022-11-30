package factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import Objetos.Basquetbol;
import Objetos.Colisionable;

public class BasquetFactory implements AbstractFactory
{
    private Texture basquetBall;
    private Sound BBsound;
    
    public BasquetFactory()
    {
    	this.BBsound = Gdx.audio.newSound(Gdx.files.internal("basketball.ogg"));
    	this.basquetBall = new Texture(Gdx.files.internal("basquetBall.png"));
    }
	@Override
	public Colisionable createObject() 
	{
		Basquetbol obj = new Basquetbol(BBsound, basquetBall, 200);
  		obj.setDimensions(70f, 70f);
		return obj;
	}

}
