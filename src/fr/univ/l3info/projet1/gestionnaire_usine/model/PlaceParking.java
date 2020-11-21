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
public class PlaceParking {

private int numRange;
private String range;
private Voiture voiture; 

public PlaceParking() {}

public PlaceParking(int numRange, String range, Voiture voiture) {
    this.numRange=numRange;
    this.range=range;
    this.voiture=voiture; 
}
//private List<Voiture> voitures = new ArrayList<>();

    public int getNumRange() {
        return numRange;
    }

    public void setNumRange(int numRange) {
        this.numRange = numRange;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
    
    

 /*   public List<Voiture> getVoitures() {
        return voitures;
    }

    public void setVoitures(List<Voiture> voitures) {
        this.voitures = voitures;
    }
*/

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    @Override
    public String toString() {
        return "La place parking numero " + numRange + "de " + range +"a la " + voiture.getNiuVoiture();
    }
    
    
    

}
