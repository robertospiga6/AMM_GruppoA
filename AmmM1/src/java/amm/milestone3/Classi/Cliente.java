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
public class Cliente extends Utente {
    /* Attributi */
    private SaldoConto saldo = new SaldoConto();
    /* Attributi */
    protected ArrayList<Prodotto> prodottiAcquistabili = new ArrayList<Prodotto>();
   
    
    
    /* Costruttore */
    public Cliente(){
        super();
    }
    
    /* Metodi */
    
    /**
     * @return the prodottiAcquistati
     */
    public ArrayList<Prodotto> getProdottiAcquistabili() {
        return prodottiAcquistabili;
    }

    /**
     * @param prodottiAcquistabili the prodottiAcquistabili to set
     */
    public void setProdottiAcquistabili(ArrayList<Prodotto> prodottiAcquistabili) {
        this.prodottiAcquistabili = prodottiAcquistabili;
    }
    
    /**
     * @return saldoConto
     */
    public double getSaldo() {
        return saldo.getSaldo();
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(SaldoConto saldo) {
        this.saldo = saldo;
    }
    
    public String getTipo(){
        return "cliente";
    }
}
