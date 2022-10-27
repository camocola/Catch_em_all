package com.mygdx.game;

public interface Colisionable extends Dibujable
{
	//Verifica si el arquero colisionó con algún objeto 
	boolean onColision (Arquero gk);
}
