package com.mygdx.game;

public interface Colisionable extends Dibujable
{
	//boolean onColision();
	//Verifica si el arquero colisionó con algún objeto 
	boolean onColision (Arquero gk);
}
