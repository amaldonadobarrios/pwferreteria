/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import java.util.Date;

/**
 *
 * @author 31424836
 */
public class ComprobanteCompra {

    private int id_comprobante;
    private String numero_comprobante;
    private String tipo;
    private String fecha;
    private int id_proveedor;
    private String estado;
    private int id_usuario;
    private Date fecha_reg;
    private String numero_detalle;
    private String id_producto;
    private String cantidad;
    private String subtotal;
    private int cantProductos;
    private double total;
    private double igv;
    private double neto;

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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
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

    public int getCantProductos() {
        return cantProductos;
    }

    public void setCantProductos(int cantProductos) {
        this.cantProductos = cantProductos;
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

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "ComprobanteCompra{" + "id_comprobante=" + id_comprobante + ", numero_comprobante=" + numero_comprobante + ", tipo=" + tipo + ", fecha=" + fecha + ", id_proveedor=" + id_proveedor + ", estado=" + estado + ", id_usuario=" + id_usuario + ", fecha_reg=" + fecha_reg + ", numero_detalle=" + numero_detalle + ", id_producto=" + id_producto + ", cantidad=" + cantidad + ", subtotal=" + subtotal + ", cantProductos=" + cantProductos + ", total=" + total + ", igv=" + igv + ", neto=" + neto + '}';
    }

}
