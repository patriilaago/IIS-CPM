package logica;

public class Dado {
	
	private static double VALORDADO = 4;

	public static int lanzar ()
	{
		int resultado =  (int) (Math.random() * VALORDADO ) + 1;
		return resultado;
	}
}
