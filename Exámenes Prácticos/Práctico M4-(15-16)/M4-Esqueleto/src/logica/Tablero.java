package logica;

public class Tablero {
	public static final int DIM = 9;
	public static final int POSICION_META = 8;
	public static int GHOST = 5;
	
	private Casilla[] casillas = new Casilla[DIM];

	public Tablero () {
		for (int i = 0; i < DIM; i++) {
			casillas[i] = new Casilla();
		}
		//GHOST = (int) (Math.random() * (DIM-2)) + 1;
		casillas[GHOST].setValor(0);
	}

	public int puntosCasilla(int posicion) {
		return casillas[posicion].getValor();
	}
	
	public Casilla getCasilla(int posicion) {
		return casillas[posicion];
	}
	
	public Casilla[] getCasillas() {
		return casillas;
	}
}
