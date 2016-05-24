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
                    
                    query = "select codprodotto from prodotti_utenti where idutente="+cliente.id;
                    Statement st = conn.createStatement();
                    ResultSet res2 = st.executeQuery(query);
                    while(res2.next())
                    {
                        cliente.prodottiAcquistabili.add(getProdotto(res2.getInt("codprodotto")));
                    }
                    st.close();
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
                   
                query = "select codprodotto from prodotti_utenti where idutente="+cliente.id;
                Statement st = conn.createStatement();
                ResultSet res2 = st.executeQuery(query);
                while(res2.next())
                {
                    cliente.prodottiAcquistabili.add(getProdotto(res2.getInt("codprodotto")));
                }
                st.close();
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
                   
                query = "select codprodotto from prodotti_utenti where idutente="+cliente.id;
                Statement st = conn.createStatement();
                ResultSet res2 = st.executeQuery(query);
                while(res2.next())
                {
                    cliente.prodottiAcquistabili.add(getProdotto(res2.getInt("codprodotto")));
                }
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
    
    public void eliminaProdotto(int codProdotto) throws SQLException
    {
        // path, username, password
        Connection conn = DriverManager.getConnection(connectionString, "robertospiga", "0000");   
        
        PreparedStatement deleteProdotti = null;
        // Sql 
        String insertProdotto = "delete from prodotti "
                + "where cod = ?";
        
        try
        {
           conn.setAutoCommit(false);
           deleteProdotti = conn.prepareStatement(insertProdotto);           
           // Prodotti
           deleteProdotti.setInt(1, codProdotto);
           
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
    
    public void buy(int codProdotto, int idCliente) throws SQLException
    {
        // path, username, password
        Connection conn = DriverManager.getConnection(connectionString, "robertospiga", "0000");   
        
        PreparedStatement updateProdottiVenditore = null;
        PreparedStatement updateSaldoCliente = null;
        PreparedStatement updateSaldoVenditore = null;
             
        // Sql 
        String deleteProdottoV = "delete from prodotti_utenti "
                + "where codprodotto = ? and idutente = ?";
        
        String updateSaldoC = "update utenti set "
                + "saldo = ? "
                + "where id = ?";
        
        String updateSaldoV = "update utenti set "
                + "saldo = ? "
                + "where id = ?";
        
        try
        {
           conn.setAutoCommit(false);
           
           int idVenditore = getIdVenditoreFromProdotto(codProdotto);
           Cliente cliente = getCliente(idCliente);
           Venditore venditore = getVenditore(idVenditore);
           Prodotto prodotto = getProdotto(codProdotto);
           
           updateProdottiVenditore = conn.prepareStatement(deleteProdottoV);
           updateSaldoCliente = conn.prepareStatement(updateSaldoC);
           updateSaldoVenditore = conn.prepareStatement(updateSaldoV);
           
           // ProdottiVenditore
           updateProdottiVenditore.setInt(1, codProdotto);
           updateProdottiVenditore.setInt(2, idVenditore);
           // SaldoCliente
           updateSaldoCliente.setDouble(1, cliente.getSaldo() - prodotto.getPrezzo() );
           updateSaldoCliente.setInt(2, idCliente);
           // SaldoVenditore
           updateSaldoVenditore.setDouble(1, venditore.getSaldo() + prodotto.getPrezzo() );
           updateSaldoVenditore.setInt(2, idVenditore);
           
           int c1 = updateProdottiVenditore.executeUpdate();
           int c2 = updateSaldoCliente.executeUpdate();
           int c3 = updateSaldoVenditore.executeUpdate();
           
           if(c1 != 1 || c2 != 1 || c3 != 1 || idVenditore == 0)
               conn.rollback();
           
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
            if(updateProdottiVenditore != null)
                updateProdottiVenditore.close();
            if(updateSaldoCliente != null)
                updateSaldoCliente.close();
            if(updateSaldoVenditore != null)
                updateSaldoVenditore.close();
            
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
                           + "WHERE utenti.tipo = true and codprodotto = ?;";
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

}