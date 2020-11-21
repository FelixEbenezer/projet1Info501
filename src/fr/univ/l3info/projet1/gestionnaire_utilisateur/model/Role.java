/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.l3info.projet1.gestionnaire_utilisateur.model;

/**
 *
 * @author çpc
 */
public enum Role {
   
    
    RESP_USINE("Responsable_usine"),
    Concessionnaire("hybride");
    
    final String description; 
    
    Role(String description) {
    this.description = description;
    }

    public String getDescription() {
        return description;
    }

    
    
   




}