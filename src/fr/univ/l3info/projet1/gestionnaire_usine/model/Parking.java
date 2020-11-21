/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.l3info.projet1.gestionnaire_usine.model;

import java.util.List;

/**
 *
 * @author çpc
 */
public class Parking {
    
    private int idParking;
    private List<PlaceParking> placesParking;
//    private List<Voiture> voitures; 

    public Parking() {}
    public Parking(int idParking, List<PlaceParking> placesParking) {
    this.idParking=idParking;
    this.placesParking=placesParking;
    }
    
    public int getIdParking() {
        return idParking;
    }

    public void setIdParking(int idParking) {
        this.idParking = idParking;
    }

    public List<PlaceParking> getPlacesParking() {
        return placesParking;
    }

    public void setPlacesParking(List<PlaceParking> placesParking) {
        this.placesParking = placesParking;
    }

  /*  public List<Voiture> getVoitures() {
        return voitures;
    }

    public void setVoitures(List<Voiture> voitures) {
        this.voitures = voitures;
    }

    */

    @Override
    public String toString() {
        return "Le parking de id " + idParking + "avec les places parking " + placesParking.toString();
    }
    
    
    
}
