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
public class Modele {

 private int idModele; 
 private String description;
 
 public Modele() {}
 
 public Modele (int idModele, String description) {
  this.idModele=idModele;
  this.description=description;
 }

    public int getIdModele() {
        return idModele;
    }

    public void setIdModele(int idModele) {
        this.idModele = idModele;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.idModele;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Modele other = (Modele) obj;
        if (this.idModele != other.idModele) {
            return false;
        }
        return true;
    }
    
    


 
}
