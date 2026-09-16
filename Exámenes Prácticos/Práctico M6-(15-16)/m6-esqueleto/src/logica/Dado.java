package logica;

public class Dado {
	
	private static int VALORDADO =2;
	
	public static int lanzar ()
	{
		int resultado =  (int) (Math.random() * VALORDADO) + 1;
		return resultado;
	}

	public int getVALORDADO() {
		return VALORDADO;
	}

	public static void setVALORDADO(int vALORDADO) {
		VALORDADO = vALORDADO;
	}

}
