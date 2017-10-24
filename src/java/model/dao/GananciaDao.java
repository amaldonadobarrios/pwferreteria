/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.dto.Ganancia;

/**
 *
 * @author 31424836
 */
public interface GananciaDao {
    
    public List<Ganancia> listar_ganancia_mensual(String ini, String fin) throws Exception;
    public List<Ganancia> listar_ganancia_anual(String ini, String fin) throws Exception;
}
