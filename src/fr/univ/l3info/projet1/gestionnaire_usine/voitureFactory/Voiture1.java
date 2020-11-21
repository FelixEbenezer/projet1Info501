/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.l3info.projet1.gestionnaire_usine.voitureFactory;

import fr.univ.l3info.projet1.gestionnaire_usine.model.Carburation;
import fr.univ.l3info.projet1.gestionnaire_usine.model.Modele;
import fr.univ.l3info.projet1.gestionnaire_usine.model.Moteur;
import fr.univ.l3info.projet1.gestionnaire_usine.model.Voiture;

/**
 *
 * @author çpc
 */
public class Voiture1 extends Voiture{
    
    
    final Modele modele1 = new Modele(1, "Twingo");
    final Moteur moteur1 = new Moteur(1, 120.50, Carburation.ESSENCE);
    final Voiture voiture1 = new Voiture (1, "hhdg34", "Bleu", "10/2/2020", moteur1, modele1,
    false, false, false);
    
    @Override
    public Voiture ajouterVoiture() {
        
        return voiture1;     
    }    
         
}
