package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public interface Colisionable
{
	boolean onColision();
	boolean verColision (Rectangle obj, Arquero goalkeeper, int i);
}
