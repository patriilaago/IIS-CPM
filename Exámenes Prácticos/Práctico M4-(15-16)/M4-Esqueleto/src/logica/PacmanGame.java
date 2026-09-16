package logica;


public class PacmanGame {
	private Tablero tablero;
	private int posFicha;
	private int posFantasma;
	private int puntosJugador;
	private int numeroDado;
	private boolean finalizada;
	private boolean gameOver;
	

	public PacmanGame() {
		inicializarJuego();
	}

	public void inicializarJuego(){
		puntosJugador = 0;
		posFantasma = Tablero.GHOST;
		posFicha = 0;
		numeroDado = 0;
		finalizada = false;
		tablero = new Tablero();
	}
	
	public void setPuntos(int puntos) {
		this.puntosJugador = puntos;
	}
 
	public int getPuntosJugador(){
		return puntosJugador;
	}

	public int getPosicionFicha(){
		return posFicha;
	}
	
	public int getPosFantasma() {
		return posFantasma;
	}

	public int getNumeroDado() {
		return numeroDado;
	}
    public int lanzarDado() {
    	if(finalizada) {
			return 0;
		}
    	int dado = Dado.lanzar();
		posFicha += dado;
		if (posFicha >= Tablero.DIM - 1) {//LLEGA AL FINAL
			posFicha = Tablero.DIM - 1;
			finalizada = true;
		}
		
		puntosJugador += tablero.getCasilla(posFicha).getValor();
		
		if(posFicha==Tablero.GHOST) {
			gameOver = true;
			setPuntos(0);
		}
		
		return dado;
    }
	public boolean isPartidaFinalizada(){
		finalizada = false;
		if (posFicha == Tablero.POSICION_META)
			finalizada = true;
		return finalizada;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
}

