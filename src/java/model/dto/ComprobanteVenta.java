/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import java.sql.Date;

/**
 *
 * @author Alexander
 */
public class ComprobanteVenta {
    
    public ComprobanteVenta() {
    }
    
    public static ComprobanteVenta getInstance() {
        return ComprobanteVentaHolder.INSTANCE;
    }

    public int getId_comprobante() {
        return id_comprobante;
    }

    public void setId_comprobante(int id_comprobante) {
        this.id_comprobante = id_comprobante;
    }

    public String getNumero_comprobante() {
        return numero_comprobante;
    }

    public void setNumero_comprobante(String numero_comprobante) {
        this.numero_comprobante = numero_comprobante;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Date getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(Date fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    public String getNumero_detalle() {
        return numero_detalle;
    }

    public void setNumero_detalle(String numero_detalle) {
        this.numero_detalle = numero_detalle;
    }

  

    public int getCantProductos() {
        return cantProductos;
    }

    public void setCantProductos(int cantProductos) {
        this.cantProductos = cantProductos;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getNeto() {
        return neto;
    }

    public void setNeto(double neto) {
        this.neto = neto;
    }
    
    
    private static class ComprobanteVentaHolder {

        private static final ComprobanteVenta INSTANCE = new ComprobanteVenta();
    }
    
    private int  id_comprobante;
    private String numero_comprobante;
    private String tipo;
    private Date fecha;
    private int id_cliente;
    private String estado;
    private int id_usuario;
    private Date fecha_reg;
    private String numero_detalle;
    private String id_producto;
    private String cantidad;
    private String precio;
    private int cantProductos;
    private double total;
    private double igv;
    private double neto;

    @Override
    public String toString() {
        return "ComprobanteVenta{" + "id_comprobante=" + id_comprobante + ", numero_comprobante=" + numero_comprobante + ", tipo=" + tipo + ", fecha=" + fecha + ", id_cliente=" + id_cliente + ", estado=" + estado + ", id_usuario=" + id_usuario + ", fecha_reg=" + fecha_reg + ", numero_detalle=" + numero_detalle + ", id_producto=" + id_producto + ", cantidad=" + cantidad + ", precio=" + precio + ", cantProductos=" + cantProductos + ", total=" + total + ", igv=" + igv + ", neto=" + neto + '}';
    }  
}
