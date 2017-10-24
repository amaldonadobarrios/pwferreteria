/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.LogicProduccion;
import logica.LogicProducto;
import logica.grilla.LogicTablaProducto;
import logica.grilla.LogicTablaReglaProduccion;
import model.dao.ProductoDao;
import model.dao.impl.ProductoDaoImpl;
import model.dto.ListaProduccion;
import model.dto.ListaReglaProduccion;
import model.dto.Producto;
import model.dto.Usuario;
import util.HtmlUtil;

/**
 *
 * @author Alexander
 */
public class ServProduccion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int respuesta = this.validaSesion(request, response);
        if (respuesta == 1) {
            try {
                String evento = null;
                evento = request.getParameter("evento");
                System.out.println("ServProduccion  evento : " + evento);
                if (evento != null) {
                    if (evento.equals("BuscarProductoFinal")) {
                        BuscarProductoFinal(request, response);
                    } else if (evento.equals("AñadirInsumo")) {
                        AñadirInsumo(request, response);
                    } else if (evento.equals("EliminarInsumoAJAX")) {
                        EliminarInsumoAJAX(request, response);
                    } else if (evento.equals("RegistrarRegla")) {
                        RegistrarRegla(request, response);
                    } else if (evento.equals("EliminarRegla")) {
                        EliminarRegla(request, response);
                    } else if (evento.equals("ListarInsumosxReglaxProd")) {
                        ListarInsumosxReglaxProd(request, response);
                    } else if (evento.equals("AñadirProduccion")) {
                        AñadirProduccion(request, response);
                    } else if (evento.equals("EliminarProductofinal")) {
                        EliminarProductofinal(request, response);
                    } else if (evento.equals("verificarProduccion")) {
                        verificarProduccion(request, response);
                    }else if (evento.equals("verificarErrorProduccion")) {
                        verificarErrorProduccion(request,response);
                    }  else if (evento.equals("RegitrarProduccion")) {
                        RegitrarProduccion(request, response);
                    } else if (evento.equals("EliminarProduccionAjax")) {
                        EliminarProduccionAjax(request,response);  
                    }

                }
            } catch (Exception ex) {
                Logger.getLogger(ServProduccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private int validaSesion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int respuesta = 1;

        HttpSession session = request.getSession();

        if (session != null) {

            Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");
            String ID = (String) session.getAttribute("ID");

            if (ID == null || usuarioSesion == null || !ID.equals(session.getId())) {
                respuesta = 0;
                session.invalidate();
                request.setAttribute("msg", "Sesion Expirada");
                HtmlUtil.getInstance().escrituraHTML(response, "NOSESION");

            }

        } else {
            respuesta = 0;
            session.invalidate();
            request.setAttribute("msg", "Sesion Expirada");
            HtmlUtil.getInstance().escrituraHTML(response, "NOSESION");
        }

        return respuesta;
    }

    private void BuscarProductoFinal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("control.ServProduccion.BuscarProductoFinal()");
        String parametro = null;
        parametro = request.getParameter("parametro");
        List<Producto> lista;
        if (parametro != null) {
            ProductoDao dao = new ProductoDaoImpl();
            lista = dao.listaProductosFinales(parametro);
            String respuesta = null;
            respuesta = LogicTablaProducto.getInstance().construirGrillaProducto(lista);
            HtmlUtil.getInstance().escrituraHTML(response, respuesta);
        }

    }

    private void AñadirInsumo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("control.ServProduccion.AñadirInsumo()");
        HttpSession session = request.getSession();
        List<ListaReglaProduccion> listatemp = new ArrayList<ListaReglaProduccion>();
        List<ListaReglaProduccion> lista = new ArrayList<ListaReglaProduccion>();
        try {
            lista = (List<ListaReglaProduccion>) session.getAttribute("listainsumos");
        } catch (Exception e) {
        }
        if (lista != null) {
            listatemp = lista;
        }
        String id_prod = request.getParameter("id_prod");
        String id_insumo = request.getParameter("id_insumo");
        String cantidad_insumo = request.getParameter("cantidad_insumo");
        Producto insumo = LogicProducto.getInstance().buscarProductoID(Integer.parseInt(id_insumo));
        ListaReglaProduccion reglas = new ListaReglaProduccion();
        reglas.setId_producto(Integer.parseInt(id_prod));
        reglas.setDescripcion(insumo.getDescripcion());
        reglas.setId_insumo(insumo.getId_producto());
        reglas.setMarca(insumo.getMarca());
        reglas.setPresentacion(insumo.getPresentacion());
        reglas.setMedida(insumo.getMedida());
        reglas.setCantidad(Double.parseDouble(cantidad_insumo));
        boolean validacion = false;
        String msg = null;
        String respuesta = null;
        if (listatemp != null) {
            if (listatemp.size() > 0) {
                for (ListaReglaProduccion listaRegla : listatemp) {
                    if (listaRegla.getId_producto() == reglas.getId_producto()) {
                        validacion = true;
                    } else {
                        validacion = false;
                        msg = "ERROR%HA CAMBIADO PRODUCTO FINAL DURANTE LA TRANSACCION";
                        HtmlUtil.getInstance().escrituraHTML(response, msg);
                        return;
                    }
                    if (listaRegla.getId_insumo() == reglas.getId_insumo()) {
                        validacion = false;
                        msg = "ERROR%EL INSUMO YA SE ENCUENTRA REGISTRADO";
                        HtmlUtil.getInstance().escrituraHTML(response, msg);
                        return;
                    } else {
                        validacion = true;
                    }
                }
            }

        }
        listatemp.add(reglas);
        session.setAttribute("listainsumos", listatemp);
        respuesta = LogicTablaReglaProduccion.getInstance().construirGrillaListaReglasProduccion(listatemp);
        HtmlUtil.getInstance().escrituraHTML(response, "OK%" + respuesta);

    }

    private void EliminarInsumoAJAX(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String item = request.getParameter("item");
        List<ListaReglaProduccion> listatemp = new ArrayList<ListaReglaProduccion>();
        List<ListaReglaProduccion> lista = new ArrayList<ListaReglaProduccion>();
        try {
            lista = (List<ListaReglaProduccion>) session.getAttribute("listainsumos");
        } catch (Exception e) {
        }
        if (lista != null) {
            listatemp = lista;

        }
        listatemp.remove(Integer.parseInt(item));
        session.setAttribute("listainsumos", listatemp);
        String respuesta = LogicTablaReglaProduccion.getInstance().construirGrillaListaReglasProduccion(listatemp);
        HtmlUtil.getInstance().escrituraHTML(response, "OK%" + respuesta);

    }

    private void RegistrarRegla(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("control.ServProduccion.RegistrarRegla()");
        String id_producto_formulario = request.getParameter("id_producto");
        int id_producto_frm = Integer.parseInt(id_producto_formulario);
        HttpSession session = request.getSession();
        List<ListaReglaProduccion> listatemp = new ArrayList<ListaReglaProduccion>();
        List<ListaReglaProduccion> lista = new ArrayList<ListaReglaProduccion>();
        try {
            lista = (List<ListaReglaProduccion>) session.getAttribute("listainsumos");
        } catch (Exception e) {
        }
        if (lista != null) {
            listatemp = lista;
        }
        //declaro variables locales;
        String id_insumo = "";
        String cantidad = "";
        int contador = 0;
        //verificar regla
        String msg = null;
        ListaReglaProduccion regla = null;
        if (listatemp.size() > 0) {
            boolean ReglaOK = LogicProduccion.getInstance().verificarRegla(id_producto_frm);
            if (ReglaOK == false) {
                msg = "ERROR%" + "EL PRODUCTO A FABRICAR YA TIENE  UNA REGLA ACTIVA";
                HtmlUtil.getInstance().escrituraHTML(response, msg);
                return;
            }
            for (ListaReglaProduccion listaInsumos : listatemp) {
                if (listaInsumos.getId_producto() == id_producto_frm) {

                } else {
                    msg = "ERROR%" + "HA CAMBIADO PRODUCTO A FABRICAR DURANTE LA TRANSACCION";
                    HtmlUtil.getInstance().escrituraHTML(response, msg);
                    return;
                }

                id_insumo = id_insumo + String.valueOf(listaInsumos.getId_insumo() + "@");
                cantidad = cantidad + String.valueOf(listaInsumos.getCantidad() + "@");
                contador = contador + 1;
            }
            Usuario usuario = usuario = new Usuario();
            usuario = (Usuario) session.getAttribute("usuario");
            int usuario_mod = usuario.getIdUsuario();
            regla = new ListaReglaProduccion();
            regla.setNro_insumos(contador);
            regla.setId_producto(id_producto_frm);
            regla.setId_usuario(usuario_mod);
            regla.setCadena_cantidad(cantidad);
            regla.setCandena_Id_insumo(id_insumo);
        } else {
            msg = "ERROR%" + "LISTA DE INSUMOS VACIA";
            HtmlUtil.getInstance().escrituraHTML(response, msg);
            return;
        }
        String respuesta = LogicProduccion.getInstance().GrabarRegla(regla);
        if (respuesta.equals("OK")) {
            msg = "OK%" + "REGLA REGISTRADA EXITOSAMENTE";
        } else if (respuesta.equals("NOK")) {
            msg = "ERROR%" + "NO SE PUDO REGISTRAR REGLA";
        }
        HtmlUtil.getInstance().escrituraHTML(response, msg);
    }

    private void EliminarRegla(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id_producto = request.getParameter("id_producto");
        String id_regla = request.getParameter("id_regla");
        String respuesta = null;
        respuesta = LogicProduccion.getInstance().eliminarRegla(id_regla, id_producto);
        HtmlUtil.getInstance().escrituraHTML(response, respuesta);
    }

    private void ListarInsumosxReglaxProd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id_producto = request.getParameter("id_producto");
        String id_regla = request.getParameter("id_regla");
        String respuesta = null;
        respuesta = LogicProduccion.getInstance().MostrarInsumos(id_regla, id_producto);
        HtmlUtil.getInstance().escrituraHTML(response, respuesta);
    }

    private void AñadirProduccion(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        List<ListaProduccion> listatemp = new ArrayList<ListaProduccion>();
        List<ListaProduccion> lista = new ArrayList<ListaProduccion>();
        try {
            lista = (List<ListaProduccion>) session.getAttribute("listaproduccion");
        } catch (Exception e) {
        }
        if (lista != null) {
            listatemp = lista;
        }
        String id_regla = request.getParameter("id_regla");
        String cantidad = request.getParameter("cantidad");
        ListaProduccion productofinal = LogicProduccion.getInstance().buscarRegla(Integer.parseInt(id_regla));
        productofinal.setCantidad_produccion(Double.parseDouble(cantidad));
        boolean validacion = false;
        String msg = null;
        String respuesta = null;
        if (listatemp != null) {
            if (listatemp.size() > 0) {
                for (ListaProduccion listaProduccion : listatemp) {
                    if (listaProduccion.getId_regla() == productofinal.getId_regla()) {
                        validacion = false;
                        msg = "ERROR%EL EL PRODUCTO A FABRICAR YA SE ENCUENTRA REGISTRADO";
                        HtmlUtil.getInstance().escrituraHTML(response, msg);
                        return;
                    } else {
                        validacion = true;
                    }
                }
            }

        }
        listatemp.add(productofinal);
        session.setAttribute("listaproduccion", listatemp);
        respuesta = LogicTablaReglaProduccion.getInstance().construirGrillaListaProduccion(listatemp);
        HtmlUtil.getInstance().escrituraHTML(response, "OK%" + respuesta);
    }

    private void EliminarProductofinal(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String item = request.getParameter("item");
        List<ListaProduccion> listatemp = new ArrayList<ListaProduccion>();
        List<ListaProduccion> lista = new ArrayList<ListaProduccion>();
        try {
            lista = (List<ListaProduccion>) session.getAttribute("listaproduccion");
        } catch (Exception e) {
        }
        if (lista != null) {
            listatemp = lista;

        }
        listatemp.remove(Integer.parseInt(item));
        session.setAttribute("listaproduccion", listatemp);
        String respuesta = LogicTablaReglaProduccion.getInstance().construirGrillaListaProduccion(listatemp);
        HtmlUtil.getInstance().escrituraHTML(response, "OK%" + respuesta);

    }

    private void RegitrarProduccion(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String doc = request.getParameter("documento");
        String numero = request.getParameter("numero");
        String fecha = request.getParameter("fecha");

        List<ListaProduccion> listatemp = new ArrayList<ListaProduccion>();
        List<ListaProduccion> lista = new ArrayList<ListaProduccion>();
        try {
            lista = (List<ListaProduccion>) session.getAttribute("listaproduccion");
        } catch (Exception e) {
        }
        if (lista != null) {
            listatemp = lista;
        }
        //declaro variables locales;
        String id_regla = "";
        String id_producto = "";
        String cantidad = "";
        int contador = 0;
        String cant_insumos = "";
        //verificar regla
        String msg = null;
        ListaReglaProduccion produccion = null;
        if (listatemp.size() > 0) {

            for (ListaProduccion listaProduccion : listatemp) {
                id_regla = id_regla + String.valueOf(listaProduccion.getId_regla() + "@");
                id_producto = id_producto + String.valueOf(listaProduccion.getId_producto() + "@");
                cantidad = cantidad + String.valueOf(listaProduccion.getCantidad_produccion() + "@");
                cant_insumos = cant_insumos + String.valueOf(listaProduccion.getNro_insumos() + "@");
                contador = contador + 1;
            }
            Usuario usuario = new Usuario();
            usuario = (Usuario) session.getAttribute("usuario");
            int usuario_mod = usuario.getIdUsuario();
            produccion = new ListaReglaProduccion();
            produccion.setCadena_nro_insumos(cant_insumos);
            produccion.setCant_regla(contador);
            produccion.setId_usuario(usuario_mod);
            produccion.setCadena_cantidad(cantidad);
            produccion.setCandena_Id_Producto(id_producto);
            produccion.setCandena_Id_Regla(id_regla);
            produccion.setDocumento(doc);
            produccion.setFecha(fecha);
            produccion.setNumero_doc(numero);
        } else {
            msg = "ERROR%" + "LISTA DE PRODCCION VACIA";
            HtmlUtil.getInstance().escrituraHTML(response, msg);
            return;
        }
        msg = LogicProduccion.getInstance().GrabarProduccion(produccion);
        HtmlUtil.getInstance().escrituraHTML(response, msg);

    }

    private void verificarProduccion(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        List<ListaProduccion> listatemp = new ArrayList<ListaProduccion>();
        List<ListaProduccion> lista = new ArrayList<ListaProduccion>();
        try {
            lista = (List<ListaProduccion>) session.getAttribute("listaproduccion");
        } catch (Exception e) {
        }
        if (lista != null) {
            listatemp = lista;
        }
        //declaro variables locales;
        String id_regla = "";
        String id_producto = "";
        String cantidad = "";
        int contador = 0;
        String cant_insumos = "";
        //verificar regla
        String msg = null;
        int validacion=999;
        if (listatemp.size() > 0) {

            for (ListaProduccion listaProduccion : listatemp) {
                id_regla = id_regla + String.valueOf(listaProduccion.getId_regla() + "@");
                id_producto = id_producto + String.valueOf(listaProduccion.getId_producto() + "@");
                cantidad = cantidad + String.valueOf(listaProduccion.getCantidad_produccion() + "@");
                cant_insumos = cant_insumos + String.valueOf(listaProduccion.getNro_insumos() + "@");
                contador = contador + 1;
            }
        } else {
            msg = "ERROR%" + "LISTA DE PRODCCION VACIA";
            HtmlUtil.getInstance().escrituraHTML(response, msg);
            return;
        }
        validacion = LogicProduccion.getInstance().VerificarProduccion(contador,id_regla,id_producto,cantidad,cant_insumos);
        if (validacion==0) {
            msg= "OK%VERIFICADO";
        }else{
            msg= "NOK%EXISTEN "+validacion+" insumos insuficientes";
        }
        HtmlUtil.getInstance().escrituraHTML(response, msg);
    }

    private void verificarErrorProduccion(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        List<ListaProduccion> listatemp = new ArrayList<ListaProduccion>();
        List<ListaProduccion> lista = new ArrayList<ListaProduccion>();
        try {
            lista = (List<ListaProduccion>) session.getAttribute("listaproduccion");
        } catch (Exception e) {
        }
        if (lista != null) {
            listatemp = lista;
        }
        //declaro variables locales;
        String id_regla = "";
        String id_producto = "";
        String cantidad = "";
        int contador = 0;
        String cant_insumos = "";
        //verificar regla
        String msg = null;
String validacion=null;
        if (listatemp.size() > 0) {

            for (ListaProduccion listaProduccion : listatemp) {
                id_regla = id_regla + String.valueOf(listaProduccion.getId_regla() + "@");
                id_producto = id_producto + String.valueOf(listaProduccion.getId_producto() + "@");
                cantidad = cantidad + String.valueOf(listaProduccion.getCantidad_produccion() + "@");
                cant_insumos = cant_insumos + String.valueOf(listaProduccion.getNro_insumos() + "@");
                contador = contador + 1;
            }
        }
        validacion = LogicProduccion.getInstance().VerificarErrorProduccion(contador,id_regla,id_producto,cantidad,cant_insumos);
        
        HtmlUtil.getInstance().escrituraHTML(response, validacion);
    }

    private void EliminarProduccionAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String respuesta=null;
        respuesta=LogicProduccion.getInstance().eliminarProduccion(id);
         HtmlUtil.getInstance().escrituraHTML(response, respuesta); 
    }
}
