/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.l3info.projet1.gestionnaire_usine;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author çpc
 */
class consulterVeiculeHandler implements HttpHandler {

           public void handle(HttpExchange t) {
           
        String reponse = "Voici la liste des veicules produits";
        JSONObject objet = recuperarVeicules(); 
        
        reponse = affichageQuery(reponse, objet.toString(1));            

        // Envoi de l'en-tête Http
        try {
            Headers h = t.getResponseHeaders();
            h.set("Content-Type", "text/html; charset=utf-8");
            t.sendResponseHeaders(200, reponse.getBytes().length);
        } catch(IOException e) {
            System.err.println("Erreur lors de l'envoi de l'en-tête : " + e);
            System.exit(0);
        }

        // Envoi du corps (données HTML)
        try {
            OutputStream os = t.getResponseBody();
            os.write(reponse.getBytes());
            os.close();
        } catch(IOException e) {
            System.err.println("Erreur lors de l'envoi du corps : " + e);
        }
        
           }
        
          private JSONObject recuperarVeicules() throws JSONException {
        //Recuperation des utilisateurs de fichier user.json
        
        // Récupération de la chaîne JSON depuis le fichier
        String json = "";
        try {
            byte[] contenu = Files.readAllBytes(Paths.get("voiture.json"));
            //byte[] contenu = Files.readAllBytes(Paths.get(args[0]));
            json = new String(contenu);
        } catch(IOException e) {
            System.err.println("Erreur lors de la lecture du fichier '");
            System.exit(0);
        }
        // Création d'un objet JSON
        JSONObject objet = new JSONObject(json);
        System.out.println("Contenu JSON : ");
        System.out.println(json);
        return objet;
    }
          
           private String affichageQuery(String reponse, String query) {
        // Affichage des données
        reponse += "<p>Données en POST : ";
        if(query == null)
            reponse += "<b>Aucune</b></p>";
        else {
            try {
                query = URLDecoder.decode(query, "UTF-8");
            } catch(UnsupportedEncodingException e) {
                query = "";
            }
            reponse += "<b>" + query + "</b></p>" + accueilUsine();
        }
        return reponse;
    }
           
           private String accueilUsine() {
        String reponse = "<!DOCTYPE html>" +
                "<html lang=\"fr\">" +
                "<head>" +
                "</head>" + 
              "<body>" +
                "<div id=\"area\">" +
                  "<form method=\"post\" action=\"http://localhost:8082/AccueilGestionUsine\">" +
        
                  "<br><button>Retour Accueil Gestionnaire Usine</button>"+
                  "</form>" +
                  "</div>" +
              "</body>" +
              "</html>";
        return reponse;
    }
  

    }
