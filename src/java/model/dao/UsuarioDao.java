/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.dto.Usuario;

/**
 *
 * @author FARID
 */
public interface UsuarioDao {

    public String insertarUsuario(Usuario usuario)throws Exception;
    
    public String actualizarUsuario(Usuario usuario)throws  Exception;

    public List<Usuario> getUsuarios() throws Exception;

    public Usuario getUsuarioXid(int id) throws Exception;

    public Usuario validarAcceso(String usuario, String clave) throws Exception;

}
