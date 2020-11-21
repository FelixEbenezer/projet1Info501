/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.l3info.projet1.gestionnaire_usine.model;

/**
 *
 * @author çpc
 */
public class Moteur {
    
    private int numMoteur;
    private double puissanceMoteur;
    private Carburation carburant = Carburation.ESSENCE; 
    
    public Moteur () {}
    
    public Moteur (int numMoteur, double puissanceMoteur, Carburation carburant) {
     this.numMoteur=numMoteur;
     this.puissanceMoteur=puissanceMoteur;
     this.carburant=carburant; 
    }

    public int getNumMoteur() {
        return numMoteur;
    }

    public void setNumMoteur(int numMoteur) {
        this.numMoteur = numMoteur;
    }

    public double getPuissanceMoteur() {
        return puissanceMoteur;
    }

    public void setPuissanceMoteur(double puissanceMoteur) {
        this.puissanceMoteur = puissanceMoteur;
    }

    public Carburation getCarburant() {
        return carburant;
    }

    public void setCarburant(Carburation carburant) {
        this.carburant = carburant;
    }
    
    
    
}
