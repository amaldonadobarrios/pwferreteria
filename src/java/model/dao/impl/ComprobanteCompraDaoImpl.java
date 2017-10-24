/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.dao.ComprobanteCompraDao;
import model.dto.ComprobanteCompra;
import org.apache.log4j.Logger;
import util.DirDate;
import util.DirFecha;
import util.Util;
import util.jdbc.ConectaDB;

/**
 *
 * @author 31424836
 */
public class ComprobanteCompraDaoImpl implements ComprobanteCompraDao {

    final Logger logger = Logger.getLogger(ComprobanteCompraDaoImpl.class);
    Util uti = new Util();
    Connection cn = null;
    ConectaDB db = new ConectaDB();

    @Override
    public String GrabarCompra(ComprobanteCompra compra) throws Exception {
        String mensaje = null;
        String sqlResult = "";

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/insertCompra.sql");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new Exception("Problemas del sistema...");
        } catch (Throwable ex) {
            logger.error(ex);
        }

        if (cn != null) {

            try {
                System.out.println("model.dao.impl.ComprobanteCompraDaoImpl.GrabarCompra()" + "voy a grabar compra");
                CallableStatement ps = cn.prepareCall(sqlResult);
                String num = compra.getTipo() + "-" + compra.getNumero_comprobante() + "-" + DirDate.getInstance().getFechaYYYY();
                System.out.println("model.dao.impl.ComprobanteCompraDaoImpl.GrabarCompra()" + compra.toString());
                ps.setString(1, num);
                ps.setString(2, compra.getId_producto());
                ps.setString(3, compra.getCantidad());
                ps.setString(4, compra.getSubtotal());
                ps.setInt(5, compra.getId_usuario());
                ps.setString(6, compra.getTipo());
                ps.setInt(7, compra.getId_proveedor());
                ps.setInt(8, compra.getCantProductos());
                ps.setDouble(9, compra.getTotal());
                ps.setDouble(10, compra.getIgv());
                ps.setDouble(11, compra.getNeto());
                ps.setString(12, compra.getFecha());
                ps.registerOutParameter(13, Types.INTEGER);
                ps.registerOutParameter(14, Types.VARCHAR);
                ps.execute();
                // devuelve el valor del parametro de salida del procedimiento
                int resultado = ps.getInt(13);
                String id_compra = ps.getString(14);
                System.out.println("model.dao.impl.ComprobanteCompraDaoImpl.GrabarCompra()------ID COMPRA" + id_compra);
                if (resultado > 0) {
//                    cn.commit();
                    logger.info("OK");
                    mensaje = num + "%" + id_compra;
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
            sqlResult = uti.getLocalResource("/sql/selectComprobantexNumeroCompra.sql");
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
    public List<ComprobanteCompra> ListarCompra200() throws Exception {
        String sqlResult = "";
        List<ComprobanteCompra> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectCompra200.sql");
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
                    ComprobanteCompra temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {

                        temp = new ComprobanteCompra();
                        temp.setId_comprobante(rs.getInt("id_comprobante"));
                        temp.setEstado(rs.getString("estado"));
                        temp.setNumero_comprobante(rs.getString("numero_comprobante"));
                        temp.setCantProductos(rs.getInt("items"));
                        temp.setEstado(rs.getString("estado"));
                        temp.setFecha_reg(rs.getDate("fecha_reg"));
                        temp.setFecha(rs.getString("fecha"));
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
    public String EliminarCompra(String numero, String id) throws Exception {
        String mensaje = null;
        String sqlResult = "";

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/eliminarCompra.sql");
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
    public List<ComprobanteCompra> ListarCompraxFecha(String fecha) throws Exception {
        String sqlResult = "";
        List<ComprobanteCompra> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectCompraxFecha.sql");
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
                    ComprobanteCompra temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {
                        temp = new ComprobanteCompra();
                        temp.setId_comprobante(rs.getInt("id_comprobante"));
                        temp.setEstado(rs.getString("estado"));
                        temp.setNumero_comprobante(rs.getString("numero_comprobante"));
                        temp.setCantProductos(rs.getInt("items"));
                        temp.setEstado(rs.getString("estado"));
                        temp.setFecha_reg(rs.getDate("fecha_reg"));
                        temp.setFecha(rs.getString("fecha"));
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
    public List<ComprobanteCompra> ListarCompraxRango(String ini, String fin) throws Exception {
        String sqlResult = "";
        List<ComprobanteCompra> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectCompraRango.sql");
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
                    ComprobanteCompra temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {
                        temp = new ComprobanteCompra();
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
    public List<ComprobanteCompra> ListarCompraxmesxaño(String ini, String fin) throws Exception {
        String sqlResult = "";
        List<ComprobanteCompra> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectCompraReporteGrafico.sql");
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
                    ComprobanteCompra temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {
                        temp = new ComprobanteCompra();
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
