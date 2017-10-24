/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import model.dao.GananciaDao;
import model.dao.impl.GananciaDaoImpl;
import model.dto.Ganancia;

/**
 *
 * @author 31424836
 */
public class LogicGanancia {
    
    private LogicGanancia() {
    }
    
    public static LogicGanancia getInstance() {
        return LogicGananciaHolder.INSTANCE;
    }
    
    private static class LogicGananciaHolder {

        private static final LogicGanancia INSTANCE = new LogicGanancia();
    }

    public List<Ganancia>  PNGReporteGananciaMensual(String ini,String fin) throws Exception {
        List<Ganancia> list = null;
        GananciaDao dao = new GananciaDaoImpl();
         list = dao.listar_ganancia_mensual(ini, fin);
        return list;
    }
     public List<Ganancia>  PNGReporteGananciaAnual(String ini,String fin) throws Exception {
        List<Ganancia> list = null;
        GananciaDao dao = new GananciaDaoImpl();
         list = dao.listar_ganancia_anual(ini, fin);
        return list;
    }
    
    
}
