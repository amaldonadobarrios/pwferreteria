/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.dto.ComprobanteCompra;

/**
 *
 * @author 31424836
 */
public interface ComprobanteCompraDao {
    
    public String GrabarCompra(ComprobanteCompra compra) throws Exception;

    public boolean verificarNumComprobante(String numeradorcomprobante) throws Exception;

    public List<ComprobanteCompra> ListarCompra200() throws Exception;

    public String EliminarCompra(String numero, String id) throws Exception;

    public List<ComprobanteCompra> ListarCompraxFecha(String fecha) throws  Exception;

    public List<ComprobanteCompra> ListarCompraxRango(String ini, String fin) throws  Exception;

    public List<ComprobanteCompra> ListarCompraxmesxaño(String ini, String fin) throws  Exception;
}
