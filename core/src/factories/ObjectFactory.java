package factories;

import com.badlogic.gdx.math.MathUtils;
import Objetos.Colisionable;

public class ObjectFactory
{	
	public Colisionable createColisionableObject()
	{
		int r = MathUtils.random(1,5);
		AbstractFactory a = null;
		switch (r)
		{
			case 1:
			{
				a = new FutbolFactory();
				break;
			}
			case 2:
			{
				a = new BengalaFactory();
				break;
			}
			case 3:
			{
				a = new BasquetFactory();
				break;
			}
			case 4:
			{
				a = new TenisFactory();
				break;
			}
			case 5:
			{
				a = new BolosFactory();
				break;
			}
		}
		return a.createColisionableObject();
	}	
}
