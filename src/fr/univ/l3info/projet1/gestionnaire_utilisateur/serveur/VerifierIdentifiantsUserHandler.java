/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.l3info.projet1.gestionnaire_utilisateur.serveur;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.univ.l3info.projet1.gestionnaire_utilisateur.model.Role;
import fr.univ.l3info.projet1.gestionnaire_utilisateur.model.Utilisateur;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author çpc
 */
class VerifierIdentifiantsUserHandler implements HttpHandler {

          public void handle(HttpExchange t) throws UnsupportedEncodingException {
              
        //String reponse = renvoyerResponse();
        String reponse = "Verifiant les identifiants";
        //ici j ai mes données venant de la requete et gardées dans query
        String query = recupererDonneesHttp(t);
        //ici j ai le contenu de mon fichier user.json        
        //j affiche juste pour confirmer ce que j ai reçu do portail  
        reponse = affichageQuery(reponse, query);            

        
        //2. Maintenant je dois verifier l existence de l utilisateur et son role donc query vs user.json
        //et apres il me faudra renvoyer la reponse a 8080/index ou via um form com get ou une autre
        //façon 
        
        //2.1.on recupere les utilisateurs contenus en user.json et on le garde dans une variable appele
        //json de type JsonObject, et pour le lire, on le recompose vu que c est un objet json
        
       JSONObject objet = recuperarUtilisateur();
       int idUtilisateur=0;
       String login = null; 
       String password = null; 
       Role role = null; 
       
       JSONArray tableau = objet.getJSONArray("contacts");
       Utilisateur[] p=new Utilisateur[tableau.length()];
       for(int i = 0; i < tableau.length(); i++) {
            JSONObject element = tableau.getJSONObject(i);
            
            idUtilisateur = element.getInt("idUtilisateur");
            login = element.getString("login");
            password = element.getString("password");
            role = element.getEnum(Role.class, "role");
            
            Utilisateur uti = new Utilisateur();
              
            p[i]=uti;
        
  
        
        //2.2. on decortque le contenu du query pour y extraire les valeurs de id, login et password
        //et pouvoir les comparer avec celles de user.json
        
       // reponse += "<p>Reponse renvoyée est : ";
        reponse += "";
        
        if(query == null)
            reponse += "<b>Aucune</b></p>";
        else {
            
                query = URLDecoder.decode(query, "UTF-8");
                
                String utilisateurId = null; 
                String utilisateurName = null;
                String utilisateurPassword = null;
                
                Scanner scanner = new Scanner(query);
                scanner.useDelimiter("&");
              //  Pattern pattern = Pattern.compile("=\\w+&");
             //   scanner.useDelimiter(pattern);
                while (scanner.hasNext())
                {
                
                         String userId = scanner.next();
                         String username = scanner.next();
                         String passwordUser = scanner.next();
                         
                         Scanner scanner1 = new Scanner(userId);
                         scanner1.useDelimiter("=");
                         String us = scanner1.next();
                         utilisateurId = scanner1.next();
                         System.out.println("UserID: "+utilisateurId);
                         
                         Scanner scannerusername = new Scanner(username);
                         scannerusername.useDelimiter("=");
                         String userna1 = scannerusername.next();
                         utilisateurName = scannerusername.next();
                         
                        System.out.println("UserName: "+utilisateurName);
                         
                         Scanner scannerpassword = new Scanner(passwordUser);
                         scannerpassword.useDelimiter("=");
                         String pass1 = scannerpassword.next();
                         utilisateurPassword = scannerpassword.next();
                         
                        System.out.println("Password: "+utilisateurPassword);
                        
                }
             
                 
               /* if(utilisateurId.equals(utilisateurId) && utilisateurName.equals(login) && utilisateurPassword.equals(password) ) 
                
                {
                   reponse += "<b>" + utilisateurName+ "  " + "est" + " " + role + "</b></p>"; 
                   if (null!=role)
                    switch (role) {
                        case Concessionnaire:
                            reponse=renvoyerConces();
                            break;
                        case RESP_USINE:
                            reponse=renvoyerUsine();
                            break;
                        
                    }
                }
                else {
                reponse = "Ressayer svp";
                }
                
                 */
               
                    if(utilisateurId.equals(utilisateurId) && utilisateurName.equals(login) && 
                            utilisateurPassword.equals(password) ) 
                    {

                            if( role==Role.RESP_USINE)
                            {reponse = renvoyerConces();
                            break;}
                            else if( role==Role.Concessionnaire)
                            {
                            reponse = renvoyerUsine();
                            break;
                            }
                        }

                    else
                    {
                     reponse = ressayer(); 
                    }    

                
               } 
                         
        }

 
        
        // Envoi de l'en-tête Http
        try {
            Headers h = t.getResponseHeaders();
            h.set("Content-Type", "text/html; charset=utf-8");
           // h.set(reponse, "http://localhost:8080/index");
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
          
          
          
          
          
          
       public JSONObject recuperarUtilisateur() throws JSONException {
        //Recuperation des utilisateurs de fichier user.json
        
        // Récupération de la chaîne JSON depuis le fichier
        String json = "";
        try {
            byte[] contenu = Files.readAllBytes(Paths.get("user.json"));
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
         
         private String ressayer() {
        String reponse = "<!DOCTYPE html>" +
                "<html lang=\"fr\">" +
                "<head>" +
                "</head>" + 
              "<body>" +
                "<div id=\"area\">" +
                "<h1 id=\"a\">Utilisateur inexistant, veuillez reesayer SVP</h1>" +
                  "<form method=\"post\" action=\"http://localhost/index.php\">" +
                        "<br><button>Se Reconnecter</button>"+
                        "</form>" +
                  "</div>" +
              "</body>" +
              "</html>";
        return reponse;
    }
         //"<form method=\"post\" action=\"http://localhost/redirect.php\">" +
         //"<form method=\"post\" action=\"http://localhost:8080/redirect\">" +
          private String renvoyerUsine() {
        String reponse = "<!DOCTYPE html>" +
                "<html lang=\"fr\">" +
                "<head>" +
                  "<title>Verifiant_identifiants</title>" +
                "</head>" + 
              "<body>" +
                "<div id=\"area\">" +
                "<h1 id=\"a\">Renvoyant les identifiants verifies</h1>" +
                  "<form method=\"post\" action=\"http://localhost/redirect.php\">" +
          
                   "<label>Utilisateur retourné est:</label><input  class=\"campo_email\" value=\"RESP_USINE\" type=\"text\" readonly name=\"username\"><br><br>" +
                      
                  "<br><button>Continuer</button>"+
                  "</form>" +
                  "</div>" +
              "</body>" +
              "</html>";
        return reponse;
    }
          
              private String renvoyerConces() {
        String reponse = "<!DOCTYPE html>" +
                "<html lang=\"fr\">" +
                "<head>" +
                  "<title>Verifiant_identifiants</title>" +
                "</head>" + 
              "<body>" +
                "<div id=\"area\">" +
                "<h1 id=\"a\">Renvoyant les identifiants verifies</h1>" +
                  "<form method=\"post\" action=\"http://localhost/redirect.php\">" +
          
                   "<label>Utilisateur retourné est:</label><input  class=\"campo_email\" value=\"CONCESSIONNAIRE\" type=\"text\" readonly name=\"username\"><br><br>" +
                      
                  "<br><button>Continuer</button>"+
                  "</form>" +
                  "</div>" +
              "</body>" +
              "</html>";
        return reponse;
    }



    
}


   /*           try {
                  // Mise en forme de l'URL
                  
  
                reponse += URLEncoder. encode("titre", "UTF-8") + "=" + URLEncoder. encode("donnees", "UTF-8");
              } catch (UnsupportedEncodingException ex) {
                  Logger.getLogger(VerifierIdentifiantsUserHandler.class.getName()).log(Level.SEVERE, null, ex);
              }
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
        
        // Envoi de la requête
        try {
            OutputStreamWriter writer = new OutputStreamWriter(connexion.getOutputStream());
            writer.write(reponse);
            writer.flush();
            writer.close();
        } catch(IOException e) {
            System.err.println("Erreur lors de l'envoi de la requete : " + e);
            System.exit(0);            
        }        

  */
       