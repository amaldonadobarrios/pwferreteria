/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import util.Util;
import util.jdbc.ConectaDB;

/**
 *
 * @author 31424836
 */
public class ServReporte extends HttpServlet {

    Util uti = new Util();
    Connection cn = null;
    ConectaDB db = new ConectaDB();
//Reporte Itext
    private Font fuenteBlod = new Font(Font.FontFamily.COURIER, 10, Font.BOLD);
    private Font fuenteNormal = new Font(Font.FontFamily.COURIER, 8, Font.NORMAL);
    private Font fuenteItalic = new Font(Font.FontFamily.COURIER, 8, Font.ITALIC);

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
            throws ServletException, IOException, SQLException, JRException, ClassNotFoundException {
        String evento = request.getParameter("evento");
        System.out.println("control.ServReporte.processRequest()" + evento);
          try {
            if (evento.equals("venta")) {
            String id = request.getParameter("num").trim();
            String estado = "";
            estado = request.getParameter("estado");
            System.out.println("control.ServReporte.processRequest()" + id);
            verreporte(request, response, id, estado);
        } else if (evento.equals("compra")) {
            String id = request.getParameter("num").trim();
            String estado = "";
            String id_compra = "";
            id_compra = request.getParameter("id_compra");
            estado = request.getParameter("estado");
            System.out.println("control.ServReporte.processRequest()" + id);
            verreportecOMPRA(request, response, id, estado, id_compra);

        } else if (evento.equals("produccion")) {
            verreporteProduccion(request, response);

        } 
        } catch (Exception e) {
         generarpdf(response);   
        }
     
       

    }

    private void verreporte(HttpServletRequest request, HttpServletResponse response, String respuesta, String estado) throws SQLException, IOException, JRException, ClassNotFoundException {

        try {
            cn = db.getConnection();
            String jrxmlfile = getServletContext().getRealPath("/jrxml/ComprobanteVentaReporte.jrxml");
            String rutalogo = getServletContext().getRealPath("/jrxml/img/logo.png");
            Map parameters = new HashMap();
            String sSubCadenacomprobante = respuesta.substring(0, 3);
            String TipoComprob = null;
            String tipodoc = null;
            if (sSubCadenacomprobante.equals("BOL")) {
                TipoComprob = "BOLETA DE VENTA";
                tipodoc = "DNI :";
            } else if (sSubCadenacomprobante.equals("FAC")) {
                TipoComprob = "FACTURA";
                tipodoc = "RUC :";
            } else if (sSubCadenacomprobante.equals("GDV")) {
                TipoComprob = "GUÍA DE VENTA";
                tipodoc = "DNI/RUC:";
            }
            if (estado.equals("VENDIDO")) {
                parameters.put("in_estado", "VENDIDO");
            } else if (estado.equals("ELIMINADO")) {
                parameters.put("in_estado", "ELIMINADO");
            }
            parameters.put("id", respuesta.trim());
            parameters.put("tipo_documento", tipodoc);
            parameters.put("tipo_comprobante", TipoComprob);
            parameters.put("logo", rutalogo);
            InputStream input = new FileInputStream(new File(jrxmlfile));
            JasperReport jasperReport = JasperCompileManager.compileReport(input);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn);
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (SQLException ex) {
            generarpdf(response);
            System.out.println("className.methodName()" + ex);
        } finally {
            cn.close();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServReporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ServReporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServReporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ServReporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void verreportecOMPRA(HttpServletRequest request, HttpServletResponse response, String id, String estado, String id_compra) throws ClassNotFoundException, FileNotFoundException, JRException, IOException, SQLException {
        int idcompra = Integer.parseInt(id_compra);
        try {
            cn = db.getConnection();
            String jrxmlfile = getServletContext().getRealPath("/jrxml/ComprobanteCompraReporte.jrxml");
            Map parameters = new HashMap();
            String sSubCadenacomprobante = id.substring(0, 3);
            String TipoComprob = null;
            String tipodoc = null;
            if (sSubCadenacomprobante.equals("BOL")) {
                TipoComprob = "BOLETA DE VENTA";
                tipodoc = "DNI :";
            } else if (sSubCadenacomprobante.equals("FAC")) {
                TipoComprob = "FACTURA";
                tipodoc = "RUC :";
            } else if (sSubCadenacomprobante.equals("GDV")) {
                TipoComprob = "GUÍA DE VENTA";
                tipodoc = "DNI/RUC:";
            }
            if (estado.equals("COMPRADO")) {
                parameters.put("in_estado", "COMPRADO");
            } else if (estado.equals("ELIMINADO")) {
                parameters.put("in_estado", "ELIMINADO");
            }
            parameters.put("id", id.trim());
            parameters.put("id_compra", idcompra);
            parameters.put("tipo_documento", tipodoc);
            parameters.put("tipo_comprobante", TipoComprob);
            InputStream input = new FileInputStream(new File(jrxmlfile));
            JasperReport jasperReport = JasperCompileManager.compileReport(input);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn);
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (SQLException ex) {
             generarpdf(response);
            System.out.println("className.methodName()" + ex);
        } finally {
            cn.close();
        }
    }

    private void verreporteProduccion(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, JRException {
        String estado = request.getParameter("estado");
        String id = request.getParameter("id_produccion");
        int id_produccion = Integer.parseInt(id);
        int state = Integer.parseInt(estado);
        try {
            cn = db.getConnection();
            String jrxmlfile = getServletContext().getRealPath("/jrxml/ReporteProduccion.jrxml");
            String jrxmlfile2 = getServletContext().getRealPath("/jrxml/ReporteProduccion_descuentos.jasper");
            Map parameters = new HashMap();
            parameters.put("id", id_produccion);
            parameters.put("estado", state);
            if (estado.equals("1")) {
                parameters.put("in_estado", "ACTIVO");
            } else if (estado.equals("0")) {
                parameters.put("in_estado", "ELIMINADO");
            }
            InputStream input = new FileInputStream(new File(jrxmlfile));
            JasperReport jasperReport = JasperCompileManager.compileReport(input);
            parameters.put("SUBREPORT_DIR", jrxmlfile2);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cn);
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (SQLException ex) {
            generarpdf(response);
            System.out.println("className.methodName()" + ex);
        } finally {
            cn.close();
        }
    }

    public void generarpdf(HttpServletResponse response) {
        String rutaerror = getServletContext().getRealPath("/assets/images/errorsys.png");
        try {
            Document document = new Document(PageSize.A7, 36, 36, 10, 10);
            // PdfWriter pw = PdfWriter.getInstance(document, new FileOutputStream(salida));
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, buffer);
            document.open();
            document.add(getHeader("SISTEMA COMERCIAL INVENTARIO Y PRODUCCION   NOVACEL"));
            Image imagen = Image.getInstance(rutaerror);
            imagen.scaleAbsolute(180, 180);
            imagen.setAlignment(Element.ALIGN_CENTER);
            document.add(imagen);
            document.add(getInfo("ERROR INTERNO"));
            document.add(getFooter("Ticsolutions.org"));
            document.close();
            DataOutputStream dataOutput = new DataOutputStream(response.getOutputStream());
            byte[] bytes = buffer.toByteArray();
            response.setContentLength(bytes.length);
            for (int i = 0; i < bytes.length; i++) {
                dataOutput.writeByte(bytes[i]);
            }
            dataOutput.flush();
            dataOutput.close();
            return;
        } catch (Exception e) {
        }

    }

    private Paragraph getHeader(String texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(fuenteBlod);
        p.add(c);
        return p;
    }

    private Paragraph getInfo(String texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
        c.append(texto);
        c.setFont(fuenteNormal);
        p.add(c);
        return p;
    }

    private Paragraph getFooter(String texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_RIGHT);
        c.append(texto);
        c.setFont(fuenteNormal);
        p.add(c);
        return p;
    }
}
