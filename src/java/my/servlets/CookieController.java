/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import my.utils.CookieHelper;

/**
 *
 * @author 767110
 */
@WebServlet(name = "CookieController", urlPatterns = {"/CookieController"})
public class CookieController extends HttpServlet {

    private CookieHelper c;

    /**
     * Constructor
     */
    public CookieController() {
        c = new CookieHelper();
    }

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
        String style = request.getParameter("styles");
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("username");
        String loaded = request.getParameter("loaded");

        if (loaded != null) {
            request.setAttribute("message", "Your theme has been updated!");
            getServletContext().getRequestDispatcher("/WEB-INF/preferences.jsp").forward(request, response);
            return;
        }

        if (style != null && !style.equals("")) {
            // make User's attributes cookie
            c.addCookie(user, style, 10, response);
            // add a global style cookie with user's theme
            c.addCookie("style", style, 10, response);
        }

        //load changes 
        request.setAttribute("source", "CookieController");
        getServletContext().getRequestDispatcher("/WEB-INF/loading.jsp").forward(request, response);

    }

    /**
     * This method searches for a "cookie style " for the given user. if the
     * user does not have a cookie a default cookie style will be created.
     *
     * @param name name of the user's cookie
     * @param request
     * @param response
     */
    public void getCookieStyle(String user, HttpServletRequest request, HttpServletResponse response) {
        if (user != null) {
            // Create a cookie "style" using the users' cookie parameters.
            boolean cStyle = c.makeCookieByValue(user, "style", request, response);
            // if cookie Style was successfully created return
            if (cStyle) {
                return;
            }
        }
        // else set the default css style to 'dark'
        c.addCookie("style", "blue", 10, response);
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

}
