/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.milestone3.Classi;

/**
 *
 * @author Robi
 */
public class SaldoConto {
    /* Attributi */
    private double saldo;
    
    
    /* Costruttore */
    public SaldoConto()
    {
        saldo = 0;
    }

    /**
     * @return the saldo
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isSufficiente(double prezzo) {
        return prezzo <= this.saldo;
    }
}
