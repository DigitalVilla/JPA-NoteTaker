/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 767110
 */
@WebServlet(name = "NotesOperationController", urlPatterns = {"/NotesOperationController"})
public class NotesOperationController extends HttpServlet {

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
        String note = request.getParameter("note");
        String remove = request.getParameter("remove");
        HttpSession session = request.getSession();

        ArrayList<String> notes = (ArrayList<String>) session.getAttribute("notes");

        // create notes first time
        if (notes == null) {
            notes = new ArrayList<String>();
            session.setAttribute("notes", notes);
        }

        if (note != null && !note.equals("")) {
            notes.add(note);
            request.setAttribute("message", "New note Added");
            getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);

        } else if (remove != null && !remove.equals("")) {
            int indx = (!remove.equals("ALL")) ? Integer.parseInt(remove) : -1;
            // restart array
            if (remove.equals("ALL")) {
                notes.clear();
                request.setAttribute("message", "All notes removed");
            } // remove one itme
            else if (indx != -1 && indx < notes.size()) {
                notes.remove(indx);
                request.setAttribute("message", "One note removed");
            }
            remove = null;
            getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
        } // redirect back to notes
        else {
            getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
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
