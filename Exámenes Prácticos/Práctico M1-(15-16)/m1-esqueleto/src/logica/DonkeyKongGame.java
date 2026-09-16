package logica;

public class DonkeyKongGame {
	
	private int posFicha;
	private int puntos;
	private Tablero tablero;
	private boolean finalizada;
	
	public DonkeyKongGame() {
		inicializar();
	}
	
	public void inicializar() {
		tablero = new Tablero();
		posFicha = 0;
		puntos = 0;
		finalizada = false;
	}

	public int getPosicionFicha() {
		return posFicha;
	}
	
	public int getPuntos() {
		return puntos;
	}
	
	public boolean isFinalizada() {
		return finalizada;
	}
	
	public int tirar() {
		if(finalizada) {
			return 0;
		}
			
		int dado = Dado.lanzar();
		posFicha += dado;
		if (posFicha >= Tablero.DIM - 1) {
			posFicha = Tablero.DIM - 1;
			finalizada = true;
		}
		
		puntos += tablero.getCasilla(posFicha).getValor();
		
		if (posFicha == Tablero.ESCALERA_INICIO_INDEX) {
			posFicha = Tablero.ESCALERA_FIN_INDEX;
			puntos += tablero.getCasilla(posFicha).getValor();
		}
		
		return dado;
	}
	
	public Tablero getTablero() {
		return tablero;
	}
}


