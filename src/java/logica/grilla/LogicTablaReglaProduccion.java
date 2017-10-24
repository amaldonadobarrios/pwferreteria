/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.grilla;

import java.text.DecimalFormat;
import java.util.List;
import model.dto.ListaProduccion;
import model.dto.ListaReglaProduccion;

/**
 *
 * @author 31424836
 */
public class LogicTablaReglaProduccion {

    private String INI_TABLA = "<table class=\"table table-striped table-bordered table-hover\" id=\"dynamic-table2\">";
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

    private LogicTablaReglaProduccion() {
    }

    public static LogicTablaReglaProduccion getInstance() {
        return LogicTablaReglaProduccionHolder.INSTANCE;
    }

    public String construirGrillaListaProduccion(List<ListaProduccion> lista) {
        StringBuilder str = new StringBuilder();
        str.append("<div class=\"table-responsive\">");
        str.append(INI_TABLA);
        StringBuilder cabecera = new StringBuilder();
        cabecera.append(INI_THEAD);
        cabecera.append(INI_TR);
        cabecera.append(INI_TH);
        cabecera.append("N°");
        cabecera.append(FINI_TH);
        cabecera.append(INI_TH);
        cabecera.append("DESCRIPCION ");
        cabecera.append(FINI_TH);
        cabecera.append(INI_TH);
        cabecera.append("MARCA");
        cabecera.append(FINI_TH);
        cabecera.append(INI_TH);
        cabecera.append("PRESENTACION");
        cabecera.append(FINI_TH);
        cabecera.append(INI_TH);
        cabecera.append("MEDIDA");
        cabecera.append(FINI_TH);
        cabecera.append(INI_TH);
        cabecera.append("CANTIDAD");
        cabecera.append(FINI_TH);
        cabecera.append(INI_TH);
        cabecera.append("SELECCIONAR");
        cabecera.append(FINI_TH);
        cabecera.append(FINI_TR);
        cabecera.append(FIN_THEAD);

        str.append(cabecera.toString());
        str.append(INI_TBODY);
        if (lista != null && lista.size() > 0) {
            int i = 1;
            for (ListaProduccion fila : lista) {
                str.append(INI_TR);
                str.append(INI_TD);
                str.append(i);
                str.append(FIN_TD);
                str.append(INI_TD);
                str.append(fila.getDescripcion());
                str.append(FIN_TD);
                str.append(INI_TD);
                str.append(fila.getMarca());
                str.append(FIN_TD);
                str.append(INI_TD);
                str.append(fila.getPresentacion());
                str.append(FIN_TD);
                str.append(INI_TD);
                str.append(fila.getMedida());
                str.append(FIN_TD);
                str.append(INI_TD);
                str.append(fila.getCantidad_produccion());
                str.append(FIN_TD);
                str.append(INI_TD);
                str.append("<input type=\"button\" value=\"Eliminar\" onclick=\"fneliminarItem('" + i + "');\"");
                str.append(FIN_TD);
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

    private static class LogicTablaReglaProduccionHolder {

        private static final LogicTablaReglaProduccion INSTANCE = new LogicTablaReglaProduccion();
    }

    public String construirGrillaListaReglasProduccion(List<ListaReglaProduccion> lista) { 
        StringBuilder str = new StringBuilder();
        str.append("<div class=\"table-responsive\">");
        str.append(INI_TABLA);
        StringBuilder cabecera = new StringBuilder();
        cabecera.append(INI_THEAD);
        cabecera.append(INI_TR);
        cabecera.append(INI_TH);
        cabecera.append("N°");
        cabecera.append(FINI_TH);
        cabecera.append(INI_TH);
        cabecera.append("DESCRIPCION ");
        cabecera.append(FINI_TH);
        cabecera.append(INI_TH);
        cabecera.append("MARCA");
        cabecera.append(FINI_TH);
        cabecera.append(INI_TH);
        cabecera.append("PRESENTACION");
        cabecera.append(FINI_TH);
        cabecera.append(INI_TH);
        cabecera.append("MEDIDA");
        cabecera.append(FINI_TH);
        cabecera.append(INI_TH);
        cabecera.append("CANTIDAD");
        cabecera.append(FINI_TH);
        cabecera.append(INI_TH);
        cabecera.append("SELECCIONAR");
        cabecera.append(FINI_TH);
        cabecera.append(FINI_TR);
        cabecera.append(FIN_THEAD);

        str.append(cabecera.toString());
        str.append(INI_TBODY);
        if (lista != null && lista.size() > 0) {
            int i = 1;
            for (ListaReglaProduccion fila : lista) {
                str.append(INI_TR);
                str.append(INI_TD);
                str.append(i);
                str.append(FIN_TD);
                str.append(INI_TD);
                str.append(fila.getDescripcion());
                str.append(FIN_TD);
                str.append(INI_TD);
                str.append(fila.getMarca());
                str.append(FIN_TD);
                str.append(INI_TD);
                str.append(fila.getPresentacion());
                str.append(FIN_TD);
                str.append(INI_TD);
                str.append(fila.getMedida());
                str.append(FIN_TD);
                str.append(INI_TD);
                str.append(fila.getCantidad());
                str.append(FIN_TD);
                str.append(INI_TD);
                str.append("<input type=\"button\" value=\"Eliminar\" onclick=\"fneliminarItem('" + i + "');\"");
                str.append(FIN_TD);
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

    public String construirGrillaMostrarInsumos(List<ListaReglaProduccion> lista) {
        String INI_TABLA1 = "<table class=\"table table-striped table-bordered table-hover\" id=\"dynamic-table3\">";
        StringBuilder str = new StringBuilder();
        int regla = 0;
        str.append("<div class=\"table-responsive\">");
        str.append(INI_TABLA1);
        StringBuilder cabecera = new StringBuilder();
        cabecera.append(INI_THEAD);
        cabecera.append(INI_TR);
        cabecera.append(INI_TH);
        cabecera.append("N°");
        cabecera.append(FINI_TH);
        cabecera.append(INI_TH);
        cabecera.append("DESCRIPCION ");
        cabecera.append(FINI_TH);
        cabecera.append(INI_TH);
        cabecera.append("MARCA");
        cabecera.append(FINI_TH);
        cabecera.append(INI_TH);
        cabecera.append("PRESENTACION");
        cabecera.append(FINI_TH);
        cabecera.append(INI_TH);
        cabecera.append("MEDIDA");
        cabecera.append(FINI_TH);
        cabecera.append(INI_TH);
        cabecera.append("CANTIDAD");
        cabecera.append(FINI_TH);
        cabecera.append(FINI_TR);
        cabecera.append(FIN_THEAD);

        str.append(cabecera.toString());
        str.append(INI_TBODY);
        if (lista != null && lista.size() > 0) {
            int i = 1;
            for (ListaReglaProduccion fila : lista) {
                regla = fila.getId_regla();
                str.append(INI_TR);
                str.append(INI_TD);
                str.append(i);
                str.append(FIN_TD);
                str.append(INI_TD);
                str.append(fila.getDescripcion());
                str.append(FIN_TD);
                str.append(INI_TD);
                str.append(fila.getMarca());
                str.append(FIN_TD);
                str.append(INI_TD);
                str.append(fila.getPresentacion());
                str.append(FIN_TD);
                str.append(INI_TD);
                str.append(fila.getMedida());
                str.append(FIN_TD);
                str.append(INI_TD);
                str.append(fila.getCantidad());
                str.append(FIN_TD);
//				str.append(INI_TD);	str.append( "<input type=\"button\" value=\"Seleccionar\" onclick=\"fnSeleccionarCliente('"+fila.getRazonSocial()+"','"+fila.getApellidoPaterno()+"','"+fila.getApellidoMaterno()+"','"+fila.getNombres()+"','"+fila.getDniRuc()+"','"+fila.getDireccion()+"','"+fila.getNaturalezaCliente()+"','"+fila.getIdTipoCliente()+"','"+fila.getIdCliente()+"')\" />");		str.append(FIN_TD);
                str.append(FINI_TR);
                i++;
            }

        }
        str.append(FIN_TBODY);
        str.append(FIN_TABLA);
        str.append("</div>");
        return str.toString().concat("%" + String.valueOf(regla));
    }

}
