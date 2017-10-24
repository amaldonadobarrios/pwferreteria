package util;

public class BatEncriptador {
	//String KP_PASS = "verimaspol";
	//String KP_PASS = = "%�&/@#$A";
	public final static String KP_PASS =  "ticSolution2017";
	//public final static String KP_PASS = "%v3r1m@sp0l";

	public BatEncriptador() {
	}
	private static BatEncriptador instance = null;
	
	public static synchronized BatEncriptador getInstance()
	{
	   if (instance == null)
	   {   instance = new BatEncriptador();   }
	   return instance;
	}

	
	public static String sumar(char letra, int numero) {
		String retorno = null;
		for (int i = 0; i < numero; i++) {
			++letra;
		}
		retorno = String.valueOf(letra);
		return retorno;
	}
	public static String restar(char letra, int numero) {
		String retorno = null;

		for (int i = 0; i < numero; i++) {
			--letra;
		}
		retorno = String.valueOf(letra);
		return retorno;
	}
	public static String invertir(String cadena) {
		String retorno = null;
		StringBuffer inversa = null;

		inversa = new StringBuffer(cadena);
		inversa.reverse();
		retorno = new String(inversa);
		return retorno;
	}
	public String encriptar(String cadena) {
		String retorno = "";
		cadena = invertir(cadena);
		for (int i = 0; i < cadena.length(); i++) {
			retorno = retorno.concat(sumar(cadena.charAt(i), i + 1));
		}
		retorno = invertir(retorno);
		return retorno;
	}
	public String desencriptar(String cadena) {
		String retorno = "";
		cadena = invertir(cadena);
		for (int i = 0; i < cadena.length(); i++) {
			retorno = retorno.concat(restar(cadena.charAt(i), i + 1));
		}
		retorno = invertir(retorno);
		return retorno;
	}

	
	
	/***
	 * Metodo Encriptador
	 * @param Pass
	 * @return Texto Encriptado
	 */
    public String Encripta(String Pass)
    {	
  	  String Codigo, CAR;
  	  //Constantes clave = new Constantes();
  	  int i;	
  	  String Pass2 = new String("");	
  	  char codigo[],car[];		
  	  for(i=1;i<= Pass.length();i++)
  	  {	    	
  		  CAR = Mid(Pass,i, 1);			
  		  car= CAR.toCharArray();			
  		  Codigo = Mid(KP_PASS, ((i - 1) % KP_PASS.length()) + 1, 1);
  		  codigo= Codigo.toCharArray();
  		  Pass2 = Pass2 + Right("0" + Integer.toHexString((int)codigo[0] ^ (int)car[0]), 2);
  	  }
  	  return Pass2;
    }
    
    /***
	 * Metodo DesenEncriptador
	 * @param Pass
	 * @return Texto Encriptado
	 */
    public String Desencripta(String Pass)
    {	
    	String Codigo, CAR;	
   	  	//Constantes clave = new Constantes();
	  	int i,j=1;		
	  	String Pass2 = new String("");	
	  	char codigo[];//,car[];		
	  	for(i=1;i<= Pass.length();i=i+2)
	  	{			
	  		CAR =Mid(Pass, i, 2);			
	  		//car= CAR.toCharArray();
	  		Codigo = Mid( KP_PASS, ((j - 1) % KP_PASS.length()) + 1, 1);
	  		codigo= Codigo.toCharArray();
	  		Pass2 = Pass2 + (char)((int)codigo[0] ^ Integer.parseInt(CAR, 16));	
	  		j++;		
	  	}
	  	return Pass2;
    } 	
    
    public static String Mid(String cad,int a, int b)
    {
  	  cad = cad.substring(a-1,a + b -1);
  	  return cad;	
    }
    
    public static String Right(String cad,int a)
    {
  	  cad = reverse(cad);
  	  cad = cad.substring(0,a);
  	  cad = reverse(cad);
  	  return cad;
    }
    
    public static String reverse(String source)
    {
  	  int i, len = source.length(); 
  	  StringBuffer dest = new StringBuffer(len);
  	  for (i = (len - 1); i >= 0; i--)
  	  {
  		  dest.append(source.charAt(i));  
  	  }
  	  return dest.toString();    
    }
    
    
	


	

}
