/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.l3info.projet1.gestionnaire_usine.json;

import fr.univ.l3info.projet1.gestionnaire_usine.model.Usine;

import fr.univ.l3info.projet1.gestionnaire_usine.model.Voiture;
import fr.univ.l3info.projet1.gestionnaire_usine.usineFactory.UsineFactory;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author çpc
 */
public class UsineEcriture {
    
        public void genererUsineJson() throws IOException {
      
        
       UsineFactory usineFactory = new UsineFactory();
       Usine usine1 = null; 
       Usine usine2 = null;
       
       usine1 = usineFactory.getUsine(1);
       usine1 = usine1.creerUsine();
       usine2 =  usineFactory.getUsine(2);
       usine2 = usine2.creerUsine();
       
       //ver a primeira forma de como criei voitures en bas
        
        // Génération du JSON depuis un tableau d'objets
        Usine p[] = { usine1, usine2 };
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
           fs = new FileWriter("usine.json");
        } catch(IOException e) {
            System.err.println("Erreur lors de l'ouverture du fichier '" + "usine.json" + "'.");
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
        
        System.out.println("Le fichier '" + "usine.json" + "' a été généré.");
        System.out.println(usine1);
        System.out.println(usine2);
        System.out.println(objet);
        
        }        

    
}
