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


/**
 *
 * @author çpc
 */
class accueilUsineHandler implements HttpHandler {

      public void handle(HttpExchange t) {
        String reponse = "<!DOCTYPE html>" +
                         "<html lang=\"fr\">" +
                         "<head>" +
                         "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"/>" +
                         "</head>" +
                         "<body>" +
                         "<h1>Bienvenue sur la page d'accueil du Gestionnaire d´usine</h1>"+
                         "<p>Veuillez choisir les options suivantes:</p>"+
                                "<p>Consulter les veicules</p>"+
                                   "<form method=\"get\" action=\"http://localhost:8082/ConsulterVeicule\">" +
                        "<br><button>Consulter</button>"+
                        "</form>" +
                
                "<p>Consulter les usines</p>"+
                                   "<form method=\"get\" action=\"http://localhost:8082/ConsulterUsine\">" +
                        "<br><button>Consulter</button>"+
                        "</form>" +
                            
                                 "<p>Créer les nouveaux veicules</p>"+
                "<form method=\"get\" action=\"http://localhost:8082/CreationVeicule\">" +
                        "<br><button>Creer</button>"+
                        "</form>" + 
                                 "<p>Rechercher voiture</p>"+
                                 "<p>Supprimer voiture</p>" + 
                         "<form method=\"get\" action=\"http://localhost/index.php\">" +
                        "<br><button>Retour au Portaitl</button>"+
                        "</form>";

     
        
        // Affichage des données
        reponse += "";
           

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

    
}
