/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.l3info.projet1.gestionnaire_utilisateur.serveur;

import fr.univ.l3info.projet1.gestionnaire_utilisateur.model.Utilisateur;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author çpc
 */
class InscriptionHandler implements HttpHandler {

    VerifierIdentifiantsUserHandler v = new VerifierIdentifiantsUserHandler();
    public void handle(HttpExchange t) {
        
        String reponse = contenuFormulaire();
        
        String query = recupererDonneesHttp(t);
                
       // Affichage des données
        reponse += "<p>Données en POST : ";
        
        if(query == null)
            reponse += "<b>Aucune</b></p>";
        else {
            try {
                query = URLDecoder.decode(query, "UTF-8");               
               } 
                catch(UnsupportedEncodingException e) {
                query = "";
            }            
            reponse += "<b>" +"Inscrit avec succés!!!!Reconnecte se. " + query + "</b></p>";
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
           decortiquerQuery(query);
//            System.err.println(query);
        
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

    private void sauvergarUser(String utilisateurId, String utilisateurName, String utilisateurPassword) throws NumberFormatException, JSONException {
        //String nomFichierUtilisateur = utilisateurName.concat(UUID.randomUUID().toString());
        // String nomFichierUtilisateur = UUID.randomUUID().toString(); 
        Utilisateur nn = new Utilisateur();
        nn.setIdUtilisateur(Integer.parseInt(utilisateurId));
        nn.setLogin(utilisateurName);
        nn.setPassword(utilisateurPassword);
        // Génération du JSON depuis un tableau d'objets
        
       Utilisateur p[] = { nn };
       
       
            
       //JSONObject objet = new JSONObject();
       JSONObject objet = v.recuperarUtilisateur();
       objet.put("Contacts",p);
        
       
        // Ajout du tableau
        try {
            
            //JSONObject json; // <- O seu JSONObject
        JSONArray array = new JSONArray(); // JSONArray com os objetos
        
      //  for(int i = 1; i <= 100; i++) {
        //array.put(objet.getJSONObject(""+i));
        array.put(objet);
      //  i++;
        
       // }
            
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
        System.out.println(nn);        
    }
    
    private String contenuFormulaire() {
        String reponse = "<!DOCTYPE html>" +
                "<html lang=\"fr\">" +
                "<head>" +
                "<meta http-equiv=\"content-type\" content=\"text/html; href=\"estilo.css\" charset=utf-8\"/>" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\">" +
                "</head>" +
                "<body>" +
                "<div width:420px id=\"area\">" +
                "<h1>Bienvenue sur la page d'INSCRIPTION</h1>" +
                        "<form width:320px id=\"formulario\" method=\"post\" action=\"http://localhost:8081/inscription\">" +
                        "<label>Remplissez le formulaie ci dessous pour s´inscrire</label>" + "</br>" +
                        "<label>IdUser</label><input type=\"number\" min=10 required name=\"idUser\"/>" + "</br>" +
                        "<label>UserName</label><input type=\"text\" required name=\"username\"/>" + "</br>" +
                        "<label>UserPassword</label><input type=\"password\" required name=\"passwordUser\"/>" + "</br>" +
                        "<button>S´inscrire</button>"+ "</br>" +
                        "</form>" + "</br>" +
                        "<form method=\"post\" action=\"http://localhost:8081/inscription\">" +
                        "<button>Annuler</button>"+
                        "</form>" +
                         "<form method=\"post\" action=\"http://localhost/index.php\">" +
                        "<button>Se connecter</button>"+
                        "</form>" +
                 "</div>" +
                 "</body>";
        return reponse;
    }

    
    private JSONArray generateurUtilisateur() throws JSONException {
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
        JSONArray objet = new JSONArray(json);
       // JSONObject objet = new JSONObject(json);
        System.out.println("Contenu JSON : ");
        System.out.println(json);
        return objet;
    }
    
    public void decortiquerQuery(String query)
        //Sauvegarde des données: Prendre les données de query et l envoyer a GenerateurUtilisateur
      //  JSONObject objet = generateurUtilisateur();
        //dans le fichier GenerateurUtilisateur, pour sauvegarder un user, il nous faut:
        //un nom d ojet utilisateur(peut venir du nom de l utilisateur.rand(), id, login, password 
        //venant du query, sur ce , il faut decortiquer d´emblée le query:
         {
                 
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
        sauvergarUser(utilisateurId, utilisateurName, utilisateurPassword);
                }
                

    
    
   

}





/*





 private void sauvergarUser(String utilisateurId, String utilisateurName, String utilisateurPassword) throws NumberFormatException, JSONException {
        //String nomFichierUtilisateur = utilisateurName.concat(UUID.randomUUID().toString());
        // String nomFichierUtilisateur = UUID.randomUUID().toString(); 
        Utilisateur nn = new Utilisateur();
        nn.setIdUtilisateur(Integer.parseInt(utilisateurId));
        nn.setLogin(utilisateurName);
        nn.setPassword(utilisateurPassword);
        // Génération du JSON depuis un tableau d'objets
        
       Utilisateur p[] = { nn };
       
            
       JSONObject objet = new JSONObject();
        // Ajout du tableau
        try {
            objet.put("Contacts", p);
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
            objet.write(fss.write(objet), 3, 0);
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
        System.out.println(nn);        
    }

    private void sauvergarUser(String utilisateurId, String utilisateurName, String utilisateurPassword) throws NumberFormatException, JSONException {
        //String nomFichierUtilisateur = utilisateurName.concat(UUID.randomUUID().toString());
        // String nomFichierUtilisateur = UUID.randomUUID().toString(); 
        Utilisateur nn = new Utilisateur();
        nn.setIdUtilisateur(Integer.parseInt(utilisateurId));
        nn.setLogin(utilisateurName);
        nn.setPassword(utilisateurPassword);
        // Génération du JSON depuis un tableau d'objets
    //    generateur1.genererUser(utilisateurId, utilisateurName, utilisateurPassword);
        
       //  Utilisateur p[] = { nn };
       
            
       // JSONObject objet = new JSONObject();
        JSONArray objet = new JSONArray();
        // Ajout du tableau
        try {
            objet.put((nn));
        } catch(JSONException e) {
            System.err.println("Erreur lors de l'insertion du tableau.");
            System.err.println(e);
            System.exit(0);
        }
     /*   
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
            FileWriter fss = null;
            //objet.write(fss.write(objet), 3, 0);
            fss.write(objet,3,0);
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
        System.out.println(nn);        
    }

*/