package logica;

public class Tablero {

	public Casilla[] casillas;
	public final static int DIM = 8;
	private final static int MINIMUM_POS = 1;
	private final static int MAXIMUM_POS = 6;
	private static int COLMENA = 0;
	
	public Tablero() {
		casillas = new Casilla[DIM];
		for(int i = 0 ; i< DIM ; i++) {
			casillas[i] = new Casilla();
		}
		COLMENA = (int)(Math.random() * (MAXIMUM_POS - MINIMUM_POS + 1)) + MINIMUM_POS;
		casillas[COLMENA].setValor(-10);
	}
	
	public int getPuntuacionCasilla(int position) {
		return casillas[position].getValor();
	}

	public static int getCOLMENA() {
		return COLMENA;
	}
	
}
