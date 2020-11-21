/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.l3info.projet1.gestionnaire_utilisateur.serveur;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 * @author çpc
 */
public class ServeurGestUtil {
    
    public void demarrerGestUtil() {    
        HttpServer serveur = null;
        try {
            serveur = HttpServer.create(new InetSocketAddress(8081), 0);
        } catch(IOException e) {
            System.err.println("Erreur lors de la création du serveur " + e);
            System.exit(0);
        }

       serveur.createContext("/verifiant", new VerifierIdentifiantsUserHandler());
       serveur.createContext("/inscription", new InscriptionHandler());
       
        serveur.setExecutor(null);
        serveur.start();

        System.out.println("Serveur Gestionnaire d´Utilisateurs démarré. Pressez CRTL+C pour arrêter.");
        
    }

}
