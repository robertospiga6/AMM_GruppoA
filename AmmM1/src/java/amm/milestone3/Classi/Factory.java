/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.milestone3.Classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Robi
 */

/* Costruttore */
public class Factory {
    
    // Attributi
    String connectionString = "";
    
        // Singleton
    private static Factory singleton;
        
    public static Factory getInstance() {
        if (singleton == null) {
            singleton = new Factory();
        }
        return singleton;
    }
        
    private Factory(){
     
    }
    
    /* Metodi */
    
    // Restituisce la lista di tutti gli studenti il cui nome e cognome contiene la stringa in input
    public ArrayList<Prodotto> getProdotti(String text)
    {
        ArrayList<Prodotto> lista = new ArrayList<Prodotto>();
        
        try
        {
            Connection conn = DriverManager.getConnection(connectionString, "robertospiga", "0000");
            String query = "select * " +
                           "from prodotti " + 
                           "where LOWER(nome) LIKE ? OR LOWER(tipo) LIKE ? OR nome LIKE ? OR tipo LIKE ?";         
            PreparedStatement stmt = conn.prepareStatement(query);
            // Assegna dati
            text = "%"+text+"%";
            stmt.setString(1, text);            
            stmt.setString(2, text);
            stmt.setString(3, text);            
            stmt.setString(4, text);
            ResultSet res = stmt.executeQuery();
            
            while(res.next())
            {
                Prodotto product = new Prodotto();
                product.setCod(res.getInt("cod"));
                product.setNome(res.getString("nome"));
                product.setTipologia(res.getString("tipo"));
                product.setPezzi(res.getInt("pezzi"));
                product.setPrezzo(res.getDouble("prezzo"));
                product.setImgurl(res.getString("imgurl"));
                lista.add(product);
            }
            
            stmt.close();
            conn.close();
        }
        catch(SQLException e)
        {}
        
        return lista;
    }
    
    
    public Utente getUtente(String username, String password)
    {
        try
        {
            Connection conn = DriverManager.getConnection(connectionString, "robertospiga","0000");
            // sql command
            String query = "select * from utenti where username= ? and password= ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            // dati
            stmt.setString(1, username);
            stmt.setString(2, password);
            //
            ResultSet set = stmt.executeQuery();
            
            if(set.next())
            {
                if(set.getBoolean("tipo")){
                    Venditore venditore = new Venditore();
                    venditore.id = set.getInt("id");
                    venditore.nome = set.getString("nome");
                    venditore.cognome = set.getString("cognome");
                    venditore.username = set.getString("username");
                    venditore.password = set.getString("password");
                    SaldoConto saldo = new SaldoConto();
                    saldo.setSaldo(set.getDouble("saldo"));
                    venditore.setSaldo(saldo);
                    
                    query = "select codprodotto from prodotti_utenti where idutente="+venditore.id;
                    Statement st = conn.createStatement();
                    ResultSet res2 = st.executeQuery(query);
                    while(res2.next())
                    {
                        venditore.prodottiInVendita.add(getProdotto(res2.getInt("codprodotto")));
                    }
                    st.close();
                    stmt.close();
                    conn.close();
                    return venditore;
                }
                else{
                    Cliente cliente = new Cliente();
                    cliente.id = set.getInt("id");
                    cliente.nome = set.getString("nome");
                    cliente.cognome = set.getString("cognome");
                    cliente.username = set.getString("username");
                    cliente.password = set.getString("password");
                    SaldoConto saldo = new SaldoConto();
                    saldo.setSaldo(set.getDouble("saldo"));
                    cliente.setSaldo(saldo);
                    cliente.setProdottiAcquistabili(getProdotti());
                    stmt.close();
                    conn.close();
                    return cliente;
                }
            }
                       
            stmt.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
            
    // Cliente
    // Dato un id restituisce il relativo cliente (se esiste un profesclientesore con quell'id, altrimenti
    // restituisce null).
    public Cliente getCliente(int id)
    {
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "robertospiga", "0000");
            // Query
            String query = "select * from utenti "
            + "where id = ? and tipo = false";
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            // Si associano i valori
            stmt.setInt(1, id);
            // Esecuzione query
            ResultSet res = stmt.executeQuery();
            
             // ciclo sulle righe restituite
            if(res.next()) 
            {
                Cliente cliente = new Cliente();
                cliente.id = res.getInt("id");
                cliente.nome = res.getString("nome");
                cliente.cognome = res.getString("cognome");
                cliente.username = res.getString("username");
                cliente.password = res.getString("password");
                SaldoConto saldo = new SaldoConto();
                saldo.setSaldo(res.getDouble("saldo"));
                cliente.setSaldo(saldo);
                cliente.setProdottiAcquistabili(getProdotti());
                stmt.close();
                conn.close();
                return cliente;
            }   
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
    
    // Restituisce la lista di tutti i clienti
    public ArrayList<Cliente> getClienti()
    {
        ArrayList<Cliente> listaClienti = new ArrayList<Cliente>();
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "robertospiga", "0000");
            Statement stmt = conn.createStatement();
            String query = "select * from utenti where tipo = false";
            ResultSet res = stmt.executeQuery(query);
            
             // ciclo sulle righe restituite
            while(res.next()) 
            {
                Cliente cliente = new Cliente();
                cliente.id = res.getInt("id");
                cliente.nome = res.getString("nome");
                cliente.cognome = res.getString("cognome");
                cliente.username = res.getString("username");
                cliente.password = res.getString("password");
                SaldoConto saldo = new SaldoConto();
                saldo.setSaldo(res.getDouble("saldo"));
                cliente.setSaldo(saldo);
                cliente.setProdottiAcquistabili(getProdotti());
                listaClienti.add(cliente);
            } 
            
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return listaClienti;
    }
    
    
    
    // Venditori
    // Restituisce la lista di tutti i venditori
    public ArrayList<Venditore> getVenditori()
    {
        ArrayList<Venditore> listaVenditori = new ArrayList<Venditore>();
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "robertospiga", "0000");
            Statement stmt = conn.createStatement();
            String query = "select * from utenti where tipo = true";
            ResultSet set = stmt.executeQuery(query);
            
             // ciclo sulle righe restituite
            while(set.next()) 
            {
                    Venditore venditore = new Venditore();
                    venditore.id = set.getInt("id");
                    venditore.nome = set.getString("nome");
                    venditore.cognome = set.getString("cognome");
                    venditore.username = set.getString("username");
                    venditore.password = set.getString("password");
                    SaldoConto saldo = new SaldoConto();
                    saldo.setSaldo(set.getDouble("saldo"));
                    venditore.setSaldo(saldo);
                    
                    query = "select codprodotto from prodotti_utenti where idutente="+venditore.id;
                    Statement st = conn.createStatement();
                    ResultSet res2 = st.executeQuery(query);
                    while(res2.next())
                    {
                        venditore.prodottiInVendita.add(getProdotto(res2.getInt("codprodotto")));
                    }
                listaVenditori.add(venditore);
            }     
            
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return listaVenditori;
    }
    
    // Dato un id restituisce il relativo venditore (se esiste un venditore con quell'id, altrimenti
    // restituisce null).
    public Venditore getVenditore(int id)
    {
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "robertospiga", "0000");
            String query = "select * from utenti "
            + "where id = ? and tipo = true";
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            // Si associano i valori
            stmt.setInt(1, id);
            // Esecuzione query
            ResultSet set = stmt.executeQuery();
           
             // ciclo sulle righe restituite
            if(set.next()) 
            {
                
                    Venditore venditore = new Venditore();
                    venditore.id = set.getInt("id");
                    venditore.nome = set.getString("nome");
                    venditore.cognome = set.getString("cognome");
                    venditore.username = set.getString("username");
                    venditore.password = set.getString("password");
                    SaldoConto saldo = new SaldoConto();
                    saldo.setSaldo(set.getDouble("saldo"));
                    venditore.setSaldo(saldo);
                    
                    query = "select codprodotto from prodotti_utenti where idutente="+venditore.id;
                    Statement st = conn.createStatement();
                    ResultSet res2 = st.executeQuery(query);
                    while(res2.next())
                    {
                        venditore.prodottiInVendita.add(getProdotto(res2.getInt("codprodotto")));
                    }
                    st.close();
                    stmt.close();
                    conn.close();
                    return venditore;
            }
            
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
    
    
    
    
    // Prodotti
    // Restituisce la lista di tutti i prodotti
    public ArrayList<Prodotto> getProdotti()
    {
        ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "robertospiga", "0000");
            Statement stmt = conn.createStatement();
            String query = "select * from prodotti";
            ResultSet set = stmt.executeQuery(query);
            
             // ciclo sulle righe restituite
            while(set.next()) 
            {
                Prodotto product = new Prodotto();
                product.setCod(set.getInt("cod"));
                product.setNome(set.getString("nome"));
                product.setTipologia(set.getString("tipo"));
                product.setPezzi(set.getInt("pezzi"));
                product.setPrezzo(set.getDouble("prezzo"));
                product.setImgurl(set.getString("imgurl"));
                listaProdotti.add(product);
            }
            
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return listaProdotti;
    }
    
    // Dato un id restituisce il relativo prodotto
    public Prodotto getProdotto(int cod)
    {
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "robertospiga", "0000");
            String query = "select * from prodotti where cod = ? ";
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            // Si associano i valori
            stmt.setInt(1, cod);
            // Esecuzione query
            ResultSet res = stmt.executeQuery();
            
             // ciclo sulle righe restituite
            if(res.next()) 
            {
                Prodotto product = new Prodotto();
                product.setCod(res.getInt("cod"));
                product.setNome(res.getString("nome"));
                product.setTipologia(res.getString("tipo"));
                product.setPezzi(res.getInt("pezzi"));
                product.setPrezzo(res.getDouble("prezzo"));
                product.setImgurl(res.getString("imgurl"));
   
                stmt.close();
                conn.close();
                return product;
            } 
            
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
    
        
    public void aggiungiProdotto(int idVenditore, String nome, String tipo, int pezzi, double prezzo, String imgurl) throws SQLException
    {
        // path, username, password
        Connection conn = DriverManager.getConnection(connectionString, "robertospiga", "0000");   
        
        PreparedStatement updateProdottiUtenti = null;
        PreparedStatement updateProdotti = null;
        int cod=0;
        // Sql 
        String insertProdottiUtenti = "insert into prodotti_utenti "
                + "(codprodotto, idutente) "
                + "values (?,?)";
        String insertProdotto = "insert into prodotti "
                + "(nome, tipo, pezzi, prezzo, imgurl) "
                + "values (?,?,?,?,?)";
        
        try
        {
           conn.setAutoCommit(false);
           updateProdotti = conn.prepareStatement(insertProdotto);
           updateProdottiUtenti = conn.prepareStatement(insertProdottiUtenti);
           
           // Prodotti
           updateProdotti.setString(1, nome);
           updateProdotti.setString(2, tipo);
           updateProdotti.setInt(3, pezzi);
           updateProdotti.setDouble(4, prezzo);
           updateProdotti.setString(5, imgurl);
           
           int c2 = updateProdotti.executeUpdate();
           if(c2 != 1) conn.rollback();
           conn.commit();
           
           // Prodotti_Utenti
           cod=getCodProdotto(nome, tipo, pezzi, prezzo, imgurl);
           if(cod != 0){
                
                updateProdottiUtenti.setInt(1, cod);
                updateProdottiUtenti.setInt(2, idVenditore);

                int c1 = updateProdottiUtenti.executeUpdate();

                if(c1 != 1)
                    conn.rollback();

                conn.commit(); 
           }          
        }catch(SQLException e)
        {
            try
            {
                conn.rollback();
            }catch(SQLException e2)
            {
                
            }
        }
        finally
        {
            if(updateProdottiUtenti != null)
                updateProdottiUtenti.close();
            if(updateProdotti != null)
                updateProdotti.close();
            
            conn.setAutoCommit(true);
            conn.close();
        }    
    }
    
    public void modificaProdotto(int codProdotto, String nome, String tipo, int pezzi, double prezzo, String imgurl) throws SQLException
    {
        
        // path, username, password
        Connection conn = DriverManager.getConnection(connectionString, "robertospiga", "0000");   
        
        PreparedStatement updateProdotti = null;
        // Sql 
        String insertProdotto = "update prodotti set "
                + "nome = ?, tipo = ?, pezzi = ?, prezzo = ?, imgurl = ? "
                + "where cod = ?";
        
        try
        {
           conn.setAutoCommit(false);
           updateProdotti = conn.prepareStatement(insertProdotto);           
           // Prodotti
           updateProdotti.setString(1, nome);
           updateProdotti.setString(2, tipo);
           updateProdotti.setInt(3, pezzi);
           updateProdotti.setDouble(4, prezzo);
           updateProdotti.setString(5, imgurl);
           updateProdotti.setInt(6, codProdotto);
           
           int c2 = updateProdotti.executeUpdate();
           if(c2 != 1) conn.rollback();
           conn.commit();
           
        }catch(SQLException e)
        {
            try
            {
                conn.rollback();
            }catch(SQLException e2)
            {
                
            }
        }
        finally
        {
        
            if(updateProdotti != null)
                updateProdotti.close();
            
            conn.setAutoCommit(true);
            conn.close();
        }  
    }
    
    public void eliminaProdotto(String codProdotto, int idUtente) throws SQLException
    {
        // path, username, password
        Connection conn = DriverManager.getConnection(connectionString, "robertospiga", "0000");   
        
        PreparedStatement deleteProdotti = null;
        // Sql 
        String deleteProdotto = "delete from prodotti_utenti "
                + "where codprodotto = ? "
                + "and idutente = ?";
        
        try
        {
           conn.setAutoCommit(false);
           deleteProdotti = conn.prepareStatement(deleteProdotto);           
           // Prodotti
           deleteProdotti.setInt(1, Integer.parseInt(codProdotto));
           deleteProdotti.setInt(2, idUtente);
           
           int c2 = deleteProdotti.executeUpdate();
           if(c2 != 1) conn.rollback();
           conn.commit();
           
           
        }catch(SQLException e)
        {
            try
            {
                conn.rollback();
            }catch(SQLException e2)
            {
                
            }
        }
        finally
        {
            if(deleteProdotti != null)
                deleteProdotti.close();
            
            conn.setAutoCommit(true);
            conn.close();
        }    
    }
    
    public void buy(int codProdotto, int idCliente, int n) throws SQLException
    {
        // path, username, password
        Connection conn = DriverManager.getConnection(connectionString, "robertospiga", "0000");   
        
        PreparedStatement updateProdottiCliente = null;
        PreparedStatement updateProdottiVenditore = null;
        PreparedStatement updateSaldoCliente = null;
        PreparedStatement updateSaldoVenditore = null;
        PreparedStatement updatePezziProdotto = null;
        
        //controllo se è l'ultimo pezzo disponibile, nel caso verrà eliminato daglioggetti vendibili di uno dei due venditori
        boolean pezzi = false;
        boolean prodotti_clienti = inRelazionePC(codProdotto, idCliente);
        System.out.println("prodotti_clienti:"+prodotti_clienti);
        if(getProdotto(codProdotto).getPezzi() - n < 2) {
            pezzi = true;
        }
        
        // Sql 
        
        String deleteProdottoV = "delete from prodotti_utenti "
                + "where codprodotto = ? and idutente = ?";
        
        String addProdottoC = "";
        if(prodotti_clienti){
            addProdottoC = "insert into prodotti_utenti "
                    + "(codprodotto, idutente) "
                    + "values (?,?)";
        }
        String updateSaldoC = "update utenti set "
                + "saldo = ? "
                + "where id = ?";
        
        String updateSaldoV = "update utenti set "
                + "saldo = ? "
                + "where id = ?";
        
        String updatePezzi = "update prodotti set "
                + "pezzi = ? "
                + "where cod = ?";
        
        
        try
        {
           conn.setAutoCommit(false);
           
           int idVenditore = 0;
           idVenditore = getIdVenditoreFromProdotto(codProdotto);
           Cliente cliente = getCliente(idCliente);
           Venditore venditore = getVenditore(idVenditore);
           Prodotto prodotto = getProdotto(codProdotto);
           
           if(prodotti_clienti) updateProdottiCliente = conn.prepareStatement(addProdottoC);
           
           if(pezzi){
                updateProdottiVenditore = conn.prepareStatement(deleteProdottoV);
           }
           updateSaldoCliente = conn.prepareStatement(updateSaldoC);
           updateSaldoVenditore = conn.prepareStatement(updateSaldoV);
           updatePezziProdotto = conn.prepareStatement(updatePezzi);
           
           // ProdottiCliente
           if(prodotti_clienti){
                updateProdottiCliente.setInt(1, codProdotto);
                updateProdottiCliente.setInt(2, idCliente);
           }
           
           if(pezzi){
                // ProdottiVenditore
                updateProdottiVenditore.setInt(1, codProdotto);
                updateProdottiVenditore.setInt(2, idVenditore);
           }
           // SaldoCliente
           updateSaldoCliente.setDouble(1, cliente.getSaldo() - prodotto.getPrezzo()*n );
           updateSaldoCliente.setInt(2, idCliente);
           // SaldoVenditore
           updateSaldoVenditore.setDouble(1, venditore.getSaldo() + prodotto.getPrezzo()*n );
           updateSaldoVenditore.setInt(2, idVenditore);
           // PezziProdotto
           updatePezziProdotto.setInt(1, (getProdotto(codProdotto).getPezzi()-n) );
           updatePezziProdotto.setInt(2, codProdotto);
           
           
           
           int c2 = updateSaldoCliente.executeUpdate();
           int c3 = updateSaldoVenditore.executeUpdate();          
           int c5 = updatePezziProdotto.executeUpdate();
           if(pezzi & prodotti_clienti){
               int c1 = updateProdottiVenditore.executeUpdate();
               int c4 = updateProdottiCliente.executeUpdate(); 
               if(c1 != 1 || c2 != 1 || c3 != 1 || c4 != 1 || c5 != 1 || idVenditore == 0)
                   conn.rollback();
           }
           else if(pezzi){
               int c1 = updateProdottiVenditore.executeUpdate();
               if(c1 != 1 || c2 != 1 || c3 != 1 || c5 != 1 || idVenditore == 0)
                   conn.rollback();
           }
           else if(prodotti_clienti){
           int c4 = updateProdottiCliente.executeUpdate(); 
               if(c4 != 1 || c2 != 1 || c3 != 1 || c5 != 1 || idVenditore == 0)
                   conn.rollback();
           }
           else{ 
               if(c2 != 1 || c3 != 1 || c5 != 1 || idVenditore == 0)
                   conn.rollback();
           }
           conn.commit();           
        }catch(SQLException e)
        {
            try
            {
                conn.rollback();
            }catch(SQLException e2)
            {
                
            }
        }
        finally
        {
            
            if(pezzi){
                if(updateProdottiVenditore != null)
                    updateProdottiVenditore.close();
            }
            if(updateSaldoCliente != null)
                updateSaldoCliente.close();
            if(updateSaldoVenditore != null)
                updateSaldoVenditore.close();
            
            if(prodotti_clienti) if(updateProdottiCliente != null) updateProdottiCliente.close(); 
                
            if(updatePezziProdotto != null)
                updatePezziProdotto.close();
            
            conn.setAutoCommit(true);
            conn.close();
        }    
    }
    
    public int getCodProdotto(String nome, String tipo, int pezzi, double prezzo, String imgurl) 
    {
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "robertospiga", "0000");
            String query = "select prodotti.cod from prodotti "
                    + "where nome = ? and tipo = ? and pezzi = ? "
                    + "and prezzo = ? and imgurl = ?";
            int counter=0;
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            // Si associano i valori
            stmt.setString(1, nome);
            stmt.setString(2, tipo);
            stmt.setInt(3, pezzi);            
            stmt.setDouble(4, prezzo);
            stmt.setString(5, imgurl);
            
            // Esecuzione query
            ResultSet res = stmt.executeQuery();
            
            // ciclo sulle righe restituite
            if(res.next() & counter == 0) 
            {
                int cod = res.getInt("cod");
                stmt.close();
                conn.close();
                return cod;
            } 
            
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    
    public void setConnectionString(String s){
	this.connectionString = s;
    }
    public String getConnectionString(){
	return this.connectionString;
    }

    private int getIdVenditoreFromProdotto(int codProdotto) 
    {
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "robertospiga", "0000");
            String query = "SELECT prodotti_utenti.idutente " 
                           + "FROM utenti " 
                           + "JOIN prodotti_utenti ON prodotti_utenti.idutente = utenti.id " 
                           + "WHERE utenti.tipo = true and codprodotto = ? ";
            int counter=0;
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            // Si associano i valori
            stmt.setInt(1, codProdotto);
            
            // Esecuzione query
            ResultSet res = stmt.executeQuery();
            
            // ciclo sulle righe restituite
            if(res.next() & counter == 0) 
            {
                int id = res.getInt("idutente");
                stmt.close();
                conn.close();
                return id;
            } 
            
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return 0;
    }

    public void setSaldoUtente(int id, String n, int tipo) throws SQLException {
        
        double incr=Double.parseDouble(n);
        
        // path, username, password
        Connection conn = DriverManager.getConnection(connectionString, "robertospiga", "0000");   
        
        PreparedStatement updateSaldo = null;
        // Sql 
        String insertProdotto = "update utenti set "
                + "saldo = ? "
                + "where id = ?";
        
        try
        {
           conn.setAutoCommit(false);
           updateSaldo = conn.prepareStatement(insertProdotto);           
           // Prodotti
           if(tipo==1)
           updateSaldo.setDouble(1,(getCliente(id).getSaldo()+incr));
           else if (tipo==0)
           updateSaldo.setDouble(1,(getVenditore(id).getSaldo()+incr));
           
           updateSaldo.setInt(2, id);
           
           int c2 = updateSaldo.executeUpdate();
           if(c2 != 1) conn.rollback();
           conn.commit();
           
           
        }catch(SQLException e)
        {
            try
            {
                conn.rollback();
            }catch(SQLException e2)
            {
                
            }
        }
        finally
        {
            if(updateSaldo != null)
                updateSaldo.close();
            
            conn.setAutoCommit(true);
            conn.close();
        }    
    }

    private boolean inRelazionePC(int codProdotto, int idCliente) {
        boolean i=false;
        try 
        {
            // path, username, password
            Connection conn = DriverManager.getConnection(connectionString, "robertospiga", "0000");
            String query = "SELECT * " 
                           + "FROM prodotti_utenti "
                           + "WHERE codprodotto = ? " 
                           + "AND idutente = ?";
            // Prepared Statement
            PreparedStatement stmt = conn.prepareStatement(query);
            // Si associano i valori
            stmt.setInt(1, codProdotto);
            stmt.setInt(2, idCliente);
            
            // Esecuzione query
            ResultSet res = stmt.executeQuery();
            // ciclo sulle righe restituite
            i=res.next();
            System.out.println("RES1:"+res.next());
            System.out.println("i:"+i);
            
            
            stmt.close();
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            
        }
        return !i;
    }

}