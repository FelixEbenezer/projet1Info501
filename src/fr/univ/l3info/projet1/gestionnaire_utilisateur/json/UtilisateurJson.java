/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.l3info.projet1.gestionnaire_utilisateur.json;
import fr.univ.l3info.projet1.gestionnaire_utilisateur.model.Role;
import fr.univ.l3info.projet1.gestionnaire_utilisateur.model.Utilisateur;


import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author çpc
 */
public class UtilisateurJson {
    
      //  public static void main(String[] args) throws IOException {
       public void genereruserjson() throws IOException {
        //Utilisateur
        Utilisateur user1 = new Utilisateur(1, "Nganga", "123", Role.RESP_USINE);
        Utilisateur user2 = new Utilisateur(2, "Ben", "123", Role.Concessionnaire);

        // Génération du JSON depuis un tableau d'objets
        Utilisateur p[] = { user1, user2 };
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
           fs = new FileWriter("user.json");
        } catch(IOException e) {
            System.err.println("Erreur lors de l'ouverture du fichier '" + "user.json" + "'.");
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
        
        System.out.println("Le fichier '" + "user.json" + "' a été généré.");
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(p[0]);
        System.out.println(objet);
        
        
        
        }        


    
}
