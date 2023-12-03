/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mbpe.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mbpe.DAOimpl.DaoPlantillaImpl;
import mbpe.clases.Plantilla;
import mbpe.clases.Usuario;

/**
 *
 * @author Alex
 */
@WebServlet(name = "AddProductServlet", urlPatterns = {"/AddProduct"})
public class AddProductServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        DaoPlantillaImpl plantilla = new DaoPlantillaImpl();
        Plantilla mb = new Plantilla();

        mb.setAdded(true);
        System.out.println("ADD PRODUCT SERVLET added = true");
        Usuario user = Usuario.ObtenerUser();
        request.setAttribute("conectado", user.getConectado());

        RequestDispatcher dispatcher = null;

        int id_plantilla = Integer.parseInt(request.getParameter("id_plantilla"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        user.ObtenerCarrito().add(id_plantilla);
        user.ObtenerCantidad().add(cantidad);

        for (int i = 0; i < user.ObtenerCarrito().size(); i++) {
            System.out.println("producto: " + user.ObtenerCarrito().get(i) + " cantidad: " + user.ObtenerCantidad().get(i));
        }

        System.out.println("producto: " + id_plantilla);
        System.out.println("cantidad: " + cantidad);

        int idEnvio = id_plantilla;
        request.setAttribute("id_plantilla", idEnvio);
        dispatcher = request.getRequestDispatcher("/MysteryBox");
        dispatcher.forward(request, response);

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
