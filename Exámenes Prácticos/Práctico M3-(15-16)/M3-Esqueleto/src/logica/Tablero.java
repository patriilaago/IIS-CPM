package logica;

public class Tablero {
	public static final int DIM = 10;
	public static final int POSICION_META = 0;
	public static final int OCAINICIO = 3;
	public static final int OCAFIN = 6;

	private Casilla[] casillas = new Casilla[DIM];

	public Tablero () {
		for (int i = 0; i < DIM; i++) {
			casillas[i] = new Casilla();
		}
		casillas[OCAINICIO].setValor(270);
	}

	public int puntosCasilla(int posicion) {
		return casillas[posicion].getValor();
	}
	
	public Casilla getCasilla(int posicion) {
		return casillas[posicion];
	}
}
