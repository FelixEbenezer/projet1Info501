/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.l3info.projet1.gestionnaire_usine.json;

import fr.univ.l3info.projet1.gestionnaire_usine.voitureFactory.VoitureFactory;

import fr.univ.l3info.projet1.gestionnaire_usine.model.Voiture;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author çpc
 */
public class VoitureEcriture {
    
        public void generervoiturejson() throws IOException {
      
        
       VoitureFactory voitureFactory = new VoitureFactory();
       Voiture voiture1 = null; 
       Voiture voiture2 = null;
       
       voiture1 = voitureFactory.getVoiture(1);
       voiture1 = voiture1.ajouterVoiture();
       voiture2 =  voitureFactory.getVoiture(2);
       voiture2 = voiture2.ajouterVoiture();
       
       //ver a primeira forma de como criei voitures en bas
        
        // Génération du JSON depuis un tableau d'objets
        Voiture p[] = { voiture1, voiture2 };
        JSONObject objet = new JSONObject();
        
        // Ajout du tableau
        try {
            objet.put("contacts", new JSONArray(p));
        } catch(JSONException e) {
            System.err.println("Erreur lors de l'insertion du tableau.");
            System.err.println(e);
            System.exit(0);
        }
        
        // Création du fichier de sortie
        FileWriter fs = null;
        try {
           // fs = new FileWriter(new File("livre.json"));
           fs = new FileWriter("voiture.json");
        } catch(IOException e) {
            System.err.println("Erreur lors de l'ouverture du fichier '" + "voiture.json" + "'.");
            System.err.println(e);
            System.exit(0);
        }
        
        // Sauvegarde dans le fichier
        try {
            objet.write(fs, 3, 0);
            fs.flush();
        } catch(IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier.");
            System.err.println(e);
            System.exit(0);
        }
        
        // Fermeture du fichier
        try {
            fs.close();
        } catch(IOException e) {
            System.err.println("Erreur lors de la fermeture du fichier.");
            System.err.println(e);
            System.exit(0);
        }
        
        System.out.println("Le fichier '" + "voiture.json" + "' a été généré.");
        System.out.println(voiture1);
        System.out.println(voiture2);
        System.out.println(objet);
        
        }        

    
}
