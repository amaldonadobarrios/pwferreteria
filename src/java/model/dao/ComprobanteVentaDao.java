/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;


import java.util.List;
import model.dto.ComprobanteVenta;

/**
 *
 * @author Alexander
 */
public interface ComprobanteVentaDao {

    public String GrabarVenta(ComprobanteVenta venta) throws Exception;

    public boolean verificarNumComprobante(String numeradorcomprobante) throws Exception;

    public List<ComprobanteVenta> ListarVenta200() throws Exception;

    public String EliminarVenta(String numero, String id) throws Exception;

    public List<ComprobanteVenta> ListarVentaxFecha(String fecha) throws  Exception;

    public List<ComprobanteVenta> ListarVentaxRango(String ini, String fin) throws  Exception;

    public List<ComprobanteVenta> ListarVentasxmesxaño(String ini, String fin) throws  Exception;

}
