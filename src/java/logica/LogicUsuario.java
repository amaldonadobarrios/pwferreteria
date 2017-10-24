/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.List;
import model.dao.UsuarioDao;
import model.dao.impl.UsuarioDaoImpl;
import model.dto.Usuario;

/**
 *
 * @author Alexander
 */
public class LogicUsuario {
    
    public List<Usuario> listarUsuarios() throws Exception{
      
        List<Usuario> lista=null;
        lista=new ArrayList<Usuario>();
        try {
          UsuarioDao dao = new UsuarioDaoImpl();   
           lista= dao.getUsuarios(); 
        } catch (Exception e) {
            System.out.println("logica.LogicUsuario.listarUsuarios()"+e);
        }
        return lista;   
    }
    public String registrar(Usuario usu) throws Exception{
      String mensaje="";
        try {
          UsuarioDao dao = new UsuarioDaoImpl();   
           mensaje= dao.insertarUsuario(usu); 
        } catch (Exception e) {
            System.out.println("logica.LogicUsuario.registrar()"+e);
        }
        return mensaje;   
    }
    public String actualizar(Usuario usu)throws Exception{
     String mensaje="";
        try {
          UsuarioDao dao = new UsuarioDaoImpl();   
           mensaje= dao.actualizarUsuario(usu); 
        } catch (Exception e) {
            System.out.println("logica.LogicUsuario.actualizar()"+e);
        }
        return mensaje;      
    }
     public Usuario buscarUsuarioID(int id)throws Exception{
     Usuario usu=new Usuario();
        try {
          UsuarioDao dao = new UsuarioDaoImpl();   
           usu= dao.getUsuarioXid(id); 
        } catch (Exception e) {
            System.out.println("logica.LogicUsuario.buscarUsuarioID()"+e);
        }
        return usu;      
    }
     public Usuario validarUsuario(String usuario, String clave) throws Exception{
        Usuario usu=null;
        try {
          UsuarioDao dao = new UsuarioDaoImpl();   
           usu= dao.validarAcceso(usuario.trim(), clave.trim());
        } catch (Exception e) {
            System.out.println("logica.LogicUsuario.validarUsuario()"+e);
        }
        return usu;       
     }
    
    
}
