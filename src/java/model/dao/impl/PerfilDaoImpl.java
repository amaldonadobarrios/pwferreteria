/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.PerfilDao;
import model.dto.Perfil;
import org.apache.log4j.Logger;
import util.Util;
import util.jdbc.ConectaDB;


/**
 *
 * @author FARID
 */
public class PerfilDaoImpl implements PerfilDao {

    final static Logger logger = Logger.getLogger(PerfilDaoImpl.class);
    Util uti = new Util();
    ConectaDB db = new ConectaDB();
    Connection cn = null;

    @Override
    public List<Perfil> getPerfils() throws Exception {

        String sqlResult = "";
        List<Perfil> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectPerfil.sql");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new Exception("Problemas de Conexion...");
        } catch (Throwable ex) {
            logger.error(ex);
            throw new Exception("Problemas del sistema...");
        }

        if (cn != null) {

            try {
                PreparedStatement ps = cn.prepareStatement(sqlResult);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    listTemp = new ArrayList<>();
                    Perfil temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {

                        temp = new Perfil();
                        listTemp.add(temp.loadRs(rs));

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
    public Perfil getPerfilId(int id) throws Exception {
        String sqlResult = "";
        Perfil temp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectPerfilId.sql");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new Exception("Problemas de Conexion...");
        } catch (Throwable ex) {
            logger.error(ex);
            throw new Exception("Problemas del sistema...");
        }

        if (cn != null) {

            try {
                PreparedStatement ps = cn.prepareStatement(sqlResult);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    temp = new Perfil();
                    temp = temp.loadRs(rs);
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

        return temp;
    }

}
