package com.mygdx.game;

public interface Movible 
{
	// mueve al objeto
	public void move();
	
	// Verifica que el objeto se pueda seguir moviendo
	public boolean outOfBounds();
}
