package logica;

public class Tablero {

	public static final int DIM = 8;
	public static final int ESCALERA_INICIO_INDEX = 2;
	public static final int ESCALERA_FIN_INDEX = 5;
	
	private Casilla[] casillas;

	public Tablero() {
		casillas = new Casilla[DIM];
		for (int i = 0; i < DIM; i++) {
			casillas[i] = new Casilla();
		}
		casillas[ESCALERA_INICIO_INDEX].setValor(200);
	}
	
	public Casilla getCasilla(int index) {
		return casillas[index];
	}
	
	public int getSize() {
		return casillas.length;
	}
}

