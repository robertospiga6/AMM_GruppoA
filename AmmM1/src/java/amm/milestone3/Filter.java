/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.milestone3;

import amm.milestone3.Classi.Factory;
import amm.milestone3.Classi.Prodotto;
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
 * @author Robi
 */
@WebServlet(name = "Filter", urlPatterns = {"/filter.json"})
public class Filter extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
                
        PrintWriter out=response.getWriter();
        
        HttpSession session = request.getSession(true);
        // Controlla se è stato inviato un commando
        String command = request.getParameter("chiave");
        if (command != null) 
        { 
            // Verifica che commando e id siano stati impostati
            if (command.equals("q")) 
            {
                ArrayList<Prodotto> listaProdotti;
                
                // Esegue la ricerca
                 if (!request.getParameter("valore").equals("")) {// se la stringa inserita non è vuota...
                    System.out.println((String)request.getParameter("valore"));
                    listaProdotti = Factory.getInstance().getProdotti((String)request.getParameter("valore")); }
                else // se nessun filtro, restituisco tutti i prodotti
                    listaProdotti = Factory.getInstance().getProdotti();
                // Imposto la lista come attributo della request, come facevamo per l'HTML
                request.setAttribute("listaProdotti", listaProdotti);
                request.setAttribute("idCliente", session.getAttribute("idUtente"));
                
                // Quando si restituisce del json e' importante segnalarlo ed evitare il caching
                response.setContentType("application/json");
                response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
                response.setHeader("Cache-Control", "no-store, no-cache, "
                        + "must-revalidate");
                
                System.out.println(listaProdotti);
                // Genero il json con una jsp
                request.getRequestDispatcher("M3/listaProdottiJson.jsp").forward(request, response);
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
