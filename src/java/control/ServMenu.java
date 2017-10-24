/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.LogicCliente;
import logica.LogicCompra;
import logica.LogicGanancia;
import logica.LogicInventario;
import logica.LogicPerfil;
import logica.LogicProduccion;
import logica.LogicProducto;
import logica.LogicProveedor;
import logica.LogicUsuario;
import logica.LogicVenta;
import model.dto.ComprobanteCompra;
import model.dto.ComprobanteVenta;
import model.dto.Ganancia;
import model.dto.Usuario;
import util.GraficoJFChart;

/**
 *
 * @author FARID
 */
public class ServMenu extends HttpServletConf {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.confHeader(request, response);

        int respuesta = this.validaSesion(request, response);

        if (respuesta == 1) {
            try {
                String action = request.getParameter("action") != null ? request.getParameter("action") : "";
                HttpSession session = request.getSession();
                session.removeAttribute("comboprod");
                session.removeAttribute("listaventa");
                session.removeAttribute("listacompra");
                session.removeAttribute("listainsumos");
                session.removeAttribute("listaproduccion");
                
                switch (action) {

                    case "pagehome":
                        this.pagehome(request, response);
                        break;

                    case "pageRegistroProductos":
                        this.pageRegistroProductos(request, response);
                        break;

                    case "pageRegistroCliente":
                        this.pageRegistroCliente(request, response);
                        break;

                    case "pageRegistroProveedor":

                        this.pageRegistroProveedor(request, response);
                        break;

                    case "pageRegistroUsuario":

                        this.pageRegistroUsuario(request, response);
                        break;

                    case "pageRegistroReglasProduccion":
                        this.pageRegistroReglasProduccion(request, response);
                        break;

                    case "pageRegistroPrecioVenta":
                        this.pageRegistroPrecioVenta(request, response);
                        break;

                    case "pageRegistroVenta":
                        this.pageRegistroVenta(request, response);
                        break;

                    case "pageConsultarVenta":
                        this.pageConsultarVenta(request, response);
                        break;

                    case "pageRegistroCompra":
                        this.pageRegistroCompra(request, response);
                        break;

                    case "pageConsultarCompra":
                        this.pageConsultarCompra(request, response);
                        break;

                    case "pageConsultarInventario":
                        this.pageConsultarInventario(request, response);
                        break;
                    case "pageConsultarInventarioProductos":
                        this.pageConsultarInventarioProductos(request, response);
                        break;
                    case "pageConsultarInventarioInsumos":
                        this.pageConsultarInventarioInsumos(request, response);
                        break;
                    case "pageReporteVentas":
                        this.pageReporteVentas(request, response);
                        break;
                    case "pageReporteCompras":
                        this.pageReporteCompras(request, response);
                        break;
                    case "pageReporteGanancias":
                        this.pageReporteGanancias(request, response);
                        break;
                    case "pagRegistroProduccion":
                        this.pagRegistroProduccion(request, response);
                        break;
                    case "pageConsultarProduccion":
                        this.pageConsultarProduccion(request,response);
                        break;
                    case "pageSalir":
                        this.pageSalir(request, response);
                        break;

                }
            } catch (Exception ex) {
                Logger.getLogger(ServMenu.class.getName()).log(Level.SEVERE, null, ex);
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

    private void pageSalir(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        session.removeAttribute("ID");
        session.removeAttribute("usuario");
        session.invalidate();
        redirect("login.jsp", response);
    }

    private void pageRegistroProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        request.setAttribute("lista", LogicProducto.getInstance().getProductos());
        request.setAttribute("EVENTO", "REGISTRAR");
        request.setAttribute("body", "registro_producto");
        forwar("template.jsp", request, response);
    }

    private void pageRegistroCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        request.setAttribute("lista", LogicCliente.getInstance().listarCliente());
        request.setAttribute("EVENTO", "REGISTRAR");
        request.setAttribute("body", "registro_cliente");
        forwar("template.jsp", request, response);
    }

    private void pageRegistroUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        LogicPerfil logiper = new LogicPerfil();
        LogicUsuario usu = new LogicUsuario();
        List<Usuario> lista;
        lista = new ArrayList<Usuario>();
        lista = usu.listarUsuarios();
        request.setAttribute("lista_perfil", logiper.listarPerfiles());
        request.setAttribute("lista", lista);
        request.setAttribute("EVENTO", "REGISTRAR");
        request.setAttribute("body", "registro_usuario");
        forwar("template.jsp", request, response);
    }

    private void pageRegistroProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        request.setAttribute("lista", LogicProveedor.getInstance().listarProveedor());
        request.setAttribute("EVENTO", "REGISTRAR");
        request.setAttribute("body", "registro_proveedor");
        forwar("template.jsp", request, response);
    }

    private int validaSesion(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        int respuesta = 1;

        HttpSession session = req.getSession();

        if (session != null) {

            Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");
            String ID = (String) session.getAttribute("ID");

            if (ID == null || usuarioSesion == null || !ID.equals(session.getId())) {
                respuesta = 0;
                session.invalidate();
                req.setAttribute("msg", "Sesion Expirada");
                forwar("login.jsp", req, resp);

            }

        } else {
            respuesta = 0;
            session.invalidate();
            req.setAttribute("msg", "Sesion Expirada");
            forwar("login.jsp", req, resp);
        }

        return respuesta;

    }

    private void pageRegistroReglasProduccion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        request.setAttribute("lista_reglasActivas", LogicProduccion.getInstance().listarReglasActivas());
        request.setAttribute("lista_insumos", LogicProduccion.getInstance().listarInsumos());
        request.setAttribute("body", "registro_reglas_produccion");
        forwar("template.jsp", request, response);
    }

    private void pageRegistroPrecioVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        HttpSession session = request.getSession();
        session.setAttribute("comboprod", LogicProducto.getInstance().getProductos());
        request.setAttribute("body", "registro_precio_venta");
        forwar("template.jsp", request, response);
    }

    private void pageRegistroVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        request.setAttribute("combopro", LogicVenta.getInstance().listarproductoscomboventa());
        request.setAttribute("body", "registro_venta");
        forwar("template.jsp", request, response);
    }

    private void pageConsultarVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
       String fecha = null;
        fecha = request.getParameter("fecha");
        if (fecha == null) {
            request.setAttribute("listaVenta", LogicVenta.getInstance().listarventa200());
        } else {
            request.setAttribute("listaVenta", LogicVenta.getInstance().listarventaxFecha(fecha));
        }
        request.setAttribute("body", "consultar_venta");
        forwar("template.jsp", request, response);
    }

    private void pageRegistroCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
         request.setAttribute("combopro", LogicCompra.getInstance().listarproductoscombocompra());
        request.setAttribute("body", "registro_compra");
        forwar("template.jsp", request, response);
    }

    private void pageConsultarCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
         String fecha = null;
        fecha = request.getParameter("fecha");
        if (fecha == null) {
            request.setAttribute("listaCompra", LogicCompra.getInstance().listarcompra200());
        } else {
            request.setAttribute("listaCompra", LogicCompra.getInstance().listarcompraxFecha(fecha));
        }
        request.setAttribute("body", "consultar_compra");
        forwar("template.jsp", request, response);
    }

    private void pageConsultarInventario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        request.setAttribute("listainventario", LogicInventario.getInstance().ListarInventario());
        request.setAttribute("body", "consultar_inventario");
        forwar("template.jsp", request, response);
    }

    private void pageConsultarInventarioProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("body", "consultar_inventario_producto");
        forwar("template.jsp", request, response);
    }

    private void pageConsultarInventarioInsumos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("body", "consultar_inventario_insumo");
        forwar("template.jsp", request, response);
    }

    private void pageReporteVentas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        String fechaini = null;
        String fechafin = null;
        fechaini = request.getParameter("fecha1");
        fechafin = request.getParameter("fecha2");
        List<ComprobanteVenta> listaventa;
        List<ComprobanteVenta> listagrafico;
        if (fechaini != null && fechafin != null) {
            SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");
            SimpleDateFormat formateador = new SimpleDateFormat("YYYY-MM-dd");
            Date date1 = parseador.parse(fechaini);
            Date date2 = parseador.parse(fechafin);
            String fecha1 = formateador.format(date1);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date2);
            cal.add(Calendar.DATE, 1);
            String fecha2 = formateador.format(cal.getTime());
            System.out.println("control.ServMenu.pageReporteVentas()FECHAS: " + fecha1 + " FECHA 2 :" + fecha2);
            listaventa = LogicVenta.getInstance().listarventaxRango(fecha1, fecha2);
            listagrafico = LogicVenta.getInstance().PNGReporteVenta(fecha1, fecha2);
            String b64 = GraficoJFChart.getInstance().B64graficoLineaxmesxyear(listagrafico);
            if (listaventa != null) {
                if (listaventa.size() > 0) {
                    request.setAttribute("listaventarango", listaventa);
                    request.setAttribute("grafico", b64);
                }
            }
        }
        request.setAttribute("body", "reporte_venta");
        forwar("template.jsp", request, response);
    }

    private void pageReporteCompras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, Exception {
         String fechaini = null;
        String fechafin = null;
        fechaini = request.getParameter("fecha1");
        fechafin = request.getParameter("fecha2");
        List<ComprobanteCompra> listacompra;
        List<ComprobanteCompra> listagrafico;
        if (fechaini != null && fechafin != null) {
            SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");
            SimpleDateFormat formateador = new SimpleDateFormat("YYYY-MM-dd");
            Date date1 = parseador.parse(fechaini);
            Date date2 = parseador.parse(fechafin);
            String fecha1 = formateador.format(date1);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date2);
            cal.add(Calendar.DATE, 1);
            String fecha2 = formateador.format(cal.getTime());
            System.out.println("control.ServMenu.pageReporteCompras()FECHAS: " + fecha1 + " FECHA 2 :" + fecha2);
            listacompra = LogicCompra.getInstance().listarcompraxRango(fecha1, fecha2);
            listagrafico = LogicCompra.getInstance().PNGReporteCompra(fecha1, fecha2);
            String b64 = GraficoJFChart.getInstance().B64graficoLineaxmesxyearCompra(listagrafico);
            if (listacompra != null) {
                if (listacompra.size() > 0) {
                    request.setAttribute("listacomprarango", listacompra);
                    request.setAttribute("grafico", b64);
                }
            }
        }
        request.setAttribute("body", "reporte_compra");
        forwar("template.jsp", request, response);
    }

    private void pageReporteGanancias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, Exception {
        String fechaini = null;
        String fechafin = null;
        fechaini = request.getParameter("fecha1");
        fechafin = request.getParameter("fecha2");
        List<Ganancia> listagananciamensual;
        List<Ganancia> listagananciaanual;
        if (fechaini != null && fechafin != null) {
            SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");
            SimpleDateFormat formateador = new SimpleDateFormat("YYYY-MM-dd");
            Date date1 = parseador.parse(fechaini);
            Date date2 = parseador.parse(fechafin);
            String fecha1 = formateador.format(date1);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date2);
            cal.add(Calendar.DATE, 1);
            String fecha2 = formateador.format(cal.getTime());
            System.out.println("control.ServMenu.pageReporteGanancia()FECHAS: " + fecha1 + " FECHA 2 :" + fecha2);
            listagananciaanual = LogicGanancia.getInstance().PNGReporteGananciaAnual(fecha1, fecha2);
            listagananciamensual=LogicGanancia.getInstance().PNGReporteGananciaMensual(fecha1, fecha2);
            String b64mensual = GraficoJFChart.getInstance().B64graficoBarraxmesxyearGanancia(listagananciamensual);
            String b64anual = GraficoJFChart.getInstance().B64graficoBarraxyearGanancia(listagananciaanual);
            if (listagananciamensual != null) {
                if (listagananciamensual.size() > 0) {
                    request.setAttribute("listagananciaMes", listagananciamensual);
                    request.setAttribute("grafico_mes", b64mensual);
                }
            }
             if (listagananciaanual != null) {
                if (listagananciaanual.size() > 0) {
                    request.setAttribute("listagananciaAño", listagananciaanual);
                    request.setAttribute("grafico_año", b64anual);
                }
            }
        }
        request.setAttribute("body", "reporte_ganancia");
        forwar("template.jsp", request, response);
    }

    private void pagRegistroProduccion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        request.setAttribute("comboreglas", LogicProduccion.getInstance().listarReglasActivas());
        request.setAttribute("body", "registro_produccion");
        forwar("template.jsp", request, response);
    }

    private void pagehome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("body", "home");
        forwar("template.jsp", request, response);
    }

    private void pageConsultarProduccion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
         String fecha = null;
        fecha = request.getParameter("fecha");
        if (fecha == null) {
            request.setAttribute("lstProduccion", LogicProduccion.getInstance().listarProduccion200());
        } else {
            request.setAttribute("lstProduccion", LogicProduccion.getInstance().listarProduccionxFecha(fecha));
        }
        request.setAttribute("body", "consultar_produccion");
        forwar("template.jsp", request, response);
    }

}
