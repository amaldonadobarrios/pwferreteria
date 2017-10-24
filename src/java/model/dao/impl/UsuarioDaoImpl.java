package model.dao.impl;

import model.dao.UsuarioDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dto.Usuario;
import org.apache.log4j.Logger;
import util.BatEncriptador;
import util.Util;
import util.jdbc.ConectaDB;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author FARID
 */
public class UsuarioDaoImpl implements UsuarioDao {

    final static Logger logger = Logger.getLogger(UsuarioDaoImpl.class);
    Util uti = new Util();
    Connection cn = null;
    ConectaDB db=new ConectaDB();
    
  //  Datasource db = new Datasource();
    @Override
    public String insertarUsuario(Usuario usuario) throws Exception {

        String mensaje = null;
        String sqlResult = "";

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/insertUsuario.sql");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new Exception("Problemas del sistema...");
        } catch (Throwable ex) {
            logger.error(ex);
        }

        if (cn != null) {

            try {
                
                PreparedStatement ps = cn.prepareStatement(sqlResult);
                ps.setString(1, usuario.getUsuario());
                ps.setString(2, BatEncriptador.getInstance().Encripta(usuario.getPassword()));
                ps.setString(3, usuario.getDni());
                ps.setString(4, usuario.getApellidoPaterno().toUpperCase());
                ps.setString(5, usuario.getApellidoMaterno().toUpperCase());
                ps.setString(6, usuario.getNombres().toUpperCase());
                ps.setString(7, usuario.getTelefonos());
                ps.setInt(8, usuario.getUsuarioReg());
                ps.setInt(9, usuario.getPerfil().getIdperfil());

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
    public String actualizarUsuario(Usuario usuario) throws Exception {

          String mensaje = null;
        String sqlResult = "";

        try {
            cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/updateUsuario.sql");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new Exception("Problemas del sistema...");
        } catch (Throwable ex) {
            logger.error(ex);
        }

        if (cn != null) {

            try {
                
                PreparedStatement ps = cn.prepareStatement(sqlResult);
                
                ps.setString(1, usuario.getUsuario());
                ps.setString(2,BatEncriptador.getInstance().Encripta(usuario.getPassword()));
                ps.setString(3, usuario.getDni());
                ps.setString(4, usuario.getApellidoPaterno().toUpperCase());
                ps.setString(5, usuario.getApellidoMaterno().toUpperCase());
                ps.setString(6, usuario.getNombres().toUpperCase());
                ps.setString(7, usuario.getTelefonos());
                ps.setString(8, usuario.getEstado());
                ps.setInt(9, usuario.getUsuarioMod());
                ps.setInt(10, usuario.getPerfil().getIdperfil());
                ps.setInt(11, usuario.getIdUsuario());

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
    public List<Usuario> getUsuarios() throws Exception  {

        String sqlResult = "";
        List<Usuario> listTemp = null;

        try {
             cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectUsuario.sql");
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
                    Usuario temp;

                    // regresa el puntero al principio
                    rs.beforeFirst();
                    while (rs.next()) {

                        temp = new Usuario();
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
    public Usuario getUsuarioXid(int id) throws Exception{
        String sqlResult = "";
        Usuario usu=null;
         
       try {
             cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectUsuarioID.sql");
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
                    usu = new Usuario();
                    usu = usu.loadRs(rs);
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

        return usu;
    }

    @Override
    public Usuario validarAcceso(String usuario, String clave) throws Exception {
 String sqlResult = "";
        Usuario usu=null;
         
       try {
             cn = db.getConnection();
            sqlResult = uti.getLocalResource("/sql/selectValidarUsuario.sql");
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
                ps.setString(1, usuario);
                ps.setString(2, BatEncriptador.getInstance().Encripta(clave));
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    usu = new Usuario();
                    usu = usu.loadRs(rs);
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

        return usu;

    }

}
