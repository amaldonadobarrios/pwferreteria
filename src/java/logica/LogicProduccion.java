/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import logica.grilla.LogicTablaReglaProduccion;
import model.dao.ProduccionDao;
import model.dao.ProductoDao;
import model.dao.impl.ProduccionDaoImpl;
import model.dao.impl.ProductoDaoImpl;
import model.dto.ListaProduccion;
import model.dto.ListaReglaProduccion;
import model.dto.Produccion;
import model.dto.Producto;

/**
 *
 * @author Alexander
 */
public class LogicProduccion {

    private LogicProduccion() {
    }

    public static LogicProduccion getInstance() {
        return LogicProduccionHolder.INSTANCE;
    }

    public String VerificarErrorProduccion(int contador, String id_regla, String id_producto, String cantidad, String cant_insumos) throws Exception{
         String estado = null;
         List<ListaReglaProduccion> list;
        ProduccionDao dao = new ProduccionDaoImpl();
        list = dao.VerificarErrorProduccion(contador,  id_regla,  id_producto,  cantidad,  cant_insumos);
        
        return "hola"; 
    }

    public List<Produccion> listarProduccion200() throws Exception {
        List<Produccion> list = null;
        ProduccionDao dao = new ProduccionDaoImpl();
        list = dao.ListarProduccion200();
        return list;
    }

    public List<Produccion> listarProduccionxFecha(String fecha) throws Exception {
        List<Produccion> list = null;
        ProduccionDao dao = new ProduccionDaoImpl();
        list = dao.ListarProduccionxFecha(fecha);
        return list;
    }

    public String eliminarProduccion(String id) throws Exception {
        String respuesta=null;
        ProduccionDao dao=new ProduccionDaoImpl();
        respuesta=dao.EliminarProduccion(id);
        return respuesta;
    }

    private static class LogicProduccionHolder {

        private static final LogicProduccion INSTANCE = new LogicProduccion();
    }

    public int VerificarProduccion(int contador, String id_regla, String id_producto, String cantidad, String cant_insumos)throws Exception {
        int estado = 1;
        ProduccionDao dao = new ProduccionDaoImpl();
        estado = dao.VerificarProduccion(contador,  id_regla,  id_producto,  cantidad,  cant_insumos);
        return estado; 
    }

    public String GrabarProduccion(ListaReglaProduccion produccion) throws Exception {
        String estado = null;
        ProduccionDao dao = new ProduccionDaoImpl();
        estado = dao.GrabarProduccion(produccion);
        return estado;
    }

    public ListaProduccion buscarRegla(int id_regla) throws Exception {
        ListaProduccion regla = null;
        ProduccionDao dao = new ProduccionDaoImpl();
        regla = dao.BuscarRegla(id_regla);
        return regla;
    }

    public String MostrarInsumos(String id_regla, String id_producto) throws Exception {
        List<ListaReglaProduccion> lista = null;
        String respuesta = null;
        ProduccionDao dao = new ProduccionDaoImpl();
        lista = dao.MostrarInsumo(Integer.parseInt(id_regla), Integer.parseInt(id_producto));
        respuesta = LogicTablaReglaProduccion.getInstance().construirGrillaMostrarInsumos(lista);
        return respuesta;
    }

    public String eliminarRegla(String id_regla, String id_producto) throws Exception {
        String respuesta = null;
        ProduccionDao dao = new ProduccionDaoImpl();
        respuesta = dao.EliminarRegla(Integer.parseInt(id_regla), Integer.parseInt(id_producto));
        return respuesta;
    }

    public String GrabarRegla(ListaReglaProduccion regla) throws Exception {
        String estado = null;
        ProduccionDao dao = new ProduccionDaoImpl();
        estado = dao.GrabarRegla(regla);
        return estado;
    }

    public boolean verificarRegla(int id_producto) throws Exception {
        boolean validacion = false;
        ProduccionDao dao = new ProduccionDaoImpl();
        validacion = dao.validarReglaProducto(id_producto);
        return validacion;
    }

    public List<Producto> listarInsumos() throws Exception {
        List<Producto> lista = null;
        ProductoDao dao = new ProductoDaoImpl();
        lista = dao.listainsumos();
        return lista;
    }

    public List<Producto> listarProductoFinalLike(String parametro) throws Exception {
        List<Producto> lista = null;
        ProductoDao dao = new ProductoDaoImpl();
        lista = dao.listaProductosFinales(parametro);
        return lista;
    }

    public List<ListaReglaProduccion> listarReglasActivas() throws Exception {
        List<ListaReglaProduccion> lista = null;
        ProduccionDao dao = new ProduccionDaoImpl();
        lista = dao.listarreglas();
        return lista;
    }
}
