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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import model.dao.ProductoDao;
import model.dto.Producto;
import org.apache.log4j.Logger;
import util.Util;
import util.jdbc.ConectaDB;

/**
 *
 * @author Alexander
 */
public class ProductoDaoImpl implements ProductoDao {

    final static Logger logger = Logger.getLogger(ProductoDaoImpl.class);
    Util uti = new Util();
    Connection cn = null;
    ConectaDB db = new ConectaDB();

    public ProductoDaoImpl() {
    }

    @Override
    public String insertarProducto(Producto pro) throws Exception {
        String mensaje = null;
        String sqlResult = "";

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/insertProducto.sql");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new Exception("Problemas del sistema...");
        } catch (Throwable ex) {
            logger.error(ex);
        }

        if (cn != null) {

            try {

                PreparedStatement ps = cn.prepareStatement(sqlResult);
                ps.setString(1, pro.getDescripcion().toUpperCase());
                ps.setString(2, pro.getMarca().toUpperCase());
                ps.setString(3, pro.getPresentacion().toUpperCase());
                ps.setString(4, pro.getMedida().toUpperCase());
                ps.setBytes(5, pro.getFoto());
                ps.setInt(6, pro.getUsuario_reg());
                ps.setString(7, pro.getType());
                ps.setString(8, pro.getProd_insu());
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
    public List<Producto> getProductos() throws Exception {
        String sqlResult = "";
        List<Producto> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectProducto.sql");
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
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    listTemp = new ArrayList<>();
                    Producto temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {

                        temp = new Producto();
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
    public Producto getProductoXid(int id) throws Exception {
        String sqlResult = "";
        Producto pro = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectProductoID.sql");
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
                    pro = new Producto();
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

    @Override
    public String actualizarProducto(Producto pro) throws Exception {

        String mensaje = null;
        String sqlResult = "";

        try {
            cn = db.getConnection();
            if (pro.getType() != null) {
                if (pro.getType().equals("image/jpeg") && pro.getFoto() != null) {
                    sqlResult = uti.getLocalResource("/sql/updateProducto.sql");
                } else {
                    sqlResult = uti.getLocalResource("/sql/updateProductoSinfoto.sql");
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            throw new Exception("Problemas del sistema...");
        } catch (Throwable ex) {
            logger.error(ex);
        }

        if (cn != null) {

            try {

                PreparedStatement ps = cn.prepareStatement(sqlResult);

                ps.setString(1, pro.getDescripcion().toUpperCase());
                ps.setString(2, pro.getMarca().toUpperCase());
                ps.setString(3, pro.getPresentacion().toUpperCase());
                ps.setString(4, pro.getMedida().toUpperCase());
                ps.setString(5, pro.getProd_insu().toUpperCase());
                ps.setInt(6, pro.getUsuario_mod());
                if (pro.getType().equals("image/jpeg")) {
                    ps.setBytes(7, pro.getFoto());
                    ps.setString(8, pro.getType());
                    ps.setInt(9, pro.getId_producto());
                } else {
                    ps.setInt(7, pro.getId_producto());
                }
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
    public String registrarPrecioVenta(Producto pro) throws Exception {
        String mensaje = null;
        String sqlResult = "";

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/updatePrecioventa.sql");

        } catch (SQLException ex) {
            logger.error(ex);
            throw new Exception("Problemas del sistema...");
        } catch (Throwable ex) {
            logger.error(ex);
        }

        if (cn != null) {

            try {

                PreparedStatement ps = cn.prepareStatement(sqlResult);

                ps.setDouble(1, pro.getPv1());
                ps.setDouble(2, pro.getPv2());
                ps.setDouble(3, pro.getPv3());
                ps.setInt(4, pro.getUsuario_mod());
                ps.setInt(5, pro.getId_producto());

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
    public List<Producto> listarproductoscomboventa() throws Exception {
        String sqlResult = "";
        List<Producto> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectProdComboVenta.sql");
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
                    Producto temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {

                        temp = new Producto();
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
    public List<Producto> getProductosSinfoto() throws Exception {
        String sqlResult = "";
        List<Producto> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectProducto.sql");
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
                  ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    listTemp = new ArrayList<>();
                    Producto temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {

                        temp = new Producto();
                        temp.setId_producto(rs.getInt("id_producto"));
                        temp.setDescripcion(rs.getString("descripcion"));
                        temp.setPresentacion(rs.getString("presentacion"));
                        temp.setMedida(rs.getString("medida"));
                        temp.setMarca(rs.getString("marca"));
                        temp.setProd_insu(rs.getString("producto_insumo"));
                        temp.setExistencia(rs.getDouble("existencia"));
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
    public String getfotoId(String id) throws Exception {
        String sqlResult = "";
        String pro = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectFotoProductoID.sql");
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
                ps.setInt(1, Integer.parseInt(id));
                ResultSet rs = ps.executeQuery();
                byte[] foto = null;
                if (rs.next()) {
                    foto = rs.getBytes("foto");
                }
                if (foto != null) {
                    Base64.Encoder code = Base64.getEncoder();
                    pro = code.encodeToString(foto);
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

    @Override
    public List<Producto> listarproductoscombocompra() throws Exception {
        String sqlResult = "";
        List<Producto> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectProdComboCompra.sql");
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
                    Producto temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {

                        temp = new Producto();
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
    public List<Producto> listainsumos() throws Exception {
        String sqlResult = "";
        List<Producto> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectInsumo.sql");
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
                    Producto temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {

                        temp = new Producto();
                        temp.setDescripcion(rs.getString("descripcion"));
                        temp.setId_producto(rs.getInt("id_producto"));
                        temp.setMarca(rs.getString("marca"));
                        temp.setPresentacion(rs.getString("presentacion"));
                        temp.setMedida(rs.getString("medida"));
                        temp.setProd_insu(rs.getString("producto_insumo"));
                        temp.setExistencia(rs.getDouble("existencia"));
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
    public List<Producto> listaProductosFinales(String parametro) throws Exception {
        String sqlResult = "";
        List<Producto> listTemp = null;

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectProductoFinal.sql");
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
                    ps.setString(i,parametro.toUpperCase()); 
                }
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {

                    listTemp = new ArrayList<>();
                    Producto temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {
                        Base64.Encoder code = Base64.getEncoder();
                        temp = new Producto();
                        temp.setDescripcion(rs.getString("descripcion"));
                        temp.setId_producto(rs.getInt("id_producto"));
                        temp.setMarca(rs.getString("marca"));
                        temp.setPresentacion(rs.getString("presentacion"));
                        temp.setMedida(rs.getString("medida"));
                        temp.setProd_insu(rs.getString("producto_insumo"));
                        temp.setFoto(rs.getBytes("foto"));
                        if (temp.getFoto()!=null) {
                         temp.setStringBase64(code.encodeToString(temp.getFoto()));  
                         temp.setFoto(null);
                        }
                        listTemp.add(temp);
                        temp=null;

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
