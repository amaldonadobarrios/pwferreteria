/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.mi.diredu.util.DirTexto;
import java.io.IOException;
import java.io.*;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.LogicProducto;
import model.dto.Producto;
import model.dto.Usuario;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import util.HtmlUtil;

/**
 *
 * @author Alexander
 */
public class ServProducto extends HttpServlet {

    private byte[] bin = null;
    private String encodedfile = null;
    private byte[] bFile = null;
    private String name = null;

    private RequestDispatcher rd = null;
    private int cont = 0;
    private String evento = null;
    private String mensaje = null;

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
            throws ServletException, IOException, FileUploadException, Exception {
        System.out.println("control.ServProducto.processRequest()" + "LLEGE AL SERVLET PRODUCTO");
        if (ServletFileUpload.isMultipartContent(request)) {
            System.out.println("control.ServProducto.processRequest()" + "ES MULTIPART");
            String value = "";
            String dato = "";
            String datos[] = new String[9];
            int cont = 0;
            Producto pro = null;
            String producto_insumo = null;
            List<FileItem> multiparts = new ServletFileUpload(
                    new DiskFileItemFactory()).parseRequest(request);
            if (multiparts != null) {
                pro = new Producto();

                for (FileItem item : multiparts) {

                    System.out.println("control.ServProducto.processRequest()" + multiparts.size());
                    if (!item.isFormField()) {
                        try {
                            InputStream fileContent = item.getInputStream();
                            bFile = IOUtils.toByteArray(fileContent);
                            String type = item.getContentType();
                            pro.setFoto(bFile);
                            pro.setType(type);

                        } catch (Exception e) {
                            System.out.println("control.ServProducto.processRequest()" + "ERROR : " + e);
                            pro.setFoto(null);
                            pro.setType(null);
                        } finally {

                        }

                    } else {
                        datos[cont] = item.getString();
                        value = value + item.getFieldName() + "#";
                        System.out.println("control.ServProducto.processRequest()" + value + ": " + datos[cont]);
                        cont++;
                    }
                }
                evento = datos[0];
                if (evento.equals("actualizarProducto")) {
                    pro.setId_producto(Integer.parseInt(datos[1]));
                }
                pro.setDescripcion(DirTexto.getInstance().cambiarFormatoUTF8(datos[2]).toUpperCase());
                pro.setMarca(DirTexto.getInstance().cambiarFormatoUTF8(datos[3]).toUpperCase());
                pro.setPresentacion(DirTexto.getInstance().cambiarFormatoUTF8(datos[4]).toUpperCase());
                pro.setMedida(datos[5]);

                if (datos[6] != null) {
                    producto_insumo = datos[6];
                    if (datos[7] != null) {
                        producto_insumo = datos[6] + " E " + datos[7];
                    }
                } else if (datos[7] != null) {
                    producto_insumo = datos[7];
                }

                if (producto_insumo != null) {
                    pro.setProd_insu(producto_insumo.toUpperCase());
                }

            }
            System.out.println("control.ServProducto.processRequest() " + pro.toString());
            if (evento.equals("registrarProducto")) {
                HttpSession session = request.getSession();
                Usuario usuario = usuario = new Usuario();
                usuario = (Usuario) session.getAttribute("usuario");
                int usuario_reg = usuario.getIdUsuario();
                pro.setUsuario_reg(usuario_reg);
                String respuesta = LogicProducto.getInstance().registrarProducto(pro);
                System.out.println("control.ServProducto.processRequest()" + "RESPUESTA :" + respuesta);
                if (respuesta == "OK") {
                    mensaje = "Producto registrado exitosamente";
                } else {
                    mensaje = "Error, no se registró el Producto";
                }
                request.setAttribute("lista", LogicProducto.getInstance().getProductos());
                request.setAttribute("mensaje", mensaje);
                request.setAttribute("EVENTO", "REGISTRAR");
                request.setAttribute("body", "registro_producto");
                forwar("template.jsp", request, response);
            } else if (evento.equals("actualizarProducto")) {
                HttpSession session = request.getSession();
                Usuario usuario = usuario = new Usuario();
                usuario = (Usuario) session.getAttribute("usuario");
                int usuario_mod = usuario.getIdUsuario();
                pro.setUsuario_mod(usuario_mod);
                String respuesta = LogicProducto.getInstance().actualizarProducto(pro);
                System.out.println("control.ServProducto.processRequest()" + "RESPUESTA :" + respuesta);
                if (respuesta == "OK") {
                    mensaje = "Producto Actualizado exitosamente";
                } else {
                    mensaje = "Error, no se actualizó el Producto";
                }
                request.setAttribute("lista", LogicProducto.getInstance().getProductos());
                request.setAttribute("mensaje", mensaje);
                request.setAttribute("EVENTO", "REGISTRAR");
                request.setAttribute("body", "registro_producto");
                forwar("template.jsp", request, response);
            }

        } else {
            System.out.println("control.ServProducto.processRequest()" + "NO ES MULTIPART");
            evento = request.getParameter("evento");
            if (evento.equals("IrformActualizarProducto")) {
                String id = request.getParameter("idproducto").trim();
                Producto pro = new Producto();
                pro = LogicProducto.getInstance().buscarProductoID(Integer.parseInt(id));
                String encod = null;
                try {
                    Base64.Encoder code = Base64.getEncoder();
                    encod = code.encodeToString(pro.getFoto());
                } catch (Exception e) {
                    encod = null;
                }

                System.out.println("control.ServProducto.processRequest()" + "  base 64 : " + encod);
                request.setAttribute("img64", encod);
                request.setAttribute("lista", LogicProducto.getInstance().getProductos());
                request.setAttribute("objpro", pro);
                request.setAttribute("EVENTO", "ACTUALIZAR");
                request.setAttribute("body", "registro_producto");
                forwar("template.jsp", request, response);

            } else if (evento.equals("registrarprecioventa")) {

                String id_producto = request.getParameter("id_producto");
                String pv1 = request.getParameter("pv1");
                String pv2 = request.getParameter("pv2");
                String pv3 = request.getParameter("pv3");
                HttpSession session = request.getSession();
                Usuario usuario = usuario = new Usuario();
                usuario = (Usuario) session.getAttribute("usuario");
                int usuario_mod = usuario.getIdUsuario();
                Producto pro = null;
                if (id_producto != null) {
                    pro = new Producto();
                    pro.setId_producto(Integer.parseInt(id_producto));
                    pro.setPv1(Double.parseDouble(pv1));
                    pro.setPv2(Double.parseDouble(pv2));
                    pro.setPv3(Double.parseDouble(pv3));
                    pro.setUsuario_mod(usuario_mod);
                }
                String respuesta = LogicProducto.getInstance().registrarPrecioVenta(pro);
                if (respuesta == "OK") {
                    mensaje = "Precio de Venta Actualizado exitosamente";
                } else {
                    mensaje = "Error, no se actualizó el Precio de Venta";
                }
                session.setAttribute("comboprod", LogicProducto.getInstance().getProductos());
                request.setAttribute("mensaje", mensaje);
                request.setAttribute("body", "registro_precio_venta");
                forwar("template.jsp", request, response);
            } else if (evento.equals("IrformActualizarPrecioVenta")) {
                HttpSession session = request.getSession();
                String id_producto = request.getParameter("idproducto");
                Producto pro = new Producto();
                pro = LogicProducto.getInstance().buscarProductoID(Integer.parseInt(id_producto));
                String encod = null;
                try {
                    Base64.Encoder code = Base64.getEncoder();
                    encod = code.encodeToString(pro.getFoto());
                } catch (Exception e) {
                    encod = null;
                }
                session.setAttribute("comboprod", LogicProducto.getInstance().getProductos());
                request.setAttribute("objProd", pro);
                request.setAttribute("img64", encod);
                request.setAttribute("body", "registro_precio_venta");
                forwar("template.jsp", request, response);
            } else if (evento.equals("IrformActualizarPrecioVentaAjax")) {
                String id = request.getParameter("idproducto").trim();
                Producto pro = new Producto();
                pro = LogicProducto.getInstance().buscarProductoID(Integer.parseInt(id));
                String encod = null;
                try {
                    Base64.Encoder code = Base64.getEncoder();
                    encod = code.encodeToString(pro.getFoto());
                } catch (Exception e) {
                    encod = null;
                }
                String respuesta=null;
                respuesta=encod+"%"+pro.getPv1()+"%"+pro.getPv2()+"%"+pro.getPv3();
                HtmlUtil.getInstance().escrituraHTML(response, respuesta);
            }else if (evento.equals("GetProductoDetallesAjax")) {
                String id = request.getParameter("idproducto").trim();
                Producto pro = new Producto();
                pro = LogicProducto.getInstance().buscarProductoID(Integer.parseInt(id));
                String encod = null;
                try {
                    Base64.Encoder code = Base64.getEncoder();
                    encod = code.encodeToString(pro.getFoto());
                } catch (Exception e) {
                    encod = null;
                }
                String respuesta=null;
                respuesta=encod+"%"+pro.getPv1()+"%"+pro.getPv2()+"%"+pro.getPv3()+"%"+pro.getExistencia();
                HtmlUtil.getInstance().escrituraHTML(response, respuesta); 
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
        try {
            processRequest(request, response);
        } catch (FileUploadException ex) {
            Logger.getLogger(ServProducto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ServProducto.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (FileUploadException ex) {
            Logger.getLogger(ServProducto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ServProducto.class.getName()).log(Level.SEVERE, null, ex);
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

    private byte[] readBytesFromFile(String filePath) {
        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;
        try {

            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];

            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return bytesArray;
    }

    private void forwar(String templatejsp, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(templatejsp).forward(request, response);
    }

}
