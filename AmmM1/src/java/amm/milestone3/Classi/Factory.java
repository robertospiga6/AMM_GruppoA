/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.milestone3.Classi;

import java.util.ArrayList;

/**
 *
 * @author Robi
 */

/* Costruttore */
public class Factory {
    
     // Attributi
    private static Factory singleton;
    public static Factory getInstance() {
        if (singleton == null) {
            singleton = new Factory();
        }
        return singleton;
    }
    
    String connectionString = new String("");
    
        // Lista Prodotto
        private ArrayList<Prodotto> listaProdotti = new ArrayList<Prodotto>();
        // Lista Clienti
        private ArrayList<Utente> listaClienti = new ArrayList<Utente>();
        // Lista Venditori
        private ArrayList<Utente> listaVenditori = new ArrayList<Utente>();
    
    private Factory(){
        
    // Prodotti         
        Prodotto prodotto1 = new Prodotto();
        prodotto1.setCod(1);
        prodotto1.setNome("Nexus4");
        prodotto1.setTipologia("Smartphone");
        prodotto1.setPezzi(12);
        prodotto1.setPrezzo(200.00);
        prodotto1.setImgurl("img/nexus4.png");
        listaProdotti.add(prodotto1);
        
        Prodotto prodotto2 = new Prodotto();
        prodotto2.setCod(2);
        prodotto2.setNome("Nexus5");
        prodotto2.setTipologia("Smartphone");
        prodotto2.setPezzi(93);
        prodotto2.setPrezzo(250.00);
        prodotto2.setImgurl("img/nexus5.png");
        listaProdotti.add(prodotto2);
        
        Prodotto prodotto3 = new Prodotto();
        prodotto3.setCod(3);
        prodotto3.setNome("Nexus6");
        prodotto3.setTipologia("Smartphone");
        prodotto3.setPezzi(134);
        prodotto3.setPrezzo(300.00);
        prodotto3.setImgurl("img/nexus6.png");
        listaProdotti.add(prodotto3);
       
        Prodotto prodotto4 = new Prodotto();
        prodotto4.setCod(4);
        prodotto4.setNome("Nexus5X");
        prodotto4.setTipologia("Smartphone");
        prodotto4.setPezzi(350);
        prodotto4.setPrezzo(400.00);
        prodotto4.setImgurl("img/nexus5x.png");
        listaProdotti.add(prodotto4);
        
        Prodotto prodotto5 = new Prodotto();
        prodotto5.setCod(5);
        prodotto5.setNome("Nexus6P");
        prodotto5.setTipologia("Smartphone");
        prodotto5.setPezzi(450);
        prodotto5.setPrezzo(500.00);
        prodotto5.setImgurl("img/nexus6p.png");
        listaProdotti.add(prodotto5);
        
    // Venditori
        Venditore venditore1 = new Venditore();
        venditore1.setId(1);
        venditore1.setNome("Alessio");
        venditore1.setCognome("Spiga");
        venditore1.setUsername("alessiospiga");
        venditore1.setPassword("1234");
        ArrayList<Prodotto> arrayProdottiVenditore1 = new ArrayList<Prodotto>();
        arrayProdottiVenditore1.add(prodotto1);
        arrayProdottiVenditore1.add(prodotto2);
        venditore1.setProdottiInVendita(arrayProdottiVenditore1);
        listaVenditori.add(venditore1);
        
        Venditore venditore2 = new Venditore();
        venditore2.setId(1);
        venditore2.setNome("Paolo");
        venditore2.setCognome("Corpino");
        venditore2.setUsername("paolocorpino");
        venditore2.setPassword("1234");
        ArrayList<Prodotto> arrayProdottiVenditore2 = new ArrayList<Prodotto>();
        arrayProdottiVenditore2.add(prodotto3);
        arrayProdottiVenditore2.add(prodotto4);
        venditore2.setProdottiInVendita(arrayProdottiVenditore2);
        listaVenditori.add(venditore2);
        
    // Clienti
        Cliente cliente1 = new Cliente();
        cliente1.setId(1);
        cliente1.setNome("Roberto");
        cliente1.setCognome("Spiga");
        cliente1.setUsername("robertospiga");
        cliente1.setPassword("1234");
        SaldoConto saldo1 = new SaldoConto();
        saldo1.setSaldo(50);
        cliente1.setSaldo(saldo1);
        ArrayList<Prodotto> arrayProdottiCliente1 = new ArrayList<Prodotto>();
        arrayProdottiCliente1.add(prodotto1);
        arrayProdottiCliente1.add(prodotto2);
        cliente1.setProdottiAcquistabili(arrayProdottiCliente1);
        listaClienti.add(cliente1);
        
        Cliente cliente2 = new Cliente();
        cliente2.setId(1);
        cliente2.setNome("Nicola");
        cliente2.setCognome("Bissiri");
        cliente2.setUsername("nicolabissiri");
        cliente2.setPassword("1234");
        SaldoConto saldo2 = new SaldoConto();
        saldo2.setSaldo(100);
        cliente2.setSaldo(saldo2);
        ArrayList<Prodotto> arrayProdottiCliente2 = new ArrayList<Prodotto>();
        arrayProdottiCliente2.add(prodotto3);
        arrayProdottiCliente2.add(prodotto5);
        cliente2.setProdottiAcquistabili(arrayProdottiCliente2);
        listaClienti.add(cliente2);
    }
    
    /* Metodi */
    
    public void setConnectionString(String s){
	this.connectionString = s;
    }
    public String getConnectionString(){
	return this.connectionString;
    }
    
       
    public ArrayList<Utente> getClienteList(){
        return listaClienti;
    }
    
    public Utente getCliente(int id){
        for(Utente u : listaClienti)
        {
            if(u.id == id)
                return u;
        }
        
        return null;
    }
    
    public ArrayList<Utente> getVenditoreList(){
        return listaVenditori;
    }
    
    public Utente getVenditore(int id){
        for(Utente u : listaVenditori)
        {
            if(u.id == id)
                return u;
        }
        
        return null;
    }
    
    public ArrayList<Utente> getUserList(){
        ArrayList<Utente> listaUtenti = new ArrayList<Utente>();
        
        listaUtenti.addAll(listaClienti);
        listaUtenti.addAll(listaVenditori);
        
        return listaUtenti;
    }
}