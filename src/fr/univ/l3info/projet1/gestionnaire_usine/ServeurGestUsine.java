/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.l3info.projet1.gestionnaire_usine;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 * @author çpc
 */
public class ServeurGestUsine {
    
     public void demarrerGestUsine() {    
        HttpServer serveur = null;
        try {
            serveur = HttpServer.create(new InetSocketAddress(8082), 0);
        } catch(IOException e) {
            System.err.println("Erreur lors de la création du serveur " + e);
            System.exit(0);
        }

       serveur.createContext("/AccueilGestionUsine", new accueilUsineHandler());
       serveur.createContext("/ConsulterVeicule", new consulterVeiculeHandler());
       serveur.createContext("/ConsulterUsine", new consulterUsineHandler());
       serveur.createContext("/CreationVeicule", new creerVeiculeHandler());
       serveur.createContext("/RechercheVeicule", new rechercheVeiculeHandler());
       
       
        serveur.setExecutor(null);
        serveur.start();

        System.out.println("Serveur Gestionnaire d´Usine démarré avec succés. Pressez CRTL+C pour arrêter.");
        
    }

    
}
