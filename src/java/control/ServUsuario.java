/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.mi.diredu.util.DirTexto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.LogicPerfil;
import logica.LogicUsuario;
import model.dto.Perfil;
import model.dto.Usuario;

/**
 *
 * @author Alexander
 */
public class ServUsuario extends HttpServlet {

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
        //response.setContentType("text/html;charset=UTF-8");
        int respuesta = this.validaSesion(request, response);

        if (respuesta == 1) {
            try {
                String evento = request.getParameter("evento");
                System.out.println("control.ServUsuario.processRequest()" + evento);
                System.out.println("control.ServUsuario.processRequest()" + "EVENTO: " + evento);
                if (evento.equals("registrarUsuario")) {
                    registrar(request, response);
                } else if (evento.equals("formActualizarUsuario")) {
                    iractualizar(request, response);
                } else if (evento.equals("eliminarUsuario")) {
                    System.out.println("control.eliminar usuario()");
                } else if (evento.equals("actualizarUsuario")) {
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
            Logger.getLogger(ServUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
        String respuesta = "";
        String nombre = request.getParameter("nombres").toUpperCase().trim();
        String paterno = request.getParameter("paterno").toUpperCase().trim();
        String materno = request.getParameter("materno").toUpperCase().trim();
        String dni = request.getParameter("dni");
        String telefono = request.getParameter("telefono");
        String txtusuario = request.getParameter("usuario");
        String password = request.getParameter("clave");
        int perfil = Integer.parseInt(request.getParameter("perfil").trim());
        String estado = request.getParameter("estado");
        HttpSession session = request.getSession();
        Usuario usuario = usuario = new Usuario();
        usuario = (Usuario) session.getAttribute("usuario");
        int usuario_reg = usuario.getIdUsuario();
        Usuario usu = null;
        usu = new Usuario();
        usu.setApellidoMaterno(DirTexto.getInstance().cambiarFormatoUTF8(materno));
        usu.setApellidoPaterno(DirTexto.getInstance().cambiarFormatoUTF8(paterno));
        usu.setDni(dni);
        usu.setEstado(estado);
        usu.setUsuarioReg(usuario_reg);
        usu.setNombres(DirTexto.getInstance().cambiarFormatoUTF8(nombre));
        usu.setPassword(DirTexto.getInstance().cambiarFormatoUTF8(password));
        usu.setTelefonos(telefono);
        usu.setUsuario(DirTexto.getInstance().cambiarFormatoUTF8(txtusuario));
        Perfil per = new Perfil();
        per.setIdperfil(perfil);
        usu.setPerfil(per);

        LogicUsuario logicUsu = new LogicUsuario();
        respuesta = logicUsu.registrar(usu);
        String mensaje = null;
        request.setAttribute("lista", logicUsu.listarUsuarios());
        if (respuesta == "OK") {
            mensaje = "Usuario registrado exitosamente";
        } else {
            mensaje = "Error, no se registró el usuario";
        }
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("EVENTO", "REGISTRAR");
        request.setAttribute("body", "registro_usuario");
        forwar("template.jsp", request, response);

    }

    private void forwar(String templatejsp, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(templatejsp).forward(request, response);
    }

    private void iractualizar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("idusuario").trim();
        LogicPerfil logiper = new LogicPerfil();
        LogicUsuario logusu = new LogicUsuario();
        Usuario usu = new Usuario();
        usu = logusu.buscarUsuarioID(Integer.parseInt(id));
        request.setAttribute("lista_perfil", logiper.listarPerfiles());
        request.setAttribute("lista", logusu.listarUsuarios());
        request.setAttribute("objusu", usu);
        request.setAttribute("EVENTO", "ACTUALIZAR");
        request.setAttribute("body", "registro_usuario");
        forwar("template.jsp", request, response);

    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String idUsuario = request.getParameter("idusuario");
        int idusuario = Integer.parseInt(idUsuario);
        String respuesta = "";
        String nombre = request.getParameter("nombres").toUpperCase().trim();
        String paterno = request.getParameter("paterno").toUpperCase().trim();
        String materno = request.getParameter("materno").toUpperCase().trim();
        String dni = request.getParameter("dni");
        String telefono = request.getParameter("telefono");
        String txtusuario = request.getParameter("usuario");
        String password = request.getParameter("clave");
        int perfil = Integer.parseInt(request.getParameter("perfil").trim());
        String estado = request.getParameter("estado");
        HttpSession session = request.getSession();
        Usuario usuario = usuario = new Usuario();
        usuario = (Usuario) session.getAttribute("usuario");
        int usuario_reg = usuario.getIdUsuario();
        Usuario usu = null;
        usu = new Usuario();
        usu.setApellidoMaterno(DirTexto.getInstance().cambiarFormatoUTF8(materno));
        usu.setApellidoPaterno(DirTexto.getInstance().cambiarFormatoUTF8(paterno));
        usu.setDni(dni);
        usu.setEstado(estado);
        usu.setUsuarioMod(usuario_reg);
        usu.setNombres(nombre);
        usu.setPassword(DirTexto.getInstance().cambiarFormatoUTF8(password));
        usu.setTelefonos(telefono);
        usu.setUsuario(DirTexto.getInstance().cambiarFormatoUTF8(txtusuario));
        Perfil per = new Perfil();
        per.setIdperfil(perfil);
        usu.setPerfil(per);
        usu.setIdUsuario(idusuario);

        LogicUsuario logicUsu = new LogicUsuario();
        respuesta = logicUsu.actualizar(usu);
        String mensaje = null;
        request.setAttribute("lista", logicUsu.listarUsuarios());
        if (respuesta == "OK") {
            mensaje = "Usuario Actualizado exitosamente";
        } else {
            mensaje = "Error, no se actualizó el usuario";
        }
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("EVENTO", "REGISTRAR");
        request.setAttribute("body", "registro_usuario");
        forwar("template.jsp", request, response);

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
