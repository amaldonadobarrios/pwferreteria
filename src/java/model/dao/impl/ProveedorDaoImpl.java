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
import model.dao.ProveedorDao;
import model.dto.Proveedor;
import org.apache.log4j.Logger;
import util.Util;
import util.jdbc.ConectaDB;

/**
 *
 * @author Alexander
 */
public class ProveedorDaoImpl implements ProveedorDao{
    final static Logger logger = Logger.getLogger(ProveedorDaoImpl.class);
    Util uti = new Util();
    Connection cn = null;
    ConectaDB db = new ConectaDB();
    
      public ProveedorDaoImpl() {
    }
    @Override
    public String insertarProveedor(Proveedor prov) throws Exception {
       String mensaje = null;
        String sqlResult = "";

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/insertProveedor.sql");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new Exception("Problemas del sistema...");
        } catch (Throwable ex) {
            logger.error(ex);
        }

        if (cn != null) {

            try {

                PreparedStatement ps = cn.prepareStatement(sqlResult);
                ps.setString(1, prov.getNaturalezaProveedor());
                ps.setString(2, prov.getDniRuc());
                ps.setString(3, prov.getRazonSocial());
                ps.setString(4, prov.getNombres());
                ps.setString(5, prov.getApellidoPaterno());
                ps.setString(6, prov.getApellidoMaterno());
                ps.setString(7, prov.getTelefono());
                ps.setString(8, prov.getDireccion());
                ps.setString(9, prov.getEmail());
                ps.setInt(10, prov.getUsuarioReg());

                int i = ps.executeUpdate();
                if (i > 0) {
                    cn.commit();
                    logger.info("OK");
                    mensaje = "OK";
                } else {
                    cn.rollback();
                    mensaje = "NO OK";
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

        return mensaje;
    }

    @Override
    public String actualizarProveedor(Proveedor prov) throws Exception {
        String mensaje = null;
        String sqlResult = "";

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/updateProveedor.sql");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new Exception("Problemas del sistema...");
        } catch (Throwable ex) {
            logger.error(ex);
        }

        if (cn != null) {

            try {
                
                PreparedStatement ps = cn.prepareStatement(sqlResult);
                
                ps.setString(1, prov.getNaturalezaProveedor());
                ps.setString(2, prov.getDniRuc());
                ps.setString(3, prov.getRazonSocial());
                ps.setString(4, prov.getNombres());
                ps.setString(5, prov.getApellidoPaterno());
                ps.setString(6, prov.getApellidoMaterno());
                ps.setString(7, prov.getTelefono());
                ps.setString(8, prov.getDireccion());
                ps.setString(9, prov.getEmail());
                ps.setInt(10, prov.getUsuarioMod());
                 ps.setInt(11, prov.getIdProveedor());

                int i = ps.executeUpdate();
                if (i > 0) {
                    cn.commit();
                    logger.info("OK");
                    mensaje = "OK";
                } else {
                    cn.rollback();
                    mensaje = "NO OK";
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

        return mensaje;  
    }

    @Override
    public List<Proveedor> getProveedor() throws Exception {
         String sqlResult = "";
        List<Proveedor> listTemp = null;

        try {
             cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectProveedor.sql");
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
                    Proveedor temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {

                        temp = new Proveedor();
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
    public Proveedor getProveedorXid(int id) throws Exception {
         String sqlResult = "";
        Proveedor prov=null;
         
       try {
             cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectProveedorID.sql");
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
                    prov = new Proveedor();
                    prov = prov.loadRs(rs);
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

        return prov;
    }

    @Override
    public List<Proveedor> getProveedorXdoc(String arg) throws Exception{
        String sqlResult = "";
        List<Proveedor> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectProveedorXdoc.sql");
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
                ps.setString(1, arg);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    listTemp = new ArrayList<>();
                    Proveedor temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {

                        temp = new Proveedor();
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
    public List<Proveedor> getProveedorXaperaz(String arg) throws Exception {
          String sqlResult = "";
        List<Proveedor> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectProveedorXaperaz.sql");
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
                for (int i = 1; i < 5; i++) {
                    ps.setString(i, arg);
                }

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    listTemp = new ArrayList<>();
                    Proveedor temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {

                        temp = new Proveedor();
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
    public Proveedor getProveedorRegistrado(Proveedor proveedor)  throws  Exception{
       String sqlResult = "";
        Proveedor pro = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectProveedorRegistrado.sql");
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
                ps.setString(1, proveedor.getNaturalezaProveedor());
                ps.setString(2, proveedor.getDniRuc());
                ps.setString(3, proveedor.getRazonSocial());
                ps.setString(4, proveedor.getNombres());
                ps.setString(5, proveedor.getApellidoPaterno());
                ps.setString(6, proveedor.getApellidoMaterno());
                ps.setString(7, proveedor.getTelefono());
                ps.setString(8, proveedor.getDireccion());
                ps.setString(9, proveedor.getEmail());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    pro = new Proveedor();
                    pro = pro.loadRs(rs);
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

        return pro;
    }
    
    
}
