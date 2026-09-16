package logica;

public class OsoMiel {

	private boolean finalizada;
	private Tablero tablero;
	private int puntosJugador;
	private int posicionJugador;
	private int dado;
	
	public OsoMiel() {
		inicializarJuego();
	}
	
	private void inicializarJuego() {
		// TODO Auto-generated method stub
		finalizada = false;
		tablero = new Tablero();
		puntosJugador = 0;
		posicionJugador = 0;
		dado = 0;
	}
	
	public int lanzarDado() {
		int dado = Dado.lanzar();
		posicionJugador = posicionJugador + dado;
		
		if(posicionJugador == Tablero.getCOLMENA()) {
			posicionJugador = 0;
		}
		
		if(posicionJugador >= Tablero.DIM-1) {
			posicionJugador = Tablero.DIM-1;
			finalizada = true;
		}
		
		puntosJugador = puntosJugador + tablero.getPuntuacionCasilla(posicionJugador);
		
		return dado;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public int getPuntosJugador() {
		return puntosJugador;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public int getPosicionJugador() {
		return posicionJugador;
	}

	public void setPosicionJugador(int posicionJugador) {
		this.posicionJugador = posicionJugador;
	}
	
	
}
