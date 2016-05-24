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
public class Venditore extends Utente {
    /* Attributi */
    private SaldoConto saldo = new SaldoConto();
    /* Attributi */
    protected ArrayList<Prodotto> prodottiInVendita = new ArrayList<Prodotto>();
    /* Costruttore */
    public Venditore(){
        super();
    }
    /* Metodi */

    /**
     * @return the prodottiAquistati
     */
    public ArrayList<Prodotto> getProdottiInVendita() {
        return prodottiInVendita;
    }

    /**
     * @param prodottiInVendita the prodottiInVendita to set
     */
    public void setProdottiInVendita(ArrayList<Prodotto> prodottiInVendita) {
        this.prodottiInVendita = prodottiInVendita;
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
        return "venditore";
    }
    
    
}
