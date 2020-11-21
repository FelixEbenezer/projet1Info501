/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.l3info.projet1.gestionnaire_usine.usineFactory;
import fr.univ.l3info.projet1.gestionnaire_usine.model.Usine; 


/**
 *
 * @author çpc
 */
public class UsineFactory {
    
  public static final int TYPE_PRODUITA1 = 1;
  public static final int TYPE_PRODUITA2 = 2;

  public Usine getUsine (int typeProduit) {
  Usine usine = null;
  switch(typeProduit) {
      case TYPE_PRODUITA1:
         usine = new Usine1();
         usine.creerUsine();
          break; 
      case TYPE_PRODUITA2:
         usine = new Usine2();
         usine.creerUsine(); 
          break; 
      default:
        throw new IllegalArgumentException("Type de usine inconnu");
  }
  return usine; 
  }
  
}
