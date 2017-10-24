/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.List;
import model.dao.PerfilDao;
import model.dao.impl.PerfilDaoImpl;
import model.dto.Perfil;

/**
 *
 * @author Alexander
 */
public class LogicPerfil {
        public List<Perfil> listarPerfiles() throws Exception{
      
        List<Perfil> lista=null;
        lista=new ArrayList<Perfil>();
        try {
          PerfilDao dao = new PerfilDaoImpl();   
           lista= dao.getPerfils();
        } catch (Exception e) {
            System.out.println("logica.LogicUsuario.listarPerfiles()"+e);
        }
        return lista;   
    }
    
    
}
