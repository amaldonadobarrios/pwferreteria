/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.grilla;

import java.util.List;
import model.dto.Proveedor;

/**
 *
 * @author 31424836
 */
public class LogicTablaProveedor {
     private String INI_TABLA = "<table class=\"table table-striped table-bordered table-hover\" id=\"dataTables-example\">";
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
    private LogicTablaProveedor() {
    }
    
    public static LogicTablaProveedor getInstance() {
        return LogicTablaProveedorHolder.INSTANCE;
    }
    
    private static class LogicTablaProveedorHolder {

        private static final LogicTablaProveedor INSTANCE = new LogicTablaProveedor();
    }
    public String construirGrillaBuscarProveedor (List<Proveedor> lista)
	{
		StringBuilder str = new StringBuilder();
		str.append("<div class=\"table-responsive\">");
		str.append(INI_TABLA);
		StringBuilder cabecera = new StringBuilder();
		cabecera.append(INI_THEAD);
		cabecera.append(INI_TR);
		cabecera.append(INI_TH);	cabecera.append("N°");	cabecera.append(FINI_TH);
		cabecera.append(INI_TH);	cabecera.append("DNI/RUC");			cabecera.append(FINI_TH);
		cabecera.append(INI_TH);	cabecera.append("PERSONA/RAZON SOCIAL ");			cabecera.append(FINI_TH);
		cabecera.append(INI_TH);	cabecera.append("SELECCIONAR");			cabecera.append(FINI_TH);
		cabecera.append(FINI_TR);
		cabecera.append(FIN_THEAD);
		
		str.append(cabecera.toString());
		str.append(INI_TBODY);
		if(  lista!=null && lista.size()>0 )
		{
                    int i=1;
			for(Proveedor fila : lista  )
			{
				str.append(INI_TR);
				str.append(INI_TD);	str.append( i );			str.append(FIN_TD);
				str.append(INI_TD);	str.append( fila.getDniRuc());				str.append(FIN_TD);
				str.append(INI_TD);	str.append( fila.getRazonSocial().concat(" ").concat(fila.getApellidoPaterno()).concat(" ").concat(fila.getApellidoMaterno().concat(" ").concat(fila.getNombres())));			str.append(FIN_TD);
				str.append(INI_TD);	str.append( "<input type=\"button\" value=\"Seleccionar\" onclick=\"fnSeleccionarProveedor('"+fila.getRazonSocial()+"','"+fila.getApellidoPaterno()+"','"+fila.getApellidoMaterno()+"','"+fila.getNombres()+"','"+fila.getDniRuc()+"','"+fila.getDireccion()+"','"+fila.getNaturalezaProveedor()+"','"+fila.getIdProveedor()+"')\" />");		str.append(FIN_TD);
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
