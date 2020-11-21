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
public enum Carburation {
   
    
    ESSENCE("Essence"),
    DIESELE("Diesel"),
    GPL("Gpl"),
    HYBRIDE("hybride");
    
    final String description; 
    
    Carburation(String description) {
    this.description = description;
    }

    public String getDescription() {
        return description;
    }

    
    
   




}