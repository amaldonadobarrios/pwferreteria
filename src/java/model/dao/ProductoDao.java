/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.dto.Producto;

/**
 *
 * @author Alexander
 */
public interface ProductoDao {

    public String insertarProducto(Producto pro) throws Exception;

    public List<Producto> getProductos() throws Exception;

    public Producto getProductoXid(int id) throws Exception;

    public String actualizarProducto(Producto pro) throws Exception;

    public String registrarPrecioVenta(Producto pro) throws Exception;
    public List<Producto> listarproductoscomboventa() throws  Exception;
    public List<Producto> getProductosSinfoto() throws Exception;
    public String getfotoId(String id) throws  Exception;

    public List<Producto> listarproductoscombocompra() throws Exception;
    public List<Producto> listainsumos() throws Exception;
    public List<Producto> listaProductosFinales(String parametro) throws Exception;
}
