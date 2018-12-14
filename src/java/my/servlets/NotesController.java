/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.servlets;

import java.io.IOException;
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
@WebServlet(name = "NotesController", urlPatterns = {"/NotesController"})
public class NotesController extends HttpServlet {

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
        String toPreferences = request.getParameter("preferences");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String toNotes = request.getParameter("notes");
        String logout = request.getParameter("logout");
        String loaded = request.getParameter("loaded");

        //	redirect to loading page
        if (loaded != null) {
            // set the sender servlet in source
            request.setAttribute("source", "NotesController");
            getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
            return;
        }

        // If the user is logging out
        if (logout != null) {
            request.getSession().invalidate();
            request.setAttribute("message", "Logged out");
            new CookieHelper().deleteCookies("style", request, response);
            getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
        } // redirect to preferences
        else if (toPreferences != null && toPreferences.equals("true")) {
            toPreferences = null;
            getServletContext().getRequestDispatcher("/WEB-INF/preferences.jsp").forward(request, response);
        } // redirect to notes
        else if (toNotes != null && toNotes.equals("true")) {
            toNotes = null;
            getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
        } // LOGIN VALIDATION
        // if login has not occurred
        else if (username == null && password == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
        } // if empty credentials
        else if (username.equals("") || password.equals("")) {
            request.setAttribute("message", "Username and password are required!");
            getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
        } // if full credentials
        else if (username != null && password != null && !username.equals("") && !password.equals("")) {
            // validate correct user
            if ((username.equals("user1") || username.equals("user2")) && password.equals("pass")) {
                // get the user name
                String usr = username.substring(0, 1).toUpperCase() + username.substring(1);
                // Create a session
                HttpSession session = request.getSession();
                session.setAttribute("username", usr);
                // Set the correct cookie style for the current user.
                new CookieController().getCookieStyle(usr, request, response);
                // forward page
                getServletContext().getRequestDispatcher("/WEB-INF/loading.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Invalid username or password!");
                getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
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

}
