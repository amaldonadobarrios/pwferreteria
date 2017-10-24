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
 * @author 31424836
 */
public class LogicInventario {
    
    private LogicInventario() {
    }
    
    public static LogicInventario getInstance() {
        return LogicInventarioHolder.INSTANCE;
    }
    
    private static class LogicInventarioHolder {

        private static final LogicInventario INSTANCE = new LogicInventario();
    }
    
    public List<Producto> ListarInventario() throws Exception {
        List<Producto> prod = null;
        ProductoDao dao = new ProductoDaoImpl();
        prod = dao.getProductosSinfoto();
        return prod;
    }
    public String getfotoProductoId(String id) throws Exception {
        String foto= null;
        ProductoDao dao = new ProductoDaoImpl();
        foto = dao.getfotoId(id);
        return foto;
    } 
}
