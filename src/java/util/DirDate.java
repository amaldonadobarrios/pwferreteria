package util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DirDate 
{
	
	private static DirDate instance = null;
	//************************************
	
			
	public static synchronized DirDate getInstance() 
	{
		if (instance == null) {		instance = new DirDate();		}
		return instance;
	}
	//************************************
	
	
	
	public DirDate()
	{}
	
	
	/** Metodo que recibe una fecha  en  una cadena y
     * retorna la fecha en un java.sql.Date
     */
public   java.sql.Date   deStringaDate(String  fecha){
    SimpleDateFormat  sdf = new SimpleDateFormat("dd/MM/yyyy"); 
    java.util.Date  d1 = new   java.util.Date(); //fecha  del sistema       
    try {	d1 = sdf.parse(fecha);	} 
    catch (ParseException e) { 	e.printStackTrace();	} 
    java.sql.Date  d2 = new java.sql.Date(d1.getTime());  
    return d2; 
}
    /* Retornar  la fecha del sistema  en  un java.sql.Date
     */
public   java.sql.Date    getFechaSistema(){
      java.util.Date  d1 = new   java.util.Date(); //fecha  del sistema
      java.sql.Date  d2 = new java.sql.Date(d1.getTime());
     return d2; 
}

public  String  convertUtilDate_String_DDMMYYYY(java.util.Date  d1){
    String    x ;
    SimpleDateFormat  sdf = new SimpleDateFormat("dd/MM/yyyy");
     java.sql.Date  d2 = new java.sql.Date(d1.getTime());
    d1= new java.util.Date(d2.getTime());       
    x= sdf.format(d1);   
    return x;
}

public  String  convertUtilDate_String_YYYYMMDD(java.util.Date  d1){
    String    x ;
    SimpleDateFormat  sdf = new SimpleDateFormat("yyyyMMdd");
     java.sql.Date  d2 = new java.sql.Date(d1.getTime());
    d1= new java.util.Date(d2.getTime());       
    x= sdf.format(d1);   
    return x;
}



/**
 * Metodo que sirve para hhalar la fecha actual del sistem y agregar dias 
 * @param cantDias
 * @return
 */
public   java.sql.Date    getFechaSistemaAgregarDias(int cantDias){
	Calendar c1 = Calendar.getInstance();
	c1.add(Calendar.DATE,cantDias); // agregar dias a una fecha
	java.util.Date fechas = c1.getTime();
	java.sql.Date  d2 = new java.sql.Date(fechas.getTime());
   return d2; 
}

public   java.sql.Date    getFechaAgregarDias(java.util.Date fechasIni ,  int cantDias){
	Calendar c1 = Calendar.getInstance();
	c1.setTime(fechasIni);
	c1.add(Calendar.DATE,cantDias); // agregar dias a una fecha
	java.util.Date fechas = c1.getTime();
	java.sql.Date  d2 = new java.sql.Date(fechas.getTime());
   return d2; 
}

public   String    getFechaAgregarDiasCAD(String fecha ,  int cantDias , String tipo  ){
		
	Calendar c1 = Calendar.getInstance();
	java.sql.Date fechasIni  = deStringaDate(fecha);
	c1.setTime(fechasIni);
	
	if(tipo.equals("D"))
	{	c1.add(Calendar.DATE,cantDias); // agregar dias a una fecha
	}
	if(tipo.equals("M"))
	{	c1.add(Calendar.MONTH,cantDias);
	}
	if(tipo.equals("Y"))
	{	c1.add(Calendar.YEAR,cantDias);
	}
	
	java.util.Date fechas = c1.getTime();
	String fechafin= convertUtilDate_String_DDMMYYYY(fechas);
	return fechafin; 
}


/* retorna la fecha  y Hora del sistema
 */
public   java.sql.Timestamp    getFechaHoraSistema(){
      java.util.Date  d1 = new   java.util.Date(); //fecha  del sistema
     return new Timestamp(d1.getTime());
}


public   java.sql.Timestamp    getFechaHoraTimestamp(String fecha){
	 SimpleDateFormat  sdf = new SimpleDateFormat("dd/MM/yyyy"); 
	    java.util.Date  d1 = new   java.util.Date(); //fecha  del sistema       
	    try {	d1 = sdf.parse(fecha);	} 
	    catch (ParseException e) { 	e.printStackTrace();	} 
	    java.sql.Date  d2 = new java.sql.Date(d1.getTime()); 

   return new Timestamp(d2.getTime());
}


public  String  getFechaYYYY(){
    String    x ;
    SimpleDateFormat  sdf = new SimpleDateFormat("yyyy");
    java.util.Date  d1 = new   java.util.Date(); //fecha  del sistema
    d1= new java.util.Date();       
    x= sdf.format(d1);   
    return x;
}

public  String  getFechaDDMMYYYY(){
    String    x ;
    SimpleDateFormat  sdf = new SimpleDateFormat("dd/MM/yyyy");
    java.util.Date  d1 = new   java.util.Date(); //fecha  del sistema
    d1= new java.util.Date();       
    x= sdf.format(d1);   
    return x;
}

public  String  getFechaDDMMYYYY(java.sql.Date  date){
    String    x ;
    SimpleDateFormat  sdf = new SimpleDateFormat("dd/MM/yyyy");
    java.util.Date  d1 = new   java.util.Date(); //fecha  del sistema
    d1= new java.util.Date(date.getTime());       
    x= sdf.format(d1);   
    return x;
}

public  String  getFechaDDMMYYYY_HHMMSS(java.sql.Timestamp  date){
    String    x ;
    SimpleDateFormat  sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    java.util.Date  d1 = new   java.util.Date(); //fecha  del sistema
    d1= new java.util.Date(date.getTime());       
    x= sdf.format(d1);          
    return x;
}

public  String  getFechaDDMMYYYY_HHMMSS_DOC(java.sql.Timestamp  date){
    String    x ;
    SimpleDateFormat  sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
    java.util.Date  d1 = new   java.util.Date(); //fecha  del sistema
    d1= new java.util.Date(date.getTime());       
    x= sdf.format(d1);          
    return x;
}


public String obtenerDiaSemana() 
{	Calendar ahoraCal = Calendar.getInstance();
	String diasemana = ahoraCal.get(Calendar.DAY_OF_WEEK) + "" ;
	return diasemana;
}

public  String  getHoraSistema(){
	 String    x ;
	    SimpleDateFormat  sdf = new SimpleDateFormat("HH");
	    java.util.Date  d1 = new   java.util.Date(); //fecha  del sistema
	    d1= new java.util.Date();       
	    x= sdf.format(d1);   
	    return x;
}


public  String  getFechaDDMMYYYY_Correlativo(){
    String    x ;
    SimpleDateFormat  sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    java.util.Date  d1 = new   java.util.Date(); //fecha  del sistema
    d1= new java.util.Date();       
    x= sdf.format(d1);   
    return x;
}

///CAMBIO DE FORMATO DE YYYY-MM-DD A 
public  String  cambioFormato(String fecchaIN){
    String    x ;   
    Date fechadd =null ;	
	SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");	
	try {	fechadd = formatoDelTexto.parse(fecchaIN);
	} catch (ParseException e) {	System.out.println("error");		}	
	SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");	
	x = formatoDeFecha.format(fechadd);    
    return x;
}

}
