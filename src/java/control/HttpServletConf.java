/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author FARID
 */
public class HttpServletConf extends HttpServlet {

    public void confHeader(HttpServletRequest req, HttpServletResponse resp) {

        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // HTTP 1.1
        resp.setHeader("Cache-Control",
                "no-cache, no-store, max-age=0, must-revalidate");
        // HTTP 1.0
        resp.setHeader("Pragma", "no-cache");
        // Proxies.
        resp.setDateHeader("Expires", 0);
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        resp.addHeader("Cache-Control", "post-check=0, pre-check=0");

    }

    public void forwar(String jsp, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(jsp).forward(req, resp);
    }

    // PRIVATE //
    public void redirect(String aDestinationPage, HttpServletResponse aResponse)
            throws IOException {
        String urlWithSessionID = aResponse.encodeRedirectURL(aDestinationPage);
        aResponse.sendRedirect(urlWithSessionID);
    }

}
