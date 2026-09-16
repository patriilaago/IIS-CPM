package logica;

public class Tablero {
	public static final int DIM = 9;
	public static final int POSICION_META = 8;
	public static final int POSICION_INICIO = 8;
	public static int FIRST_INVADER = 0;
	public static int SECOND_INVADER = 0;
	private final static int MINIMUM_POS = 1;
	private final static int MAXIMUM_POS = 7;

	private Casilla[] casillas;

	public Tablero () {
		casillas = new Casilla[DIM];
		for (int i = 0; i < DIM; i++) {
			casillas[i] = new Casilla();
		}
		
		FIRST_INVADER = (int)(Math.random() * (MAXIMUM_POS - MINIMUM_POS + 1)) + MINIMUM_POS;
		
		do {
			SECOND_INVADER = (int)(Math.random() * (MAXIMUM_POS - MINIMUM_POS + 1)) + MINIMUM_POS;
		}while(FIRST_INVADER == SECOND_INVADER);
		casillas[FIRST_INVADER].setValor(300);
		casillas[SECOND_INVADER].setValor(300);
	}

	public int getPuntosCasilla(int posicion) {
		return casillas[posicion].getValor(); 
	}
	
	public Casilla getCasilla(int posicion) {
		return casillas[posicion];
	}
	
	public int getFirstInvasor() {
		return FIRST_INVADER;
	}
	
	public int getSecondInvasor() {
		return SECOND_INVADER;
	}
}
