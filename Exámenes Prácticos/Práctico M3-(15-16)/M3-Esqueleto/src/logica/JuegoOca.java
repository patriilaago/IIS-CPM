package logica;


public class JuegoOca {
	private Tablero tablero;
	private int posFicha;
	private int puntosJugador;
	private int numeroDado;
	private boolean finalizada;
	

	public JuegoOca() {
		inicializarJuego();
	}

	public void inicializarJuego(){
		puntosJugador = 0;
		posFicha = 0;
		numeroDado = 0;
		finalizada = false;
		tablero = new Tablero();
	}
	
 
	public int getPuntosJugador(){
		return puntosJugador;
	}

	public int getPosicionFicha(){
		return posFicha;
	}
	
	public int getNumeroDado() {
		return numeroDado;
	}
    public int lanzarDado(){
    	int dado = Dado.lanzar();
    	//ACTUALIZAR DATOS
    	posFicha = posFicha + dado;
    	
    	
    	//CHECKEAR CAER EN OCA
    	if(posFicha == Tablero.OCAINICIO) {
    		posFicha = Tablero.OCAFIN;
    	}
    	//CHECKEAR FIN
    	if(posFicha >= Tablero.DIM -1) {
    		posFicha = Tablero.DIM -1;
    		finalizada = true;
    	}
    	puntosJugador = puntosJugador+tablero.puntosCasilla(posFicha);
    	
    	return dado;
    }

	public boolean isPartidaFinalizada(){
		return finalizada;
	}
}

