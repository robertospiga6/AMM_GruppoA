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
 * @author Alessandro
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"}, loadOnStartup = 0)
public class Login extends HttpServlet {
    
    //Costanti
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CLEAN_PATH = "../../web/WEB-INF/db/ammdb";
    private static final String DB_BUILD_PATH = "WEB-INF/db/ammdb";

    @Override 
    public void init(){
        String dbConnection = "jdbc:derby://localhost:1527/ammdb;create=true";
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        Factory.getInstance().setConnectionString(dbConnection);
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
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(true);
        //PrintWriter out=response.getWriter();
        //out.println("prima if");
        
        if(request.getParameter("LogOut") != null) {
            session.invalidate();
            request.getRequestDispatcher("M3/login.jsp").forward(request, response);
        }
        
        
        if((Boolean)session.getAttribute("loggedIn") != null){
            if(session.getAttribute("tipoUtente").equals("venditore")){
                Utente u = Factory.getInstance().getVenditore((int)session.getAttribute("idUtente"));
                request.setAttribute("venditore", u);
                request.getRequestDispatcher("M3/venditore.jsp").forward(request, response);  
            }
            else if(session.getAttribute("tipoUtente").equals("cliente")){
                Utente u = Factory.getInstance().getCliente((int)session.getAttribute("idUtente"));
                request.setAttribute("cliente", u);
                request.getRequestDispatcher("M3/cliente.jsp").forward(request, response);  
            }
        }
        
        
        if(request.getParameter("Submit") != null)
        {
            // Preleva i dati inviati
            String username = request.getParameter("Username");
            String password = request.getParameter("Password");
            
            Utente u = Factory.getInstance().getUtente(username, password);
            if(u != null)
            {
                    
                    session.setAttribute("loggedIn", true);                    
                    session.setAttribute("idUtente", u.getId());
                                        
                    if(u instanceof Cliente) 
                    { 
                        request.setAttribute("cliente", u);
                        session.setAttribute("tipoUtente", "cliente");
                        request.getRequestDispatcher("M3/cliente.jsp").forward(request, response);
                    }
                    else
                    { 
                        request.setAttribute("venditore", u);
                        session.setAttribute("tipoUtente", "venditore");
                        request.getRequestDispatcher("M3/venditore.jsp").forward(request, response);  
                    }
            }  
            else{
                request.setAttribute("logInError", true);
                request.getRequestDispatcher("M3/login.jsp").forward(request, response);
            }
            
        }
        else{
            request.setAttribute("logInError", null);
            request.getRequestDispatcher("M3/login.jsp").forward(request, response);
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
    

    
