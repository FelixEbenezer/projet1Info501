/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.l3info.projet1.portail_essai_php;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 * @author çpc
 */
public class ServeurPortailPHP {
    
    public void demarrerPortailPHP() {    
        HttpServer serveur = null;
        try {
            serveur = HttpServer.create(new InetSocketAddress(8080), 0);
        } catch(IOException e) {
            System.err.println("Erreur lors de la création du serveur " + e);
            System.exit(0);
        }

       serveur.createContext("/index", new IndexHandler());
       serveur.createContext("/redirect", new RedirectHandler());
       
        serveur.setExecutor(null);
        serveur.start();

        System.out.println("Serveur Portail a démarré. Pressez CRTL+C pour arrêter.");
        
    }

    
}
