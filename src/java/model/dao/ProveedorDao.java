/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.dto.Proveedor;

/**
 *
 * @author Alexander
 */
public interface ProveedorDao {
        public String insertarProveedor(Proveedor prov)throws Exception;
    
    public String actualizarProveedor(Proveedor prov)throws  Exception;

    public List<Proveedor> getProveedor() throws Exception;

    public Proveedor getProveedorXid(int id) throws Exception;

    public List<Proveedor> getProveedorXdoc(String arg)throws  Exception;

    public List<Proveedor> getProveedorXaperaz(String arg)throws  Exception;

    public Proveedor getProveedorRegistrado(Proveedor cli) throws  Exception;
}
