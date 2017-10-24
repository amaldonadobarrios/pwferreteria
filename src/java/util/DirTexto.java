package com.mi.diredu.util;

import java.io.UnsupportedEncodingException;

public class DirTexto 
{
	
	private static DirTexto instance = null;
	//************************************
	public DirTexto() 
	{		}
			
	public static synchronized DirTexto getInstance() 
	{
		if (instance == null) {		instance = new DirTexto();		}
		return instance;
	}
	//************************************
	
	
	public String cambiarFormatoUTF8(String parametrp)
	{
		String nuevoParam ="";
		
		
		if( parametrp !=null )
		{
			try {
				nuevoParam = new String( parametrp.getBytes("ISO-8859-1") , "UTF-8");
			} catch (UnsupportedEncodingException e) {
				nuevoParam ="";
			}
		}
		return nuevoParam;
		
		
	}
	
	

}
