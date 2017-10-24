package model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Perfil entity. @author MyEclipse Persistence Tools
 */
public class Perfil implements java.io.Serializable {

    // Fields
    private int idperfil;
    private String codigo;
    private String tipo;
    private String descripcion;
    private String estado;

    // Constructors
    /**
     * default constructor
     */
    public Perfil() {
    }

    /**
     * full constructor
     */
    public Perfil(String codigo, String tipo, String descripcion,
            String estado) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    // Property accessors
    public int getIdperfil() {
        return this.idperfil;
    }

    public void setIdperfil(int idperfil) {
        this.idperfil = idperfil;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Perfil loadRs(ResultSet rs) throws SQLException {

        Perfil temp = new Perfil();
        temp.setIdperfil(rs.getInt("id_perfil"));
        temp.setCodigo(rs.getString("codigo"));
        temp.setTipo(rs.getString("tipo"));
        temp.setDescripcion(rs.getString("descripcion"));
        temp.setEstado(rs.getString("estado"));

        return temp;
    }

    @Override
    public String toString() {
        return "Perfil{" + "idperfil=" + idperfil + ", codigo=" + codigo + ", tipo=" + tipo + ", descripcion=" + descripcion + ", estado=" + estado +'}';
    }

}
