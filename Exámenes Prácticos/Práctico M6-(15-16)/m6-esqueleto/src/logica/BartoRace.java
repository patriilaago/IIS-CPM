package logica;

public class BartoRace {

	private boolean tieneSkate;
	private boolean finalizada;
	private Tablero tablero;
	private int puntosJugador;
	private int posicionJugador;
	private int dado;
	
	public BartoRace() {
		inicializarJuego();
	}

	public void inicializarJuego() {
		// TODO Auto-generated method stub
		tieneSkate = false;
		finalizada = false;
		tablero = new Tablero();
		puntosJugador = 0;
		posicionJugador = 0;
		dado = 0;
	}

	public boolean tieneSkate() {
		return tieneSkate;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public int getPuntosJugador() {
		return puntosJugador;
	}

	public int getPosicionJugador() {
		return posicionJugador;
	}
	
	public Tablero getTablero() {
		return tablero;
	}
	
	public int lanzarDado() {
		int dado = Dado.lanzar();
		posicionJugador = posicionJugador + dado;
		
		if(posicionJugador == tablero.getSKATE()) {
			tieneSkate =true;
			Dado.setVALORDADO(4);
		}
		
		if(posicionJugador>= Tablero.DIM -1) {
			posicionJugador = Tablero.DIM -1;
			finalizada = true;
		}
		
		puntosJugador = puntosJugador + tablero.getValorCasilla(posicionJugador);
		
		return dado;
	}
}
