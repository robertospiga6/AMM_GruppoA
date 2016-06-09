/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.milestone3;

import amm.milestone3.Classi.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "VenditoreServlet", urlPatterns = {"/VenditoreServlet"})
public class VenditoreServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //out.println("A");
        HttpSession session = request.getSession();
        
        Venditore v = null;
        
        if(session.getAttribute("loggedIn") != null){
            ArrayList<Venditore> listaVenditori = Factory.getInstance().getVenditori();
                for(Utente u : listaVenditori)
                {
                    if(u.getId() == Integer.parseInt(request.getParameter("id")))
                    { 
                        session.setAttribute("loggedIn", true);                    
                        session.setAttribute("idUtente", u.getId());

                        if(u instanceof Venditore) 
                        { 
                            request.setAttribute("venditore", u);
                            v=(Venditore) u;
                        }
                    }                    
                }
        }
        //memorizzazione dati del form
        
            //SCELTA OPERAZIONE
            if (request.getParameter("SubmitScelta") != null) {
                String scelta=request.getParameter("scelta");
                request.setAttribute("scelta", scelta);
                request.getRequestDispatcher("M3/venditore.jsp").forward(request, response);
            }
            
            //AGGIUNGI
            if (request.getParameter("SubmitAggiungi") != null) {
                Factory.getInstance().aggiungiProdotto(v.getId(),request.getParameter("Nome"),
                        request.getParameter("Tipologia"),
                        Integer.parseInt(request.getParameter("Pezzi")),
                        Double.parseDouble(request.getParameter("Prezzo")),
                        request.getParameter("ImgUrl"));
            }
            
            //MODIFICA
            if (request.getParameter("SubmitModifica") != null) {
                    Factory.getInstance().modificaProdotto(Integer.parseInt(request.getParameter("Cod")),
                            request.getParameter("Nome"),
                            request.getParameter("Tipologia"),
                            Integer.parseInt(request.getParameter("Pezzi")),
                            Double.parseDouble(request.getParameter("Prezzo")),
                            request.getParameter("ImgUrl"));
                }
            
            //ELIMINA
            if (request.getParameter("SubmitElimina") != null) {
                    Factory.getInstance().eliminaProdotto(request.getParameter("Cod"), v.getId());
            }
            
            if(request.getParameter("SubmitModifica") != null || request.getParameter("SubmitAggiungi") != null){
                
                request.setAttribute("prodotto", Factory.getInstance().getProdotto(Factory.getInstance().getCodProdotto(
                request.getParameter("Nome"),
                request.getParameter("Tipologia"),
                Integer.parseInt(request.getParameter("Pezzi")),
                Double.parseDouble(request.getParameter("Prezzo")),
                request.getParameter("ImgUrl"))));
                request.getRequestDispatcher("M3/prodotto.jsp").forward(request, response);
            }
        
        //richiamo la pagina venditore
        request.getRequestDispatcher("M3/venditore.jsp").forward(request, response);
        
    }
    /*
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
        } catch (SQLException ex) {
            Logger.getLogger(VenditoreServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(VenditoreServlet.class.getName()).log(Level.SEVERE, null, ex);
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
