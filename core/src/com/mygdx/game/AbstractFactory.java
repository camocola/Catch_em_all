package com.mygdx.game;

public interface AbstractFactory 
{
	public Tenis createTenis();
	public Futbol createSoccer();
	public Basquetbol createBasquet();
	public Bolos createBowl();
}
