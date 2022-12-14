package controladorObjetos;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import Objetos.Colisionable;
import factories.ObjectFactory;

public class ColeccionColisionable 
{
	private static ColeccionColisionable c;
	private Array<Colisionable> objectPos;
	private long lastDropTime;
	
	private ColeccionColisionable()
	{
		objectPos = new Array<Colisionable>();
	}
	
	
	// Crea distintos tipos de objetos de manera aleatoria
	public void createObject() 
	{
		ObjectFactory of = new ObjectFactory();
		objectPos.add(of.createColisionableObject());     
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
	
	// Devuelve el tiempo en donde se generó el ultimpo objecto.
	public long getDropTime()
	{
		return lastDropTime;
	}
	
	// Devuelve la instancia de la clase (singleton).
	public static ColeccionColisionable getInstance()
	{
		if (c == null)
		{
			c = new ColeccionColisionable();
		}
		return c;
	}
}
