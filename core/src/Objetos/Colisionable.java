package Objetos;

public interface Colisionable extends Movible
{
	// Verifica si el arquero colisiona con algun objeto.
	public boolean checkColision(Arquero gk);
	// Evento al chocar con cierto tipo de objeto 
	public void onColision (Arquero gk);
}
