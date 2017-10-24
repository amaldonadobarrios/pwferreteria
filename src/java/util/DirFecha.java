package util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DirFecha 
{
	
	public DirFecha()
	{}
	
	private static DirFecha instance = null;
	//*******************************************************************************************************************
	//*******************************************************************************************************************
	
	public static synchronized DirFecha getInstance() {
		if (instance == null) {		instance = new DirFecha();		}
		return instance;
	}
	
	
	public String  getCambio_Formato(String fechaInput) 
	{
		String formato="";
        //2017-02-15 00:00:00.0
		if( fechaInput!=null )
		{
			String anio = fechaInput.substring(0,4);
			String mes = fechaInput.substring(5,7);
			String dia = fechaInput.substring(8,10);
			formato= dia+"/"+mes+"/"+anio;	
		}
		
		
return formato;
		   }
	
	
	
	  public String getFechaHora() {
	      Locale currentLocale = new Locale(System.getProperty("user.language"),System.getProperty("user.country"));
	      DateFormat formatter =   DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, currentLocale);
	      String fechaHora = formatter.format(new java.util.Date());
	      currentLocale = null;
	      formatter = null;
	      return fechaHora ;
	   }
   
   public String getFechaHoraDIA() {
	      Locale currentLocale = new Locale(System.getProperty("user.language"),System.getProperty("user.country"));
	      DateFormat formatter =   DateFormat.getDateTimeInstance(DateFormat.DATE_FIELD, DateFormat.DATE_FIELD, currentLocale);
	      String fechaHora = formatter.format(new java.util.Date());
	      
	      @SuppressWarnings("unused")
	      Date fechita = new Date();
	      DateFormat formatter2 =   DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, currentLocale);
	      String fechaHora2 = formatter2.format(new java.util.Date());
	  	      
	     
	      @SuppressWarnings("unused")
	      int dd, mm, yy;
		   dd = Integer.parseInt(fechaHora2.charAt(0)+""+fechaHora2.charAt(1 ));
		   mm = Integer.parseInt(fechaHora2.charAt(3)+""+fechaHora2.charAt(4 ));
		   yy = Integer.parseInt(fechaHora2.charAt(6)+""+fechaHora2.charAt(7 ))+ 2000; 
					   
		  String dato = fechaHora2.substring(0,5);
		  fechaHora = dato+"/"+yy;
	      currentLocale = null;
	      formatter = null;
	      return fechaHora ;
	   }
   

@SuppressWarnings("deprecation")
public java.sql.Date getReturnFechaSql(String fecha) {
	   int dd, mm, yy;
	   dd = Integer.parseInt(fecha.charAt(0)+""+fecha.charAt(1 ));
	   mm = Integer.parseInt(fecha.charAt(3)+""+fecha.charAt(4 )) - 1;
	   yy = Integer.parseInt(fecha.charAt(6)+""+fecha.charAt(7 )+""+fecha.charAt(8)+""+fecha.charAt(9)) - 1900; 
	   return (new java.sql.Date(yy,mm,dd));
	   }		   




public String getReturnFechaT_mismoFormato(String fecha) {

	   String dd, mm, yy;
	   String fechanueva ="";
       //0123456789 		   
	   //2009-05-21 00:00:00
	   //16/05/2009
	   dd = (fecha.charAt(8)+""+fecha.charAt(9 ));
	   mm = (fecha.charAt(5)+""+fecha.charAt(6 )) ;
	   yy = (fecha.charAt(0)+""+fecha.charAt(1 )+""+fecha.charAt(2)+""+fecha.charAt(3)) ;
	   fechanueva = dd+"/"+mm+"/"+yy;
	//   System.out.println(fecha);
	//  System.out.println(fechanueva);
	   
	   return fechanueva;
	   }



@SuppressWarnings("deprecation")
public java.sql.Timestamp getReturnFechaT(String fecha) {

	   int dd, mm, yy;
	   dd = Integer.parseInt(fecha.charAt(0)+""+fecha.charAt(1 ));
	   mm = Integer.parseInt(fecha.charAt(3)+""+fecha.charAt(4 )) - 1;
	   yy = Integer.parseInt(fecha.charAt(6)+""+fecha.charAt(7 )+""+fecha.charAt(8)+""+fecha.charAt(9)) - 1900; 
	   return (new java.sql.Timestamp(yy,mm,dd,0,0,0,0));
	   }	

//////para definir rango en la busqueda por envio
@SuppressWarnings("deprecation")
public java.sql.Timestamp getReturnFechaInicioEnvio(String fecha , String envio) 
	{
	Timestamp fechaI = null;
	Timestamp fechaInicial = null; 
	
	String fechaS ="";
	
	 int dd, mm, yy;
	 int hora =0;
	
	
	if(envio.equals("1") )  //desde las 5 pm el dia anterior 
							//hasta las 10 am del dia actual	
	{
		 fechaI = getReturnFechaQuitardia(fecha, 1);
		 fechaS = getReturnFechaSql(fechaI);
		 hora = 14;
	}
	else if(envio.equals("2") )	//desde las 10 am del dia actual
								//hasta las 12 pm del dia actual
	{
		 fechaI = getReturnFechaT(fecha);
		 fechaS = getReturnFechaSql(fechaI);
		 hora = 0;
	}
	else if(envio.equals("3") )	//desde las 12 am del dia actual
								//hasta las 5 pm del dia actual
	{
		fechaI = getReturnFechaT(fecha);
		fechaS = getReturnFechaSql(fechaI);
		 hora = 0;
	}
	
	dd = Integer.parseInt(fechaS.charAt(0)+""+fechaS.charAt(1 ));
	mm = Integer.parseInt(fechaS.charAt(3)+""+fechaS.charAt(4 )) - 1;
	yy = Integer.parseInt(fechaS.charAt(6)+""+fechaS.charAt(7 )+""+fechaS.charAt(8)+""+fechaS.charAt(9)) - 1900; 
	  
	fechaInicial = new java.sql.Timestamp(yy,mm,dd,hora,0,0,0);
	
	System.out.println("fecha Inicio " +fechaInicial);
	return fechaInicial;
	}	

@SuppressWarnings("deprecation")
public java.sql.Timestamp getReturnFechaFinEnvio(String fecha , String envio) 
{
	Timestamp fechaI = null;
	Timestamp fechaInicial = null; 
	
	String fechaS ="";
	
	 int dd, mm, yy;
	 int hora =0;
	
	 fechaI = getReturnFechaT(fecha);
	 fechaS = getReturnFechaSql(fechaI);
	 
	if(envio.equals("1") )  //desde las 5 pm el dia anterior 
							//hasta las 10 am del dia actual	
	{	 hora = 14;		}
	else if(envio.equals("2") )	//desde las 10 am del dia actual
								//hasta las 12 pm del dia actual
	{	 hora = 23;		}
	else if(envio.equals("3") )	//desde las 12 am del dia actual
								//hasta las 5 pm del dia actual
	{	 hora = 23;		}
	
	dd = Integer.parseInt(fechaS.charAt(0)+""+fechaS.charAt(1 ));
	mm = Integer.parseInt(fechaS.charAt(3)+""+fechaS.charAt(4 )) - 1;
	yy = Integer.parseInt(fechaS.charAt(6)+""+fechaS.charAt(7 )+""+fechaS.charAt(8)+""+fechaS.charAt(9)) - 1900; 
	  
	fechaInicial = new java.sql.Timestamp(yy,mm,dd,hora,0,0,0);
	
	System.out.println("fecha final " +fechaInicial);
	return fechaInicial;
	}	

 


@SuppressWarnings("deprecation")
public java.sql.Timestamp getReturnFechaAgregadia(String fecha ,int ndias  ) {

	   // 27/02/2009
		int dd, mm, yy;
		dd = Integer.parseInt(fecha.charAt(0)+""+fecha.charAt(1 ));
		mm = Integer.parseInt(fecha.charAt(3)+""+fecha.charAt(4 )) ;
		yy = Integer.parseInt(fecha.charAt(6)+""+fecha.charAt(7 )+""+fecha.charAt(8)+""+fecha.charAt(9)) - 1900;
	   
	   dd = dd + ndias;
	   
	   if(mm == 2 )
	   {
		   System.out.println("p caso");
		   mm =3 ; dd = dd - 28;
	   }
	   else if(mm == 4||mm ==  6 ||mm ==  9 ||mm == 11 )
	   {
		   System.out.println("t caso"); 
		   mm =mm+1 ; dd = dd -30;
	   }
	   else if(mm == 1||mm ==  3 ||mm ==  5 ||mm == 7 ||mm == 8 ||mm == 10 ||mm == 12)
	   {
		   System.out.println("s caso");
		   mm =mm+1 ; dd = dd -31;
		   if(mm==12)
		   { yy = yy +1 ; }
		   
	   }
	   
	 //  2	28
	 //  1 3 5 7 8 10 12	31
	 //   4	 6 9 11 30
	 
	   //System.out.println("F R "+dd +" "+mm);
	   mm = mm-1;
	  
	   
	   return (new java.sql.Timestamp(yy,mm,dd,0,0,0,0));
	   }


public int devolverNUmeroDias_x_mes(int mes)
{	int numero_dias=0;
	if(mes == 2 )
   {	numero_dias =28;	}
	else if(mes == 4||mes ==  6 ||mes ==  9 ||mes == 11 )
   {   numero_dias =30;		}
	else if(mes == 1||mes ==  3 ||mes ==  5 ||mes == 7 ||mes == 8 ||mes == 10 ||mes == 12)
   {   numero_dias =31;		}
	
	
	return numero_dias;
}

   
@SuppressWarnings("deprecation")
public java.sql.Timestamp getReturnFechaQuitardia(String fecha ,int ndias  ) {

	   // 27/02/2009
		int dd, mm, yy;
		dd = Integer.parseInt(fecha.charAt(0)+""+fecha.charAt(1 ));
		mm = Integer.parseInt(fecha.charAt(3)+""+fecha.charAt(4 )) ;
		yy = Integer.parseInt(fecha.charAt(6)+""+fecha.charAt(7 )+""+fecha.charAt(8)+""+fecha.charAt(9)) - 1900;
	   
		dd =dd - ndias ;
		if (dd < 0)
		{	mm = mm-1;
			if(mm ==0)
			{mm =12;  
			yy = yy -1;
			}
			int numerodias = devolverNUmeroDias_x_mes(mm);
			dd =dd+	numerodias ;
		}
		
	 //  2	28
	 //  1 3 5 7 8 10 12	31
	 //   4	 6 9 11 30
	    //System.out.println("F R "+dd +" "+mm);
	   mm = mm-1;
	   return (new java.sql.Timestamp(yy,mm,dd,0,0,0,0));
	   }


public java.sql.Timestamp getReturnFechaAgregadia2(String fecha ,int ndias  ) {

	Timestamp Tfecha = getReturnFechaAgregadia(fecha,ndias);
	    return Tfecha;
	   }	
   
public String getReturnFechaSql( java.sql.Date fecha) {
	   if (fecha==null){fecha = getReturnFechaSql("00/00/0000");}
	   String Sfecha = new String();
	   Sfecha = fecha.toString();
	   return (Sfecha.charAt(8)+""+Sfecha.charAt(9)+"/"+Sfecha.charAt(5)+""+Sfecha.charAt(6)+"/"+Sfecha.charAt(0)+""+Sfecha.charAt(1)+""+Sfecha.charAt(2)+""+Sfecha.charAt(3)); 
	   }	

public String getReturnFechaSql( java.sql.Timestamp fecha) {
	   if (fecha==null){fecha = getReturnFechaT("00/00/0000");}
	   
	   String Sfecha = new String();
	   Sfecha = fecha.toString();
	   String Fecha = Sfecha.charAt(8)+""+Sfecha.charAt(9)+"/"+Sfecha.charAt(5)+""+Sfecha.charAt(6)+"/"+Sfecha.charAt(0)+""+Sfecha.charAt(1)+""+Sfecha.charAt(2)+""+Sfecha.charAt(3) ;
	   String Retorno = "";
	   if (!Fecha.equals("01/01/1900")){Retorno =Fecha ; }
	   return Retorno;
	   
	   
	   }	

public Timestamp getReturnFechaMas_N_dias( java.sql.Timestamp fecha ,int N_dias ) {
	   
	Timestamp tiempo =null;
	if (fecha==null){fecha = getReturnFechaT("00/00/0000");}
	   
	   String Sfecha = new String();
	   Sfecha = fecha.toString();
	   String Fecha = Sfecha.charAt(8)+""+Sfecha.charAt(9)+"/"+Sfecha.charAt(5)+""+Sfecha.charAt(6)+"/"+Sfecha.charAt(0)+""+Sfecha.charAt(1)+""+Sfecha.charAt(2)+""+Sfecha.charAt(3) ;
	  // String Retorno = "";
	  // if (!Fecha.equals("01/01/1900")){Retorno =Fecha ; }
	   
	   tiempo = getReturnFechaAgregadia(Fecha, N_dias);
	   
	   return tiempo;
	   
	   
	   }	




@SuppressWarnings("deprecation")
public java.util.Date getReturnFecha(String fecha) {
	   int dd, mm, yy;
	   dd = Integer.parseInt(fecha.charAt(0)+""+fecha.charAt(1 ));
	   mm = Integer.parseInt(fecha.charAt(3)+""+fecha.charAt(4 )) - 1;
	   yy = Integer.parseInt(fecha.charAt(6)+""+fecha.charAt(7 )+""+fecha.charAt(8)+""+fecha.charAt(9)) - 1900; 
	   return (new java.util.Date(yy,mm,dd));
	   }	

public String getReturnFecha(java.util.Date fecha) {
	   String Sfecha = new String();
	   Sfecha = fecha.toString();
	   return (Sfecha.charAt(8)+""+Sfecha.charAt(9)+"/"+Sfecha.charAt(5)+""+Sfecha.charAt(6)+"/"+Sfecha.charAt(0)+""+Sfecha.charAt(1)+""+Sfecha.charAt(2)+""+Sfecha.charAt(3)); 
	  
	   }	


@SuppressWarnings("deprecation")
public java.sql.Time getReturnHoraSql(String fecha) {

	   int hh, mm, ss;
	   hh = Integer.parseInt(fecha.charAt(0)+""+fecha.charAt(1 ));
	   mm = Integer.parseInt(fecha.charAt(3)+""+fecha.charAt(4 ));
	   ss = Integer.parseInt(fecha.charAt(6)+""+fecha.charAt(7 )); 
	   return (new java.sql.Time(hh,mm,ss));
	   }	
public  String getReturnHoraSql(java.sql.Time hora) {

	String Shora = new String();
	Shora = hora.toString();
	return (Shora.charAt(0)+""+Shora.charAt(1)+":"+Shora.charAt(3)+""+Shora.charAt(4)+":"+Shora.charAt(6)+""+Shora.charAt(7));
	   }	


@SuppressWarnings("deprecation")
public java.util.Date getReturnHora(String hora) {
	   int hh, mm, ss;
	   hh = Integer.parseInt(hora.charAt(0)+""+hora.charAt(1 ));
	   mm = Integer.parseInt(hora.charAt(3)+""+hora.charAt(4 ));
	   ss = Integer.parseInt(hora.charAt(6)+""+hora.charAt(7 )); 
	   return (new java.util.Date(0,0,0,hh,mm,ss));
	   }

public String getReturnHora(java.util.Date hora) {

	String Shora = new String();
	Shora = hora.toString();
	return (Shora.charAt(0)+""+Shora.charAt(1)+":"+Shora.charAt(3)+""+Shora.charAt(4)+":"+Shora.charAt(6)+""+Shora.charAt(7));

	   }

public java.util.Date getReturnFecha(String fecha,String formato) throws ParseException {
	   return (new SimpleDateFormat(formato).parse(fecha));
}

}
