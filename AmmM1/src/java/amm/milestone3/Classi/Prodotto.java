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
public class Prodotto {
    /* Attributi */
    private Integer cod;
    private String nome;
    private String tipologia;
    private Integer pezzi;
    private double prezzo;
    private String imgurl;
    
    
    /* Costruttore */
    public Prodotto()
    {
        cod = 0;
        nome = "";
        tipologia = "";
        pezzi= 0;
        prezzo= 0;
        imgurl="";
    }

    /**
     * @return the cod
     */
    public Integer getCod() {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(int cod) {
        this.cod = cod;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the tipologia
     */
    public String getTipologia() {
        return tipologia;
    }

    /**
     * @param tipologia the tipologia to set
     */
    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    /**
     * @return the pezzi
     */
    public Integer getPezzi() {
        return pezzi;
    }

    /**
     * @param pezzi the pezzi to set
     */
    public void setPezzi(int pezzi) {
        this.pezzi = pezzi;
    }

    /**
     * @return the prezzo
     */
    public double getPrezzo() {
        return prezzo;
    }

    /**
     * @param prezzo the prezzo to set
     */
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
    
        /**
     * @return the imgurl
     */
    public String getImgurl() {
        return imgurl;
    }

    /**
     * @param imgurl the imgurl to set
     */
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
