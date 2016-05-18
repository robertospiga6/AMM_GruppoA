/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.milestone3;

import amm.milestone3.Classi.Factory;
import amm.milestone3.Classi.Cliente;
import amm.milestone3.Classi.Utente;
import amm.milestone3.Classi.Factory;
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
 * @author Martina
 */
@WebServlet(name = "Venditore", urlPatterns = {"/Venditore"})
public class Venditore extends HttpServlet {

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

        HttpSession session = request.getSession();

        //controllo se è attivo un login
        if (session.getAttribute("loggedId") != null) {
            //Controllo il tipo dell'utente
            if (((String) session.getAttribute("tipoUtente")).equals("venditore")) {
                session.setAttribute("loggedIdVenditore", true); //variabile di sessione per la lettura della pagina
            }
        } else {
            session.setAttribute("loggedIdVenditore", false); //variabile di sessione per la lettura della pagina
        }

        //memorizzazione dati del form
        if (request.getParameter("SubmitForm") != null) {
            ArrayList<ArticoloInVendita> listaArticoli = ArticoliFactory.getInstance().getListaArticoli();
            
            ArticoloInVendita articolo = new ArticoloInVendita();
            articolo.setNome(request.getParameter("Nome"));
            articolo.setTipologia(request.getParameter("Tipologia"));
            articolo.setImgLink(request.getParameter("ImgLink"));
            articolo.setDescrizione(request.getParameter("Descrizione"));
            articolo.setPrezzo(Double.parseDouble(request.getParameter("Prezzo")));
            articolo.setQuantita(Integer.parseInt(request.getParameter("Quantita")));
            listaArticoli.add(articolo);

            request.setAttribute("articolo", articolo);
            request.setAttribute("formArticolo", true);
        } else {
            request.setAttribute("formArticolo", false);
        }

        //richiamo la pagina venditore
        request.getRequestDispatcher("venditore.jsp").forward(request, response);

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
