package com.mygdx.game;

import Objetos.Basquetbol;
import Objetos.Bolos;
import Objetos.Futbol;
import Objetos.Tenis;

public interface AbstractFactory 
{
	public Tenis createTenis();
	public Futbol createSoccer();
	public Basquetbol createBasquet();
	public Bolos createBowl();
}
