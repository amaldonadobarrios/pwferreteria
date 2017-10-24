/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import com.google.gson.Gson;
import java.util.List;
import model.dao.ComprobanteCompraDao;
import model.dao.ProductoDao;
import model.dao.ProveedorDao;
import model.dao.impl.ComprobanteCompraDaoImpl;
import model.dao.impl.ProductoDaoImpl;
import model.dao.impl.ProveedorDaoImpl;
import model.dto.ComprobanteCompra;
import model.dto.Producto;
import model.dto.Proveedor;

/**
 *
 * @author 31424836
 */
public class LogicCompra {
    
    private LogicCompra() {
    }
    
    public static LogicCompra getInstance() {
        return LogicCompraHolder.INSTANCE;
    }
    
    private static class LogicCompraHolder {

        private static final LogicCompra INSTANCE = new LogicCompra();
    }
     public boolean verificarNumComprobante(String numeradorcomprobante) throws Exception {
        boolean estado = false;
        ComprobanteCompraDao dao=new ComprobanteCompraDaoImpl();
        estado=dao.verificarNumComprobante(numeradorcomprobante);
        return estado;
    }

    public String eliminarCompra(String numero, String id)throws Exception {
       String respuesta=null;
        ComprobanteCompraDao dao=new ComprobanteCompraDaoImpl();
        respuesta=dao.EliminarCompra(numero,id);
        return respuesta;
    }

 
    public List<Proveedor> buscarproveedorxDOC(String arg) throws Exception {
        List<Proveedor> cli = null;
        ProveedorDao dao = new ProveedorDaoImpl();
        cli = dao.getProveedorXdoc(arg);
        return cli;
    }

    public List<Proveedor> buscarproveedorxAPERAZ(String arg) throws Exception {
        List<Proveedor> cli = null;
        ProveedorDao dao = new ProveedorDaoImpl();
        cli = dao.getProveedorXaperaz(arg.toUpperCase());
        return cli;
    }

    public String buscarProveedorRegistrado(Proveedor cli) throws Exception {
        String json = null;
        Proveedor cl = null;
        ProveedorDao dao = new ProveedorDaoImpl();
        cl = dao.getProveedorRegistrado(cli);
        if (cl != null) {
            Gson gson = new Gson();
            json = gson.toJson(cl);
        }
        return json;
    }

    public List<Producto> listarproductoscombocompra() throws Exception {
        List<Producto> list = null;
        ProductoDao dao = new ProductoDaoImpl();
        list = dao.listarproductoscombocompra();
        return list;
    }

    public String grabarCompra(ComprobanteCompra compra) throws Exception {
        String rpta = null;
        ComprobanteCompraDao dao = new ComprobanteCompraDaoImpl();
        rpta = dao.GrabarCompra(compra);
        return rpta;
    }
    public List<ComprobanteCompra> listarcompra200() throws Exception {
        List<ComprobanteCompra> list = null;
        ComprobanteCompraDao dao = new ComprobanteCompraDaoImpl();
        list = dao.ListarCompra200();
        return list;
    }
     public List<ComprobanteCompra> listarcompraxFecha(String fecha) throws Exception {
        List<ComprobanteCompra> list = null;
        ComprobanteCompraDao dao = new ComprobanteCompraDaoImpl();
        list = dao.ListarCompraxFecha(fecha);
        return list;
    }
       public List<ComprobanteCompra> listarcompraxRango(String ini,String fin) throws Exception {
        List<ComprobanteCompra> list = null;
        ComprobanteCompraDao dao = new ComprobanteCompraDaoImpl();
        list = dao.ListarCompraxRango(ini,fin);
        return list;
    }
       public List<ComprobanteCompra>  PNGReporteCompra(String ini,String fin) throws Exception {
        List<ComprobanteCompra> list = null;
        ComprobanteCompraDao dao = new ComprobanteCompraDaoImpl();
         list = dao.ListarCompraxmesxaño(ini,fin);
        return list;
    }
}
