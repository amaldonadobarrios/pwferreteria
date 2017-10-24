package model.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.GananciaDao;
import model.dto.Ganancia;
import org.apache.log4j.Logger;
import util.Util;
import util.jdbc.ConectaDB;

public class GananciaDaoImpl implements GananciaDao{
    final Logger logger = Logger.getLogger(ClienteDaoImpl.class);
    Util uti = new Util();
    Connection cn = null;
    ConectaDB db = new ConectaDB();
    @Override
    public List<Ganancia> listar_ganancia_mensual(String ini, String fin) throws Exception {
        String sqlResult = "";
        List<Ganancia> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/callGananciaMensual.sql");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new Exception("Problemas de Conexion...");
        } catch (Throwable ex) {
            logger.error(ex);
            throw new Exception("Problemas del sistema...");
        }

        if (cn != null) {

            try {
                 CallableStatement ps = cn.prepareCall(sqlResult);
                ps.setString(1, ini);
                ps.setString(2, fin);
                 ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    listTemp = new ArrayList<>();
                    Ganancia temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {

                        temp = new Ganancia();
                        temp.setFecha(rs.getString(1));
                        temp.setGanancia(rs.getDouble(2));
                        listTemp.add(temp);
                    }
                }

            } catch (SQLException e) {
                logger.error(e);
                throw new Exception("Problemas del sistema...");
            } finally {
                try {
                    cn.close();
                } catch (SQLException ex) {
                    logger.error(ex);
                }
            }
        }

        return listTemp;  
    }

    @Override
    public List<Ganancia> listar_ganancia_anual(String ini, String fin) throws Exception {
     String sqlResult = "";
        List<Ganancia> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/callGananciaAnual.sql");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new Exception("Problemas de Conexion...");
        } catch (Throwable ex) {
            logger.error(ex);
            throw new Exception("Problemas del sistema...");
        }

        if (cn != null) {

            try {
                 CallableStatement ps = cn.prepareCall(sqlResult);
                ps.setString(1, ini);
                ps.setString(2, fin);
                 ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    listTemp = new ArrayList<>();
                    Ganancia temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {

                        temp = new Ganancia();
                        temp.setFecha(rs.getString(1));
                        temp.setGanancia(rs.getDouble(2));
                        listTemp.add(temp);
                    }
                }

            } catch (SQLException e) {
                logger.error(e);
                throw new Exception("Problemas del sistema...");
            } finally {
                try {
                    cn.close();
                } catch (SQLException ex) {
                    logger.error(ex);
                }
            }
        }

        return listTemp;   
        
        
        
        
    }
 
    
    
}
