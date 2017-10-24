package util;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

//import org.apache.poi.ss.usermodel.Workbook;
//autor Enrique Rivera Pizarro
public class HtmlUtil  implements Serializable 
{
	
	private static final long serialVersionUID = 1L;

	private static HtmlUtil instance = null;
	//************************************
	public HtmlUtil() 
	{		}
			
	public static synchronized HtmlUtil getInstance() 
	{
		if (instance == null) {		instance = new HtmlUtil();		}
		return instance;
	}
	//************************************
		
	
	public void escrituraHTML(HttpServletResponse response, String htmltexto) {
		response.setContentType("text/html; charset=UTF-8");
		try {//--05/07/2016 version para la presentacion 
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"), true);
			String cadena = htmltexto;
			writer.print(cadena);
			writer.flush();
			writer.close();
		} catch (IOException ex) {
			//depurador.info("ERROR " + ex.getMessage());
		}
	}
	
	public void escritura(HttpServletResponse response, String htmltexto) 
	{ //response.setContentType("application/json;charset=UTF-8");
		try {
			PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF8"), true);
						out.print(htmltexto);
						out.flush();
						out.close();
		} catch (IOException ex) {}
	}
	
	

	
	
	
}
