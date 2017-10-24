/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.dto.Cliente;

/**
 *
 * @author Alexander
 */
public interface ClienteDao {
    public String insertarCliente(Cliente cli)throws Exception;
    
    public String actualizarCliente(Cliente cli)throws  Exception;

    public List<Cliente> getCliente() throws Exception;

    public Cliente getClienteXid(int id) throws Exception;
    public List<Cliente> getClienteXdoc(String arg) throws Exception;
    public  List<Cliente> getClienteXaperaz(String arg) throws Exception;
    public Cliente getClienteRegistrado(Cliente cli) throws Exception;
}
