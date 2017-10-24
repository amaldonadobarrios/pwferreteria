/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import java.util.List;
import model.dao.ComprobanteVentaDao;
import model.dto.ComprobanteVenta;
import org.apache.log4j.Logger;
import util.DirDate;
import util.Util;
import util.jdbc.ConectaDB;

/**
 *
 * @author Alexander
 */
public class ComprobanteVentaDaoImpl implements ComprobanteVentaDao {

    final Logger logger = Logger.getLogger(ClienteDaoImpl.class);
    Util uti = new Util();
    Connection cn = null;
    ConectaDB db = new ConectaDB();

    @Override
    public String GrabarVenta(ComprobanteVenta venta) throws Exception {
        String mensaje = null;
        String sqlResult = "";

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/insertVenta.sql");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new Exception("Problemas del sistema...");
        } catch (Throwable ex) {
            logger.error(ex);
        }

        if (cn != null) {

            try {

                CallableStatement ps = cn.prepareCall(sqlResult);
                String num = venta.getTipo() + "-" + venta.getNumero_comprobante() + "-" + DirDate.getInstance().getFechaYYYY();
                System.out.println("model.dao.impl.ComprobanteVentaDaoImpl.GrabarVenta()" + venta.toString());
                ps.setString(1, num);
                ps.setString(2, venta.getId_producto());
                ps.setString(3, venta.getCantidad());
                ps.setString(4, venta.getPrecio());
                ps.setInt(5, venta.getId_usuario());
                ps.setString(6, venta.getTipo());
                ps.setInt(7, venta.getId_cliente());
                ps.setInt(8, venta.getCantProductos());
                ps.setDouble(9, venta.getTotal());
                ps.setDouble(10, venta.getIgv());
                ps.setDouble(11, venta.getNeto());
                ps.registerOutParameter(12, Types.INTEGER);
                ps.execute();
                // devuelve el valor del parametro de salida del procedimiento
                int resultado = ps.getInt(12);
                if (resultado > 0) {
//                    cn.commit();
                    logger.info("OK");
                    mensaje = num;
                } else {
                    //cn.rollback();
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
    public boolean verificarNumComprobante(String numeradorcomprobante) throws Exception {
        boolean estado = false;
        String sqlResult = "";
        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectComprobantexNumero.sql");
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
                ps.setString(1, numeradorcomprobante);
                ResultSet rs = ps.executeQuery();

                if (rs.next() == true) {
                    estado = false;
                } else {
                    estado = true;
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
        return estado;
    }

    @Override
    public List<ComprobanteVenta> ListarVenta200() throws Exception {
        String sqlResult = "";
        List<ComprobanteVenta> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectVenta200.sql");
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
                    ComprobanteVenta temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {

                        temp = new ComprobanteVenta();
                        temp.setId_comprobante(rs.getInt("id_comprobante"));
                        temp.setEstado(rs.getString("estado"));
                        temp.setNumero_comprobante(rs.getString("numero_comprobante"));
                        temp.setCantProductos(rs.getInt("items"));
                        temp.setEstado(rs.getString("estado"));
                        temp.setFecha_reg(rs.getDate("fecha_reg"));
                        temp.setId_usuario(rs.getInt("id_usuario"));
                        temp.setIgv(rs.getDouble("igv"));
                        temp.setNeto(rs.getDouble("neto"));
                        temp.setTotal(rs.getDouble("total"));
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
    public String EliminarVenta(String numero, String id) throws Exception {
        String mensaje = null;
        String sqlResult = "";

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/eliminarVenta.sql");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new Exception("Problemas del sistema...");
        } catch (Throwable ex) {
            logger.error(ex);
        }

        if (cn != null) {

            try {

                CallableStatement ps = cn.prepareCall(sqlResult);
                ps.setString(1, numero);
                ps.setInt(2, Integer.parseInt(id));
                ps.registerOutParameter(3, Types.INTEGER);
                ps.execute();
                // devuelve el valor del parametro de salida del procedimiento
                int resultado = ps.getInt(3);
                if (resultado > 0) {
//                    cn.commit();
                    logger.info("OK");
                    mensaje = "OK";
                } else {
                    //cn.rollback();
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
    public List<ComprobanteVenta> ListarVentaxFecha(String fecha) throws Exception {
        String sqlResult = "";
        List<ComprobanteVenta> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectVentaxFecha.sql");
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
                ps.setString(1, fecha);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    listTemp = new ArrayList<>();
                    ComprobanteVenta temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {
                        temp = new ComprobanteVenta();
                        temp.setId_comprobante(rs.getInt("id_comprobante"));
                        temp.setEstado(rs.getString("estado"));
                        temp.setNumero_comprobante(rs.getString("numero_comprobante"));
                        temp.setCantProductos(rs.getInt("items"));
                        temp.setEstado(rs.getString("estado"));
                        temp.setFecha_reg(rs.getDate("fecha_reg"));
                        temp.setId_usuario(rs.getInt("id_usuario"));
                        temp.setIgv(rs.getDouble("igv"));
                        temp.setNeto(rs.getDouble("neto"));
                        temp.setTotal(rs.getDouble("total"));
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
    public List<ComprobanteVenta> ListarVentaxRango(String ini, String fin) throws Exception {
        String sqlResult = "";
        List<ComprobanteVenta> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectVentaRango.sql");
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
                ps.setString(1, ini);
                ps.setString(2, fin);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    listTemp = new ArrayList<>();
                    ComprobanteVenta temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {
                        temp = new ComprobanteVenta();
                        temp.setId_comprobante(rs.getInt("id_comprobante"));
                        temp.setEstado(rs.getString("estado"));
                        temp.setNumero_comprobante(rs.getString("numero_comprobante"));
                        temp.setCantProductos(rs.getInt("items"));
                        temp.setEstado(rs.getString("estado"));
                        temp.setFecha_reg(rs.getDate("fecha_reg"));
                        temp.setId_usuario(rs.getInt("id_usuario"));
                        temp.setIgv(rs.getDouble("igv"));
                        temp.setNeto(rs.getDouble("neto"));
                        temp.setTotal(rs.getDouble("total"));
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
    public List<ComprobanteVenta> ListarVentasxmesxaño(String ini, String fin) throws Exception {
        String sqlResult = "";
        List<ComprobanteVenta> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectventaReporteGrafico.sql");
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
                ps.setString(1, ini);
                ps.setString(2, fin);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    listTemp = new ArrayList<>();
                    ComprobanteVenta temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {
                        temp = new ComprobanteVenta();
                        temp.setEstado(rs.getString("intervalo"));
                        temp.setTotal(rs.getDouble("totalpormes"));
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
