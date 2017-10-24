/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.List;
import model.dao.ProveedorDao;
import model.dao.impl.ProveedorDaoImpl;
import model.dto.Proveedor;

/**
 *
 * @author Alexander
 */
public class LogicProveedor {
    
    private LogicProveedor() {
    }
    
    public static LogicProveedor getInstance() {
        return LogicProveedorHolder.INSTANCE;
    }
    
    private static class LogicProveedorHolder {

        private static final LogicProveedor INSTANCE = new LogicProveedor();
    }
    public String registrarProveedor(Proveedor prov) throws  Exception{
      String mensaje="";
        try {
          ProveedorDao dao = new ProveedorDaoImpl();   
           mensaje= dao.insertarProveedor(prov); 
        } catch (Exception e) {
            System.out.println("logica.LogicProveedor.registrarProveedor()"+e);
        }
        return mensaje;  
    }
       public String actualizar(Proveedor prov)throws Exception{
     String mensaje="";
        try {
          ProveedorDao dao = new ProveedorDaoImpl();   
           mensaje= dao.actualizarProveedor(prov); 
        } catch (Exception e) {
            System.out.println("logica.LogicProveedor.actualizar()"+e);
        }
        return mensaje;      
    }
     public Proveedor buscarProveedorID(int id)throws Exception{
     Proveedor prov=new Proveedor();
        try {
          ProveedorDao dao = new ProveedorDaoImpl();   
           prov= dao.getProveedorXid(id); 
        } catch (Exception e) {
            System.out.println("logica.LogicProveedor.buscarProveedorID()"+e);
        }
        return prov;      
    }
     public List<Proveedor> listarProveedor() throws Exception{
      
        List<Proveedor> lista=null;
        lista=new ArrayList<Proveedor>();
        try {
          ProveedorDao dao = new ProveedorDaoImpl();   
           lista= dao.getProveedor(); 
        } catch (Exception e) {
            System.out.println("logica.LogicProveedor.listarProveedor()"+e);
        }
        return lista;   
    }
    
    
    
    
    
}
