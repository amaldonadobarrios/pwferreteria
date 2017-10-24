/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.grilla;

import java.text.DecimalFormat;
import java.util.List;
import model.dto.ListaVenta;

/**
 *
 * @author 31424836
 */
public class LogicTablaVenta {
     private String INI_TABLA = "<table class=\"table table-striped table-bordered table-hover\" id=\"dynamic-table\">";
    private String INI_THEAD = "<thead>";
    private String INI_TR = "<tr>";
    private String INI_TH = "<th>";
    private String FINI_TH = "</th>";
    private String FINI_TR = "</tr>";
    private String FIN_THEAD = "</thead>";
    private String INI_TBODY = "<tbody>";
    private String FIN_TBODY = "</tbody>";
    private String FIN_TABLA = "</table>";
    private String INI_TD = "<td>";
    private String FIN_TD = "</td>";
    DecimalFormat formateadorCodigo = new DecimalFormat("0000");
    DecimalFormat formateadorDecimal = new DecimalFormat("#######0.00");
    private LogicTablaVenta() {
    }
    
    public static LogicTablaVenta getInstance() {
        return LogicTablaVentaHolder.INSTANCE;
    }
    
    private static class LogicTablaVentaHolder {

        private static final LogicTablaVenta INSTANCE = new LogicTablaVenta();
    }
    public String construirGrillaVenta (List<ListaVenta> lista)
	{
		StringBuilder str = new StringBuilder();
		str.append("<div class=\"table-responsive\">");
		str.append(INI_TABLA);
		StringBuilder cabecera = new StringBuilder();
		cabecera.append(INI_THEAD);
		cabecera.append(INI_TR);
		cabecera.append(INI_TH);	cabecera.append("N°");	cabecera.append(FINI_TH);
		cabecera.append(INI_TH);	cabecera.append("CODIGO");			cabecera.append(FINI_TH);
		cabecera.append(INI_TH);	cabecera.append("DESCRIPCION ");			cabecera.append(FINI_TH);
		cabecera.append(INI_TH);	cabecera.append("MARCA");			cabecera.append(FINI_TH);
                  cabecera.append(INI_TH);	cabecera.append("CANTIDAD");			cabecera.append(FINI_TH);
                cabecera.append(INI_TH);	cabecera.append("SUBTOTAL");			cabecera.append(FINI_TH);
                cabecera.append(INI_TH);	cabecera.append("ELIMINAR");			cabecera.append(FINI_TH);
		cabecera.append(FINI_TR);
		cabecera.append(FIN_THEAD);
		
		str.append(cabecera.toString());
		str.append(INI_TBODY);
		if(  lista!=null && lista.size()>0 )
		{
                    int i=1;
			for(ListaVenta fila : lista  )
			{
				str.append(INI_TR);
				str.append(INI_TD);	str.append( i );			str.append(FIN_TD);
				str.append(INI_TD);	str.append( formateadorCodigo.format(Integer.parseInt(fila.getId_producto())));				str.append(FIN_TD);
                                str.append(INI_TD);	str.append( fila.getDescripcion().concat("(").concat(fila.getPresentacion()).concat(")"));				str.append(FIN_TD);
                                str.append(INI_TD);	str.append( fila.getMarca());				str.append(FIN_TD);
                                str.append(INI_TD);	str.append( formateadorDecimal.format(fila.getCantidad()));				str.append(FIN_TD);
                                str.append(INI_TD);	str.append( formateadorDecimal.format(fila.getPrecio()));				str.append(FIN_TD);
				str.append(INI_TD);	str.append( "<input type=\"button\" value=\"Eliminar\" onclick=\"fneliminarItem('"+i+"');\"");				str.append(FIN_TD);
//				str.append(INI_TD);	str.append( "<input type=\"button\" value=\"Seleccionar\" onclick=\"fnSeleccionarCliente('"+fila.getRazonSocial()+"','"+fila.getApellidoPaterno()+"','"+fila.getApellidoMaterno()+"','"+fila.getNombres()+"','"+fila.getDniRuc()+"','"+fila.getDireccion()+"','"+fila.getNaturalezaCliente()+"','"+fila.getIdTipoCliente()+"','"+fila.getIdCliente()+"')\" />");		str.append(FIN_TD);
				str.append(FINI_TR);
			i++;	
			}
			
			
		}
		str.append(FIN_TBODY);
		str.append(FIN_TABLA);
		str.append("</div>");
		return str.toString();
	}
}
