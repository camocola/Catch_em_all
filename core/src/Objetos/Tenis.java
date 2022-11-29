package Objetos;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Tenis extends Balon
{
	public Tenis(Sound s, Texture i, int vY)
	{
		super(s,i,vY);
	}
	
	@Override
	public void onColision(Arquero gk) 
	{
		points(gk);
		playSound();
	}

	@Override
	//Si el arquero atrapa la pelota suma 15 puntos
	public void points(Arquero gk) 
	{
		gk.sumarPuntos(25);
	}

	@Override
	public void effect(Arquero gk) 
	{
		gk.buffear(2f);
	}
	
}
