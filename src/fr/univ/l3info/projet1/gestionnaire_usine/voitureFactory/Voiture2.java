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
public class Voiture2 extends Voiture1{
    
    
    final Modele modele2 = new Modele(2, "Peugeot");
    final Moteur moteur2 = new Moteur(2, 120.50, Carburation.DIESELE);
    final Voiture voiture2 = new Voiture (2, "hhdg333", "Jaune", "10/2/2020",  moteur2, modele2,
    true, false, true);
    
    @Override
    public Voiture ajouterVoiture() {
        
        return voiture2;     
    }    
         
}
