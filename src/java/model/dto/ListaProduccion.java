/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

/**
 *
 * @author 31424836
 */
public class ListaProduccion {
   private int id_regla;
   private int id_producto;
   private int nro_insumos;
   private String descripcion;
   private String marca;
   private String presentacion;
   private String medida;
   private double cantidad_produccion;

    public int getId_regla() {
        return id_regla;
    }

    public void setId_regla(int id_regla) {
        this.id_regla = id_regla;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getNro_insumos() {
        return nro_insumos;
    }

    public void setNro_insumos(int nro_insumos) {
        this.nro_insumos = nro_insumos;
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

    public double getCantidad_produccion() {
        return cantidad_produccion;
    }

    public void setCantidad_produccion(double cantidad_produccion) {
        this.cantidad_produccion = cantidad_produccion;
    }

    @Override
    public String toString() {
        return "ListaProduccion{" + "id_regla=" + id_regla + ", id_producto=" + id_producto + ", nro_insumos=" + nro_insumos + ", descripcion=" + descripcion + ", marca=" + marca + ", presentacion=" + presentacion + ", medida=" + medida + ", cantidad_produccion=" + cantidad_produccion + '}';
    }
   
   
}
