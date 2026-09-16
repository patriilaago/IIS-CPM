package logica;


public class Tablero {

	private Casilla[] casillas;
	private int SKATE = 0;
	public final static int DIM = 10;
	private final static int MINIMUM_POS = 1;
	private final static int MAX_POS = 8;
	
	
	public Tablero() {
		casillas = new Casilla[DIM];
		for(int i = 0 ; i<DIM ; i++) {
			casillas[i] = new Casilla();
		}
		SKATE = (int)(Math.random() * (MAX_POS - MINIMUM_POS  + 1)) + MINIMUM_POS ;
		casillas[SKATE].setValor(270);
	}
	
	public Casilla[] getCasillas() {
		return casillas;
	}
	
	public int getValorCasilla(int position) {
		return casillas[position].getValor();
	}

	public int getSKATE() {
		return SKATE;
	}
}
