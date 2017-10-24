/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import model.dao.ProductoDao;
import model.dao.impl.ProductoDaoImpl;
import model.dto.Producto;

/**
 *
 * @author Alexander
 */
public class LogicProducto {
    
    private LogicProducto() {
    }
    
    public static LogicProducto getInstance() {
        return LogicProductoHolder.INSTANCE;
    }

    public String actualizarProducto(Producto pro) throws Exception{
        String mensaje="";
        try {
          ProductoDao dao = new ProductoDaoImpl();   
           mensaje= dao.actualizarProducto(pro); 
        } catch (Exception e) {
            System.out.println("logica.LogicProducto.actualizarProducto()"+e);
        }
        return mensaje; 
    }

    public String registrarPrecioVenta(Producto pro)throws Exception{
       String mensaje="";
        try {
          ProductoDao dao = new ProductoDaoImpl();   
           mensaje= dao.registrarPrecioVenta(pro); 
        } catch (Exception e) {
            System.out.println("logica.LogicProducto.registrarPrecioVenta()"+e);
        }
        return mensaje;   
    }
    
    private static class LogicProductoHolder {

        private static final LogicProducto INSTANCE = new LogicProducto();
    }
    
     public String registrarProducto(Producto pro) throws  Exception{
         String mensaje="";
        try {
          ProductoDao dao = new ProductoDaoImpl();   
           mensaje= dao.insertarProducto(pro); 
        } catch (Exception e) {
            System.out.println("logica.LogicProducto.registrarProducto()"+e);
        }
        return mensaje;  
     }
     public List<Producto> getProductos() throws  Exception{
        List<Producto> lista=null;
        ProductoDao dao = new ProductoDaoImpl();   
           lista= dao.getProductos();
           return lista;
     }
       public Producto buscarProductoID(int id)throws Exception{
     Producto cli=new Producto();
        try {
          ProductoDao dao = new ProductoDaoImpl();   
           cli= dao.getProductoXid(id); 
        } catch (Exception e) {
            System.out.println("logica.LogicProducto.buscarProductoID()"+e);
        }
        return cli;      
    }
}
