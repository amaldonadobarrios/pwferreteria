/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.mi.diredu.util.DirTexto;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.LogicProveedor;
import model.dto.Proveedor;
import model.dto.Usuario;

/**
 *
 * @author Alexander
 */
public class ServProveedor extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
         int respuesta = this.validaSesion(request, response);

        if (respuesta == 1) {
            try {
        String evento = request.getParameter("evento");
         System.out.println("control.ServProveedor.processRequest()" + "EVENTO: " + evento);
        if (evento.equals("registrarProveedor")) {
            registrar(request, response);
        } else if (evento.equals("formActualizarProveedor")) {
          iractualizar(request, response);
        }  else if (evento.equals("actualizarProveedor")) {
            actualizar(request, response);
        }
        } catch (Exception ex) {
                Logger.getLogger(ServUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(ServProveedor.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(ServProveedor.class.getName()).log(Level.SEVERE, null, ex);
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
 private void registrar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mensaje="";
        String naturaleza=request.getParameter("naturaleza");
        String ruc=request.getParameter("ruc");
        String dni=request.getParameter("dni");
        String RazonSocial=request.getParameter("RazonSocial").toUpperCase().trim();
        String nombres=request.getParameter("nombres").toUpperCase().trim();
        String paterno=request.getParameter("paterno").toUpperCase().trim();
        String materno=request.getParameter("materno").toUpperCase().trim();
        String telefono=request.getParameter("telefono").trim();
        String direccion=request.getParameter("direccion").toUpperCase().trim();
        String correo=request.getParameter("correo").trim().toLowerCase();
        
          HttpSession session = request.getSession(); 
         Usuario usuario=usuario=new Usuario();
        usuario= (Usuario) session.getAttribute("usuario");
        int usuario_reg=usuario.getIdUsuario();
        
        Proveedor prov=null;
        if (naturaleza.equals("P")) {
            prov=new Proveedor();
            prov.setApellidoMaterno(DirTexto.getInstance().cambiarFormatoUTF8(materno));
            prov.setApellidoPaterno(DirTexto.getInstance().cambiarFormatoUTF8(paterno));
            prov.setDireccion(DirTexto.getInstance().cambiarFormatoUTF8(direccion));
            prov.setEmail(DirTexto.getInstance().cambiarFormatoUTF8(correo));
            prov.setDniRuc(dni);
            prov.setNaturalezaProveedor(naturaleza);
            prov.setNombres(DirTexto.getInstance().cambiarFormatoUTF8(nombres));
            prov.setRazonSocial("");
            prov.setTelefono(telefono);
            prov.setUsuarioReg(usuario_reg);
        }else if (naturaleza.equals("E")) {
            prov=new Proveedor();
            prov.setApellidoMaterno("");
            prov.setApellidoPaterno("");
            prov.setDireccion(DirTexto.getInstance().cambiarFormatoUTF8(direccion));
            prov.setEmail(DirTexto.getInstance().cambiarFormatoUTF8(correo));
            prov.setDniRuc(ruc);
            prov.setNaturalezaProveedor(naturaleza);
            prov.setNombres("");
            prov.setRazonSocial(DirTexto.getInstance().cambiarFormatoUTF8(RazonSocial));
            prov.setTelefono(telefono); 
            prov.setUsuarioReg(usuario_reg);
        }
        String respuesta=LogicProveedor.getInstance().registrarProveedor(prov);
        if (respuesta.equals("OK") ){
            mensaje = "Proveedor registrado exitosamente";
        } else {
            mensaje = "Error, no se registró el Proveedor";
        }
        request.setAttribute("lista",LogicProveedor.getInstance().listarProveedor());
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("EVENTO", "REGISTRAR");
        request.setAttribute("body", "registro_proveedor");
        forwar("template.jsp", request, response);
  
    }


    private void iractualizar(HttpServletRequest request, HttpServletResponse response) throws Exception {
       String id = request.getParameter("idproveedor").trim();
        Proveedor prov = new Proveedor();
        prov = LogicProveedor.getInstance().buscarProveedorID(Integer.parseInt(id));
        request.setAttribute("lista",LogicProveedor.getInstance().listarProveedor());
        request.setAttribute("objprov", prov);
        request.setAttribute("EVENTO", "ACTUALIZAR");
        request.setAttribute("body", "registro_proveedor");
        forwar("template.jsp", request, response);    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws Exception {
         String idproveedor = request.getParameter("idproveedor");
        int idprov=Integer.parseInt(idproveedor);
        String respuesta = "";
        String naturaleza=request.getParameter("naturaleza");
        String ruc=request.getParameter("ruc");
        String dni=request.getParameter("dni");
        String RazonSocial=request.getParameter("RazonSocial").toUpperCase().trim();
        String nombres=request.getParameter("nombres").toUpperCase().trim();
        String paterno=request.getParameter("paterno").toUpperCase().trim();
        String materno=request.getParameter("materno").toUpperCase().trim();
        String telefono=request.getParameter("telefono").trim();
        String direccion=request.getParameter("direccion").toUpperCase().trim();
        String correo=request.getParameter("correo").trim().toLowerCase();
        
         HttpSession session = request.getSession(); 
         Usuario usuario=usuario=new Usuario();
        usuario= (Usuario) session.getAttribute("usuario");
        int usuario_reg=usuario.getIdUsuario();
        
        Proveedor prov=null; 
        if (naturaleza.equals("P")) {
            prov=new Proveedor();
            prov.setIdProveedor(idprov);
            prov.setApellidoMaterno(DirTexto.getInstance().cambiarFormatoUTF8(materno));
            prov.setApellidoPaterno(DirTexto.getInstance().cambiarFormatoUTF8(paterno));
            prov.setDireccion(DirTexto.getInstance().cambiarFormatoUTF8(direccion));
            prov.setEmail(DirTexto.getInstance().cambiarFormatoUTF8(correo));
            prov.setDniRuc(dni);
            prov.setNaturalezaProveedor(naturaleza);
            prov.setNombres(DirTexto.getInstance().cambiarFormatoUTF8(nombres));
            prov.setRazonSocial("");
            prov.setTelefono(telefono);
            prov.setUsuarioReg(usuario_reg);
        }else if (naturaleza.equals("E")) {
            prov=new Proveedor();
            prov.setIdProveedor(idprov);
            prov.setApellidoMaterno("");
            prov.setApellidoPaterno("");
            prov.setDireccion(DirTexto.getInstance().cambiarFormatoUTF8(direccion));
            prov.setEmail(DirTexto.getInstance().cambiarFormatoUTF8(correo));
            prov.setDniRuc(ruc);
            prov.setNaturalezaProveedor(naturaleza);
            prov.setNombres("");
            prov.setRazonSocial(DirTexto.getInstance().cambiarFormatoUTF8(RazonSocial));
            prov.setTelefono(telefono); 
            prov.setUsuarioMod(usuario_reg);
        }
        respuesta = LogicProveedor.getInstance().actualizar(prov);
        String mensaje = null;
        request.setAttribute("lista", LogicProveedor.getInstance().listarProveedor());
        if (respuesta.equals("OK")) {
            mensaje = "Proveedor Actualizado exitosamente";
        } else {
            mensaje = "Error, no se actualizó el proveedor";
        }
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("EVENTO", "REGISTRAR");
        request.setAttribute("body", "registro_proveedor");
        forwar("template.jsp", request, response);
        
    }

    private void forwar(String templatejsp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(templatejsp).forward(request, response);
    }
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
                forwar("login.jsp", request, response);

            }

        } else {
            respuesta = 0;
            session.invalidate();
            request.setAttribute("msg", "Sesion Expirada");
            forwar("login.jsp", request, response);
        }

        return respuesta;
    }

}
