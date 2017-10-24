/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.dto.ListaProduccion;
import model.dto.ListaReglaProduccion;
import model.dto.Produccion;

/**
 *
 * @author 31424836
 */
public interface ProduccionDao {

    public List<ListaReglaProduccion> listarreglas() throws Exception;

    public boolean validarReglaProducto(int id_producto) throws Exception;

    public String GrabarRegla(ListaReglaProduccion regla) throws Exception;

    public String EliminarRegla(int id_regla, int id_producto) throws Exception;

    public List<ListaReglaProduccion> MostrarInsumo(int id_regla, int id_producto) throws Exception;

    public ListaProduccion BuscarRegla(int id_regla) throws Exception;

    public String GrabarProduccion(ListaReglaProduccion produccion) throws Exception;

    public int VerificarProduccion(int contador, String id_regla, String id_producto, String cantidad, String cant_insumos) throws Exception;

    public List<ListaReglaProduccion> VerificarErrorProduccion(int contador, String id_regla, String id_producto, String cantidad, String cant_insumos) throws Exception;

    public List<Produccion> ListarProduccion200() throws Exception;

    public List<Produccion> ListarProduccionxFecha(String fecha)throws Exception;

    public String EliminarProduccion(String id)throws Exception;
}
