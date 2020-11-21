/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.l3info.projet1.gestionnaire_usine;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.univ.l3info.projet1.gestionnaire_usine.model.Modele;
import fr.univ.l3info.projet1.gestionnaire_usine.model.Moteur;
import fr.univ.l3info.projet1.gestionnaire_usine.model.Voiture;
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
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author çpc
 */
class creerVeiculeHandler implements HttpHandler {

 
   
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
            reponse += "<b>" + query + "</b></p>";
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
            String nn = decortiquerQuery(query);
//            System.err.println(query);
            System.err.println(query);
            System.err.println(reponse);
            System.err.println(nn);
    }

    private void sauvergarVoiture(int ideVoiture, String niuVoiture, String couleurVoiture, String dateFabricationVoiture,
            Moteur numeroMoteur, Modele modeleVoiture, Boolean vitreElectrique, Boolean radar, Boolean gps) 
            throws NumberFormatException, JSONException {
        //String nomFichierUtilisateur = utilisateurName.concat(UUID.randomUUID().toString());
        // String nomFichierUtilisateur = UUID.randomUUID().toString(); 
        Voiture nn = new Voiture(ideVoiture, niuVoiture, couleurVoiture, dateFabricationVoiture, numeroMoteur, modeleVoiture, vitreElectrique, radar, gps);
       
        // Génération du JSON depuis un tableau d'objets
    //    generateur1.genererUser(utilisateurId, utilisateurName, utilisateurPassword);
        
         Voiture p[] = { nn };
       
            
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
        System.out.println(nn);
        System.out.println(objet);        
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

   private String contenuFormulaire() {
        String reponse = "<!DOCTYPE html>" +
                "<html lang=\"fr\">" +
                "<head>" +
                  "<title>Portail</title>" +
                  "<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\">" +
                  "<link rel=\"stylesheet\" type=\"text/css\" href=\"estiloie.css\">" +
                "</head>" + 
              "<body>" +
                "<div id=\"area\">" +
                "<h1 id=\"a\">CREATION DE VEICULES</h1>" +
                  "<form id=\"formulario\" autocomplete=\"off\" method=\"post\" action=\"http://localhost:8082/CreationVeicule\">" + "</br>"+
                    "<label>Remplissez le formulaie ci dessous pour creer ta voiture</label>" + "</br><br>" +
                    "<fieldset>" +
                      "<legend>Personnalisation Voiture</legend>" +
                      "<label>Couleur_________:</label><input class=\"campo_nome\" type=\"text\" required name=\"couleur\"><br><br>" +
                     "<label>NIU__________:</label><input class=\"campo_nome\" type=\"text\" required name=\"niu\"><br><br>" +
                      
                    "<label>Radar____:</label>" +
                        "<input type=\"radio\" name=\"radar\" id=\"size_1\" value=false>" +
                        "<label for=\"size_1\">Oui</label>"+
                        "<input type=\"radio\" name=\"radar\" id=\"size_1\" value=true>" +
                        "<label for=\"size_1\">Non</label>"+"<br><br>" +
                       
                        "<label>GPS____:</label>" +
                        "<input type=\"radio\" name=\"gps\" id=\"size_1\" value=false>" +
                        "<label for=\"size_1\">Oui</label>"+
                        "<input type=\"radio\" name=\"gps\" id=\"size_1\" value=true>" +
                        "<label for=\"size_1\">Non</label>"+"<br><br>" +
                
                        "<label>Vitre electrique____:</label>" +
                        "<input type=\"radio\" name=\"vitre\" id=\"size_1\" value=0>" +
                        "<label for=\"size_1\">Oui</label>"+
                        "<input type=\"radio\" name=\"vitre\" id=\"size_1\" value=1>" +
                        "<label for=\"size_1\">Non</label>"+"<br><br>" +
                
                          "<p>" + 
                          "<label for=\"NumeroMoteur\">" + 
                            "<span>Choisir un numero:</span>" + 
                          "</label>" + 
                          "<select id=\"card\" name=\"moteur\">" +
                            "<option value=\"1 - ESSENCE\">1 - ESSENCE</option>" +
                            "<option value=\"2 - DIESEL\">2 - DIESEL</option>" +
                          "</select>" + 
                        "</p>" + 
                
                        "<p>" + 
                          "<label for=\"Modele\">" + 
                            "<span>Choisir un modele:</span>" + 
                          "</label>" + 
                          "<select id=\"card\" name=\"modele\">" +
                            "<option value=\"1 - TWINGO\">1 - TWINGO</option>" +
                            "<option value=\"2 - PEUGEOT\">2 - PEUGEOT</option>" +
                          "</select>" + 
                        "</p>" + 
                
                
                        "<label>IdVoiture______.:</label><input class=\"campo_email\" type=\"number\" min=\"10\" required name=\"idVoiture\"><br><br>" +
                        "<label>Date Fabrique______.:</label><input class=\"campo_email\" type=\"date\" required name=\"fateFabrique\"><br><br>" +
                        
                    "<input class=\"btn_submit\" type=\"submit\" value=\"Creer\"><br>" +
                    "</fieldset>" +
                  "</form>" +
                        "<form method=\"post\" action=\"http://localhost:8082/AccueilGestionUsine\">" +
                        "<br><button>Annuler</button>"+
                        "</form>" +
                "</div>" +
              "</body>" +
              "</html>";
        return reponse;
    }

    
    private JSONObject generateurVoiture() throws JSONException {
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
    
    public String decortiquerQuery(String query)
        //Sauvegarde des données: Prendre les données de query et l envoyer a GenerateurUtilisateur
      //  JSONObject objet = generateurUtilisateur();
        //dans le fichier GenerateurUtilisateur, pour sauvegarder un user, il nous faut:
        //un nom d ojet utilisateur(peut venir du nom de l utilisateur.rand(), id, login, password 
        //venant du query, sur ce , il faut decortiquer d´emblée le query:
         {
                Moteur m = new Moteur();
                Modele mo = new Modele ();
                
                String voitureId = null; 
                String voitureNiu = null;
                String voitureCouleur = null;
                String voitureDateFabrication = null;
                String voitureMoteur = null;
                String voitureModele = null;
                Boolean voitureVitre = false;
                Boolean voitureRadar = false;
                Boolean voitureGPS = false;
     /*couleur=knklnm
     &niu=kkll
     &radar=small
     &gps=small
     &vitre=small
     &moteur=1 - ESSENCE
     &modele=1 - TWINGO
     &idVoiture=9
     &fateFabrique=2020-10-30           
       */         
                Scanner scanner = new Scanner(query);
                scanner.useDelimiter("&");
              //  Pattern pattern = Pattern.compile("=\\w+&");
             //   scanner.useDelimiter(pattern);
                while (scanner.hasNext())
                {
                        
                         String vId = scanner.next();
                         String vNiu = scanner.next();
                         String vCou = scanner.next();
                         String vDate = scanner.next();
                         String vMot = scanner.next();
                         String vMod = scanner.next();
                         String vVit = scanner.next();
                         String vRad = scanner.next();
                         String vGps = scanner.next();
                         
                        //voitureId
                         Scanner scanner1 = new Scanner(vId);
                         scanner1.useDelimiter("=");
                         String vid1 = scanner1.next();
                         voitureId = scanner1.next();
         
                         //voitureNiu
                         Scanner scannerusername = new Scanner(vNiu);
                         scannerusername.useDelimiter("=");
                         String vNiu1 = scannerusername.next();
                         voitureNiu = scannerusername.next();
                         
                         
                         //voitureCou
                         Scanner scanner3 = new Scanner(vCou);
                         scanner3.useDelimiter("=");
                         String vCou1 = scanner3.next();
                         voitureCouleur = scanner3.next();
                         //voitureDate
                         Scanner scanner4 = new Scanner(vDate);
                         scanner4.useDelimiter("=");
                         String vDate1 = scanner4.next();
                         voitureDateFabrication = scanner4.next();
                         //voitureMot
                         Scanner scanner5 = new Scanner(vMot);
                         scanner5.useDelimiter("=");
                         String vMot1 = scanner5.next();
                         voitureMoteur = scanner5.next();
                         //voitureMod
                         Scanner scanner6 = new Scanner(vMod);
                         scanner3.useDelimiter("=");
                         String vMod1 = scanner6.next();
                         voitureModele = scanner6.next();
                         
                         //voitureVit
                         Scanner scanner7 = new Scanner(vVit);
                         scanner7.useDelimiter("=");
                         String vVit1 = scanner7.next();
                         voitureVitre = scanner7.nextBoolean();
                         //voitureRad
                         Scanner scanner8 = new Scanner(vRad);
                         scanner8.useDelimiter("=");
                         String vRad1 = scanner8.next();
                         voitureRadar = scanner8.nextBoolean();
                         //voitureGps
                         Scanner scanner9 = new Scanner(vGps);
                         scanner9.useDelimiter("=");
                         String vGps1 = scanner9.next();
                         voitureGPS = scanner9.nextBoolean();
                         
                         m.setNumMoteur(Integer.parseInt(voitureMoteur));
                         mo.setIdModele(Integer.parseInt(voitureModele));
                         
                }
             sauvergarVoiture(Integer.parseInt(voitureId), voitureNiu, voitureCouleur, voitureDateFabrication, m, mo, voitureVitre, voitureRadar, voitureGPS);
             return query;
    }
                
 
    
    
   

}

