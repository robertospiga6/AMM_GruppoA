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
@WebServlet(name = "ClienteServlet", urlPatterns = {"/ClienteServlet"})
public class ClienteServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        Cliente x = null;
        PrintWriter out = response.getWriter();
        //out.println("A");
        if(session.getAttribute("loggedIn") != null){
        
            ArrayList<Cliente> listaClienti = Factory.getInstance().getClienti();
                for(Utente u : listaClienti)
                {
                    if(u.getId() == Integer.parseInt(request.getParameter("id")))
                    { 
                        session.setAttribute("loggedIn", true);                    
                        session.setAttribute("idUtente", u.getId());

                        if(u instanceof Cliente) 
                        { 
                            request.setAttribute("cliente", u);
                            x=(Cliente) u;
                        }
                    }                    
                }
        }
        if(request.getParameter("ricaricaC") != null) {
            Factory.getInstance().setSaldoUtente(x.getId(), (String) request.getParameter("Ricarica"), 1); 
            request.setAttribute("ricaricaFatta", true);
        }
        //controllo se c'è un id dell'articolo
        if (request.getParameter("codProdotto") != null) {
            
            Integer codProdotto = Integer.parseInt(request.getParameter("codProdotto"));
            Prodotto a = Factory.getInstance().getProdotto(codProdotto);
            request.setAttribute("prodotto", a);
            request.setAttribute("riepilogo", true); //variabile d'accesso alla pagina
            //controllo se è stato premuto il pulsante conferma
            if (request.getParameter("Conferma") != null) {
                //controllo se il saldo è sufficiente
                int n = Integer.parseInt(request.getParameter("NumeroPezzi"));
                if(x.getSaldo() >= a.getPrezzo()*n){
                    Factory.getInstance().buy(a.getCod(), x.getId(), n);
                    request.setAttribute("conferma", true); //variabile d'accesso alla pagina
                    request.setAttribute("riepilogo", false); //variabile d'accesso alla pagina
                    request.setAttribute("saldoInsuff", false); //variabile d'accesso alla pagina
                    request.setAttribute("cliente", x);
    
                }else{
                    request.setAttribute("saldoInsuff", true); //variabile d'accesso alla pagina
                }
            }
        }
        ArrayList<Prodotto> listaProdotti = Factory.getInstance().getProdotti();
        request.setAttribute("listaProdotti", listaProdotti);
        request.getRequestDispatcher("M3/cliente.jsp").forward(request, response);
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
        } catch (SQLException ex) {
            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
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
