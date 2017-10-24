/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import java.util.Date;

/**
 *
 * @author Alexander
 */
public class Produccion {
    
    Date fecha,fecha_reg;
    String num, des_estado, operador,doc;
    int estado ,cant_reglas,id_produccion;

    public Date getFecha() {
        return fecha;
    }

    public Date getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(Date fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDes_estado() {
        return des_estado;
    }

    public void setDes_estado(String des_estado) {
        this.des_estado = des_estado;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getCant_reglas() {
        return cant_reglas;
    }

    public void setCant_reglas(int cant_reglas) {
        this.cant_reglas = cant_reglas;
    }

    public int getId_produccion() {
        return id_produccion;
    }

    public void setId_produccion(int id_produccion) {
        this.id_produccion = id_produccion;
    }
    
}
