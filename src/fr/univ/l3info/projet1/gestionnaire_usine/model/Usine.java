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
public class Usine {
    
    private int idUsine;
    private List<Parking> parkings; 
    
    public Usine() {}
    public Usine(int idUsine, List<Parking> parkings ) {
    this.idUsine=idUsine;
    this.parkings=parkings; 
    }

    public void setIdUsine(int idUsine) {
        this.idUsine = idUsine;
    }

    
    
    public int getIdUsine() {
        return idUsine;
    }

    public List<Parking> getParkings() {
        return parkings;
    }

    public void setParkings(List<Parking> parkings) {
        this.parkings = parkings;
    }

    public Usine creerUsine() {
    return this;
    }

    @Override
    public String toString() {
  //      return "L´Usine de id " + idUsine + "de"+ parkings.stream().map(c->c.getPlacesParking()).collect(Collectors.toList()).toString();
     return "L´Usine de id " + idUsine + " " + "a les parkings suivants: \n"+ parkings.toString();
  
    }
    
    
    
    
    
}
