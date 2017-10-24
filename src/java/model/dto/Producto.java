/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Alexander
 */
public class Producto {

    int id_producto;
    String descripcion;
    String marca;
    String presentacion;
    String medida;
    byte[] foto;
    String estado;
    Date fecha_reg;
    Date fecha_mod;
    int usuario_reg;
    int usuario_mod;
    String StringBase64;
    String type;
    String prod_insu;
    double pv1;
    double pv2;
    double pv3;
    double existencia;
    
    public Producto() {
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(Date fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    public Date getFecha_mod() {
        return fecha_mod;
    }

    public void setFecha_mod(Date fecha_mod) {
        this.fecha_mod = fecha_mod;
    }

    public int getUsuario_reg() {
        return usuario_reg;
    }

    public void setUsuario_reg(int usuario_reg) {
        this.usuario_reg = usuario_reg;
    }

    public int getUsuario_mod() {
        return usuario_mod;
    }

    public void setUsuario_mod(int usuario_mod) {
        this.usuario_mod = usuario_mod;
    }

    public String getStringBase64() {
        return StringBase64;
    }

    public void setStringBase64(String StringBase64) {
        this.StringBase64 = StringBase64;
    }

    public String getProd_insu() {
        return prod_insu;
    }

    public void setProd_insu(String prod_insu) {
        this.prod_insu = prod_insu;
    }

 

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPv1() {
        return pv1;
    }

    public void setPv1(double pv1) {
        this.pv1 = pv1;
    }

    public double getPv2() {
        return pv2;
    }

    public void setPv2(double pv2) {
        this.pv2 = pv2;
    }

    public double getPv3() {
        return pv3;
    }

    public void setPv3(double pv3) {
        this.pv3 = pv3;
    }

    public double getExistencia() {
        return existencia;
    }

    public void setExistencia(double existencia) {
        this.existencia = existencia;
    }

    public Producto loadRs(ResultSet rs) throws SQLException {
        Producto temp = new Producto();
        temp.setId_producto(rs.getInt("id_producto"));
        temp.setDescripcion(rs.getString("descripcion"));
        temp.setMarca(rs.getString("marca"));
        temp.setPresentacion(rs.getString("presentacion"));
        temp.setMedida(rs.getString("medida"));
        temp.setFecha_mod(rs.getDate("fecha_mod"));
        temp.setFecha_reg(rs.getDate("fecha_reg"));
        temp.setUsuario_reg(rs.getInt("usuario_reg"));
        temp.setUsuario_mod(rs.getInt("usuario_mod"));
        temp.setFoto(rs.getBytes("foto"));
        temp.setType(rs.getString("type"));
        temp.setProd_insu(rs.getString("producto_insumo"));
        temp.setPv1(rs.getDouble("pv1"));
        temp.setPv2(rs.getDouble("pv2"));
        temp.setPv3(rs.getDouble("pv3"));
        temp.setExistencia(rs.getDouble("existencia"));
//        Encoder code=Base64.getEncoder();
//        String encod=code.encodeToString(rs.getBytes("foto"));
//        temp.setStringBase64(encod);
        return temp;

    }

    @Override
    public String toString() {
        return "Producto{" + "id_producto=" + id_producto + ", descripcion=" + descripcion + ", marca=" + marca + ", presentacion=" + presentacion + ", medida=" + medida + ", foto=" + foto + ", estado=" + estado + ", fecha_reg=" + fecha_reg + ", fecha_mod=" + fecha_mod + ", usuario_reg=" + usuario_reg + ", usuario_mod=" + usuario_mod + ", StringBase64=" + StringBase64 + ", type=" + type + ", prod_insu=" + prod_insu + ", pv1=" + pv1 + ", pv2=" + pv2 + ", pv3=" + pv3 + ", existencia=" + existencia + '}';
    }

    
}
