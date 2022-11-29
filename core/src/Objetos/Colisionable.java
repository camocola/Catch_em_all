package Objetos;

public interface Colisionable extends Movible, Effect
{
	// Verifica si el arquero colisiona con algun objeto.
	boolean checkColision(Arquero gk);
	// Evento al chocar con cierto tipo de objeto 
	void onColision (Arquero gk);
}
