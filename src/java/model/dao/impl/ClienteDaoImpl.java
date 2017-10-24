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
import model.dao.ClienteDao;
import model.dto.Cliente;
import org.apache.log4j.Logger;
import util.Util;
import util.jdbc.ConectaDB;

/**
 *
 * @author Alexander
 */
public class ClienteDaoImpl implements ClienteDao {

    final static Logger logger = Logger.getLogger(ClienteDaoImpl.class);
    Util uti = new Util();
    Connection cn = null;
    ConectaDB db = new ConectaDB();

    public ClienteDaoImpl() {
    }

    @Override
    public String insertarCliente(Cliente cli) throws Exception {
        String mensaje = null;
        String sqlResult = "";

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/insertCliente.sql");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new Exception("Problemas del sistema...");
        } catch (Throwable ex) {
            logger.error(ex);
        }

        if (cn != null) {

            try {

                PreparedStatement ps = cn.prepareStatement(sqlResult);
                ps.setString(1, cli.getNaturalezaCliente());
                ps.setInt(2, cli.getIdTipoCliente());
                ps.setString(3, cli.getDniRuc());
                ps.setString(4, cli.getRazonSocial());
                ps.setString(5, cli.getNombres());
                ps.setString(6, cli.getApellidoPaterno());
                ps.setString(7, cli.getApellidoMaterno());
                ps.setString(8, cli.getTelefono());
                ps.setString(9, cli.getDireccion());
                ps.setString(10, cli.getEmail());
                ps.setInt(11, cli.getUsuarioReg());

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
    public String actualizarCliente(Cliente cli) throws Exception {
        String mensaje = null;
        String sqlResult = "";

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/updateCliente.sql");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new Exception("Problemas del sistema...");
        } catch (Throwable ex) {
            logger.error(ex);
        }

        if (cn != null) {

            try {

                PreparedStatement ps = cn.prepareStatement(sqlResult);

                ps.setString(1, cli.getNaturalezaCliente());
                ps.setInt(2, cli.getIdTipoCliente());
                ps.setString(3, cli.getDniRuc());
                ps.setString(4, cli.getRazonSocial());
                ps.setString(5, cli.getNombres());
                ps.setString(6, cli.getApellidoPaterno());
                ps.setString(7, cli.getApellidoMaterno());
                ps.setString(8, cli.getTelefono());
                ps.setString(9, cli.getDireccion());
                ps.setString(10, cli.getEmail());
                ps.setInt(11, cli.getUsuarioMod());
                ps.setInt(12, cli.getIdCliente());

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
    public List<Cliente> getCliente() throws Exception {
        String sqlResult = "";
        List<Cliente> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectCliente.sql");
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

                    listTemp = new ArrayList<Cliente>();
                    Cliente temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {

                        temp = new Cliente();
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
    public Cliente getClienteXid(int id) throws Exception {
        String sqlResult = "";
        Cliente cli = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectClienteID.sql");
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
                    cli = new Cliente();
                    cli = cli.loadRs(rs);
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

        return cli;
    }

    @Override
    public List<Cliente> getClienteXdoc(String arg) throws Exception {
        String sqlResult = "";
        List<Cliente> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectClienteXdoc.sql");
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

                    listTemp = new ArrayList<Cliente>();
                    Cliente temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {

                        temp = new Cliente();
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
    public List<Cliente> getClienteXaperaz(String arg) throws Exception {
        String sqlResult = "";
        List<Cliente> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectClienteXaperaz.sql");
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

                    listTemp = new ArrayList<Cliente>();
                    Cliente temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {

                        temp = new Cliente();
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
    public Cliente getClienteRegistrado(Cliente clie) throws Exception {
        String sqlResult = "";
        Cliente cli = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectClienteRegistrado.sql");
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
                ps.setString(1, clie.getNaturalezaCliente());
                ps.setInt(2, clie.getIdTipoCliente());
                ps.setString(3, clie.getDniRuc());
                ps.setString(4, clie.getRazonSocial());
                ps.setString(5, clie.getNombres());
                ps.setString(6, clie.getApellidoPaterno());
                ps.setString(7, clie.getApellidoMaterno());
                ps.setString(8, clie.getTelefono());
                ps.setString(9, clie.getDireccion());
                ps.setString(10, clie.getEmail());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    cli = new Cliente();
                    cli = cli.loadRs(rs);
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

        return cli;

    }

}
