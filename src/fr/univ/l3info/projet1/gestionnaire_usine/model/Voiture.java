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
public class Voiture {
    
    private int idVoiture; 
    private String niuVoiture; 
    private String couleurVoiture;
    //j utilise momentanement String pour les dates
    private String dateFabricationVoiture;
    private Moteur numeroMoteur;
    private Modele modeleVoiture;
    private Boolean vitreElectrique = false;
    private Boolean radar = false;
    private Boolean gps = false;
    
    public Voiture () {}
    
    public Voiture (int ideVoiture, String niuVoiture, String couleurVoiture, String dateFabricationVoiture,
            Moteur numeroMoteur, Modele modeleVoiture, Boolean vitreElectrique, Boolean radar, Boolean gps)
    {
        this.idVoiture=ideVoiture;
        this.niuVoiture=niuVoiture;
        this.couleurVoiture=couleurVoiture;
        this.dateFabricationVoiture=dateFabricationVoiture;
        this.numeroMoteur=numeroMoteur;
        this.modeleVoiture=modeleVoiture;
        this.vitreElectrique=vitreElectrique;
        this.radar=radar;
        this.gps=gps;
    }
    
  //  private Set<PlaceParking> placeParkings = new HashSet<>();
 //   private PlaceParking placeParking = new PlaceParking();
    
    public int getIdVoiture() {
        return idVoiture;
    }

    public void setIdVoiture(int idVoiture) {
        this.idVoiture = idVoiture;
    }

    public String getNiuVoiture() {
        return niuVoiture;
    }

    public void setNiuVoiture(String niuVoiture) {
        this.niuVoiture = niuVoiture;
    }

    public String getCouleurVoiture() {
        return couleurVoiture;
    }

    public void setCouleurVoiture(String couleurVoiture) {
        this.couleurVoiture = couleurVoiture;
    }

    public String getDateFabricationVoiture() {
        return dateFabricationVoiture;
    }

    public void setDateFabricationVoiture(String dateFabricationVoiture) {
        this.dateFabricationVoiture = dateFabricationVoiture;
    }

    public Moteur getNumeroMoteur() {
        return numeroMoteur;
    }

    public void setNumeroMoteur(Moteur numeroMoteur) {
        this.numeroMoteur = numeroMoteur;
    }

    public Modele getModeleVoiture() {
        return modeleVoiture;
    }

    public void setModeleVoiture(Modele modeleVoiture) {
        this.modeleVoiture = modeleVoiture;
    }

    public Boolean getVitreElectrique() {
        return vitreElectrique;
    }

    public void setVitreElectrique(Boolean vitreElectrique) {
        this.vitreElectrique = vitreElectrique;
    }

    public Boolean getRadar() {
        return radar;
    }

    public void setRadar(Boolean radar) {
        this.radar = radar;
    }

    public Boolean getGps() {
        return gps;
    }

    public void setGps(Boolean gps) {
        this.gps = gps;
    }

    /*
    public PlaceParking getPlaceParking() {
        return placeParking;
    }

    public void setPlaceParking(PlaceParking placeParking) {
        this.placeParking = placeParking;
    }*/
    
    
    

    @Override
    public String toString() {
        return "La voiture de id " + idVoiture + "de " + niuVoiture + "de couleur "+ couleurVoiture + "fabriqué le " + dateFabricationVoiture + "numero Moteur " + numeroMoteur.getNumMoteur() + "de modele "+modeleVoiture.getDescription() + "avec les options suivantes :" + 
                vitreElectrique + radar + gps + "";        
    }
    
    public Voiture ajouterVoiture() {
    return this;
    }
    
    
    
    
}
