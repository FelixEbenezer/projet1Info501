/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.l3info.projet1.portail_essai_php;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

/**
 *
 * @author çpc
 */
public class IndexHandler implements HttpHandler{

       public void handle(HttpExchange t) {
           
        String reponse = contenuFormulaire1();
    //    String query = recupererDonneesHttp(t);
     
        //comment les données sont envoyées vers 8081/verifiant, pas necessaire de l afficher
       // reponse = affichageQuery(reponse, query);            

       
   /*    
       URL url = null;
        try { 
            url = new URL("http://localhost:8080/index"); 
        } catch(MalformedURLException e) { 
            System.err.println("URL incorrect : " + e);
            System.exit(0);
        }
        
        // Etablissement de la connexion
        URLConnection connexion = null; 
        try { 
            connexion = url.openConnection(); 
            connexion.setDoOutput(true);
        } catch(IOException e) { 
            System.err.println("Connexion impossible : " + e);
            System.exit(0);
        } 
       
         String donnees = "";
         try {
         BufferedReader reader = new BufferedReader(new InputStreamReader(connexion. getInputStream()));
         String tmp;
         while((tmp = reader. readLine()) != null)
         donnees += tmp;
         reader. close();
         } catch(Exception e) {
         System. err. println("Erreur lors de la lecture de la réponse : " + e);
         System. exit(0);
         }
     
*/

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

    private String affichageQuery(String reponse, String query) {
        // Affichage des données
        reponse += "";
        if(query == null)
            reponse += "<b>Aucune</b></p>";
        else {
            try {
                query = URLDecoder.decode(query, "UTF-8");
            } catch(UnsupportedEncodingException e) {
                query = "";
            }
            reponse += "<b>" + query + "</b></p>";
        }
        return reponse;
    }
       
         private String recupererDonneesHttp(HttpExchange t) {
        // Récupération des données
        URI requestedUri = t.getRequestURI();
        String query = requestedUri.getRawQuery();
        // Utilisation d'un flux pour lire les données du message Http
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(t.getRequestBody(),"utf-8"));
        } catch(UnsupportedEncodingException e) {
            System.err.println("Erreur lors de la récupération du flux " + e);
            System.exit(0);
        }
        // Récupération des données en POST
        try {
            query = br.readLine();
        } catch(IOException e) {
            System.err.println("Erreur lors de la lecture d'une ligne " + e);
            System.exit(0);
        }
        return query;
    }

     
      private String contenuFormulaire1() {
        String reponse = "<!DOCTYPE html>" +
                "<html lang=\"fr\">" +
                "<head>" +
                  "<title>Portail</title>" +
                  "<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\">" +
                  "<link rel=\"stylesheet\" type=\"text/css\" href=\"estiloie.css\">" +
                "</head>" + 
              "<body>" +
                "<div id=\"area\">" +
                "<h1 id=\"a\">Bienvenu sur le Portail de Gestion de Construction Auto - LOGIN</h1>" +
                  "<form id=\"formulario\" autocomplete=\"off\" method=\"post\" action=\"http://localhost:8081/verifiant\">" + "</br>"+
                    "<label>Remplissez le formulaie ci dessous pour se connecter</label>" + "</br><br>" +
                    "<fieldset>" +
                      "<legend>Formulaire de LOGIN</legend>" +
                      "<label>IdUser_________:</label><input class=\"campo_nome\" type=\"number\" required name=\"idUser\"><br><br>" +
                      "<label>Email/Login____:</label><input class=\"campo_email\" type=\"text\" required name=\"username\"><br><br>" +
                      "<label>Password______.:</label><input class=\"campo_email\" type=\"password\" required name=\"username\"><br><br>" +
                      "<input class=\"btn_submit\" type=\"submit\" value=\"Se connecter\"><br>" +
                    "</fieldset>" +
                  "</form>" +
                        "<form method=\"post\" action=\"http://localhost:8080/index\">" +
                        "<br><button>Annuler</button>"+
                        "</form>" +
                "</div>" +
              "</body>" +
              "</html>";
        return reponse;
    }

    
}
