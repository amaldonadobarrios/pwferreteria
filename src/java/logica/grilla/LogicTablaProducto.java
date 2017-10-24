/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.grilla;

import com.google.gson.Gson;
import java.text.DecimalFormat;
import java.util.List;
import model.dto.Producto;

/**
 *
 * @author Alexander
 */
public class LogicTablaProducto {
    private String INI_TABLA = "<table class=\"table table-striped table-bordered table-hover\" id=\"dynamic-table1\">";
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
    private LogicTablaProducto() {
    }
    
    public static LogicTablaProducto getInstance() {
        return LogicTablaProductoHolder.INSTANCE;
    }
    
    private static class LogicTablaProductoHolder {

        private static final LogicTablaProducto INSTANCE = new LogicTablaProducto();
    }
    public String construirGrillaProducto (List<Producto> lista)
	{
                StringBuilder str = new StringBuilder();
		str.append("<div class=\"table-responsive\">");
		str.append(INI_TABLA);
		StringBuilder cabecera = new StringBuilder();
		cabecera.append(INI_THEAD);
		cabecera.append(INI_TR);
		cabecera.append(INI_TH);	cabecera.append("N°");	cabecera.append(FINI_TH);
		cabecera.append(INI_TH);	cabecera.append("DESCRIPCION ");			cabecera.append(FINI_TH);
		cabecera.append(INI_TH);	cabecera.append("MARCA");			cabecera.append(FINI_TH);
                      cabecera.append(INI_TH);	cabecera.append("PRESENTACION");			cabecera.append(FINI_TH);
                      cabecera.append(INI_TH);	cabecera.append("TIPO");			cabecera.append(FINI_TH);
                cabecera.append(INI_TH);	cabecera.append("MEDIDA");			cabecera.append(FINI_TH);
                cabecera.append(INI_TH);	cabecera.append("SELECCIONAR");			cabecera.append(FINI_TH);
		cabecera.append(FINI_TR);
		cabecera.append(FIN_THEAD);
		
		str.append(cabecera.toString());
		str.append(INI_TBODY);
		if(  lista!=null && lista.size()>0 )
		{
                    int i=1;
			for(Producto fila : lista  )
			{
				str.append(INI_TR);
				str.append(INI_TD);	str.append( i );			str.append(FIN_TD);
				str.append(INI_TD);	str.append( fila.getDescripcion());				str.append(FIN_TD);
                                str.append(INI_TD);	str.append( fila.getMarca());				str.append(FIN_TD);
                                str.append(INI_TD);	str.append( fila.getPresentacion());				str.append(FIN_TD);
                                str.append(INI_TD);	str.append( fila.getProd_insu());				str.append(FIN_TD);
                               str.append(INI_TD);	str.append( fila.getMedida());				str.append(FIN_TD);
                               str.append(INI_TD);	str.append( "<input type=\"button\" value=\"Seleccionar\" onclick=\"fn_pintar_producto('"+fila.getId_producto()+"','"+fila.getDescripcion()+"','"+fila.getMarca()+"','"+fila.getPresentacion()+"','"+fila.getMedida()+"','"+fila.getStringBase64()+"');\"");				str.append(FIN_TD);
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
