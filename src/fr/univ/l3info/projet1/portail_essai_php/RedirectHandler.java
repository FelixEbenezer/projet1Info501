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
import java.net.URI;
import java.net.URLDecoder;

/**
 *
 * @author çpc
 */
class RedirectHandler implements HttpHandler {

    public void handle(HttpExchange t) {
              
        //String reponse = renvoyerResponse();
        String reponse = "Verifiant les identifiants";
        //ici j ai mes données venant de la requete et gardées dans query
        String query = recupererDonneesHttp(t);
        //ici j ai le contenu de mon fichier user.json        
        //j affiche juste pour confirmer ce que j ai reçu do portail  
        reponse = affichageQuery(reponse, query); 
        if (query.equals("username=RESP_USINE"))
        {
        reponse= gestionUsine();
        }
        else
        {
        reponse= gestionConces();
        }
        
       
        
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
        
          System.err.println("Query:" + query);
        System.err.println(query.toString());
        System.err.println("Reponse:" + reponse);
       
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
         
         private String gestionUsine() {
        String reponse = "<!DOCTYPE html>" +
                "<html lang=\"fr\">" +
                "<head>" +
                  "<title>Verifiant_identifiants</title>" +
                "</head>" + 
              "<body>" +
                "<div id=\"area\">" +
                "<h1 id=\"a\">Renvoyant les identifiants verifies</h1>" +
                  "<form method=\"post\" action=\"http://localhost:8082/AccueilGestionUsine\">" +
        
                  "<br><button>Cliquer ici pour etre redirectionné vers la page d´acceuil du gestionnaire d´usine</button>"+
                  "</form>" +
                  "</div>" +
              "</body>" +
              "</html>";
        return reponse;
    }
         
         private String gestionConces() {
        String reponse = "<!DOCTYPE html>" +
                "<html lang=\"fr\">" +
                "<head>" +
                  "<title>Verifiant_identifiants</title>" +
                "</head>" + 
              "<body>" +
                "<div id=\"area\">" +
                "<h1 id=\"a\">Renvoyant les identifiants verifies</h1>" +
                  "<form method=\"post\" action=\"http://localhost:8083/AccueilGestionConces\">" +
        
                  "<br><button>Cliquer ici pour etre redirectionné vers la page d´acceuil du gestionnaire de Concessionnaire</button>"+
                  "</form>" +
                  "</div>" +
              "</body>" +
              "</html>";
        return reponse;
    }

    
}
