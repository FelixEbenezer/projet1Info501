/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.l3info.projet1.gestionnaire_usine.usineFactory;

import fr.univ.l3info.projet1.gestionnaire_usine.model.Voiture;
import fr.univ.l3info.projet1.gestionnaire_usine.model.Parking;
import fr.univ.l3info.projet1.gestionnaire_usine.model.PlaceParking;
import fr.univ.l3info.projet1.gestionnaire_usine.model.Usine;
import fr.univ.l3info.projet1.gestionnaire_usine.voitureFactory.VoitureFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author çpc
 */
public class Usine1 extends Usine{
    
              
    
    @Override
    public Usine creerUsine() {
       
        VoitureFactory voitureFactory = new VoitureFactory();
       Voiture voiture1 = voitureFactory.getVoiture(1); 
       Voiture voiture2 = voitureFactory.getVoiture(2);
       
       
       final PlaceParking place1 = new PlaceParking(1, "A", voiture1.ajouterVoiture());
       final PlaceParking place2 = new PlaceParking(2, "B", voiture2.ajouterVoiture());
       
       final List<PlaceParking> placesParking = new ArrayList<>();
       
       placesParking.add(place1);
       placesParking.add(place2);
       
       
       final Parking parking1 = new Parking(1, placesParking);
       final Parking parking2 = new Parking(2, placesParking);
       
       final List<Parking> parkings = new ArrayList<>();
       parkings.add(parking1);
       parkings.add(parking2);
       
       final Usine usine1 = new Usine(1,parkings );

        return usine1;     
    }    
         
}
