/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.LogicUsuario;
import model.dto.Usuario;

/**
 *
 * @author FARID
 */
public class ServLogin extends HttpServletConf {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        
        this.confHeader(request, response);
        HttpSession session = request.getSession();            
        String usuario = request.getParameter("usuario") != null ? request.getParameter("usuario") : "";
        String clave = request.getParameter("clave") != null ? request.getParameter("clave") : "";
        
        Usuario usuarioBean = null;
        usuarioBean=new Usuario();
        LogicUsuario logicusu=new LogicUsuario();
        usuarioBean= logicusu.validarUsuario(usuario, clave);
        
        if (usuarioBean != null) {
            final String ID = request.getSession().getId();
            session.setAttribute("ID", ID);
            session.setAttribute("usuario", usuarioBean);
            request.setAttribute("body", "home");
            forwar("template.jsp", request, response);
        } else {
            request.setAttribute("msg", "Datos Incorrectos");
            forwar("login.jsp", request, response);
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
            Logger.getLogger(ServLogin.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServLogin.class.getName()).log(Level.SEVERE, null, ex);
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

}
