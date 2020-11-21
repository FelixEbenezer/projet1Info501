/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.l3info.projet1.gestionnaire_usine.voitureFactory;
import fr.univ.l3info.projet1.gestionnaire_usine.model.Voiture;

/**
 *
 * @author çpc
 */
public class VoitureFactory {
    
  public static final int TYPE_PRODUITA1 = 1;
  public static final int TYPE_PRODUITA2 = 2;

  public Voiture getVoiture (int typeProduit) {
  Voiture voiture = null;
  switch(typeProduit) {
      case TYPE_PRODUITA1:
         voiture = new Voiture1();
          voiture.ajouterVoiture();
          break; 
      case TYPE_PRODUITA2:
         voiture = new Voiture2();
         voiture.ajouterVoiture(); 
          break; 
      default:
        throw new IllegalArgumentException("Type de voiture inconnu");
  }
  return voiture; 
  }
  
}
