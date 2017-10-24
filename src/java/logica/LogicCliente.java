/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.List;
import model.dao.ClienteDao;
import model.dao.impl.ClienteDaoImpl;
import model.dto.Cliente;

/**
 *
 * @author Alexander
 */
public class LogicCliente {
    
    public LogicCliente() {
    }
    
    public static LogicCliente getInstance() {
        return LogicClienteHolder.INSTANCE;
    }
    
    private static class LogicClienteHolder {

        private static final LogicCliente INSTANCE = new LogicCliente();
    }
    
    public String registrarCliente(Cliente cli) throws  Exception{
      String mensaje="";
        try {
          ClienteDao dao = new ClienteDaoImpl();   
           mensaje= dao.insertarCliente(cli); 
        } catch (Exception e) {
            System.out.println("logica.LogicUsuario.registrar()"+e);
        }
        return mensaje;  
    }
       public String actualizar(Cliente cli)throws Exception{
     String mensaje="";
        try {
          ClienteDao dao = new ClienteDaoImpl();   
           mensaje= dao.actualizarCliente(cli); 
        } catch (Exception e) {
            System.out.println("logica.LogicUsuario.actualizar()"+e);
        }
        return mensaje;      
    }
     public Cliente buscarClienteID(int id)throws Exception{
     Cliente cli=new Cliente();
        try {
          ClienteDao dao = new ClienteDaoImpl();   
           cli= dao.getClienteXid(id); 
        } catch (Exception e) {
            System.out.println("logica.LogicUsuario.buscarUsuarioID()"+e);
        }
        return cli;      
    }
     public List<Cliente> listarCliente() throws Exception{
      
        List<Cliente> lista=null;
        lista=new ArrayList<Cliente>();
        try {
          ClienteDao dao = new ClienteDaoImpl();   
           lista= dao.getCliente(); 
        } catch (Exception e) {
            System.out.println("logica.LogicCliente.listarUsuarios()"+e);
        }
        return lista;   
    }
    
    
    
    
    
}
