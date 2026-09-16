package logica;


public class SpaceInvaders {
	private Tablero tablero;
	private int posicionNave;
	private int puntos;
	private int numeroDado;
	private boolean finalizada;
	private boolean invader;
	

	public SpaceInvaders() {
		inicializarJuego();
	}

	public void inicializarJuego(){
		puntos = 0;
		posicionNave = 0;
		numeroDado = 0;
		finalizada = false;
		invader = true;
		tablero = new Tablero();
	}
	
 
	public int getPuntos(){
		return puntos;
	}

	public int getPosicionNave(){
		return posicionNave;
	}
	
	public int getNumeroDado() {
		return numeroDado;
	}
    
	public int lanzarDado(){

    	numeroDado = Dado.lanzar();
    	
    	posicionNave = posicionNave +numeroDado;
    	
    	if(posicionNave>=Tablero.DIM-1) {
    		posicionNave = Tablero.DIM-1;
    		finalizada = true;
    	}
    	
    	puntos = puntos + tablero.getPuntosCasilla(posicionNave);
    	return numeroDado;
    }


    public boolean isPartidaFinalizada(){
		return finalizada;
	}

	public boolean isInvader() {
		return invader;
		
	}
	
	public Tablero getTablero() {
		return tablero;
	}
}

