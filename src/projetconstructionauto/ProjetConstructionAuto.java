/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetconstructionauto;

import fr.univ.l3info.projet1.gestionnaire_usine.ServeurGestUsine;
import fr.univ.l3info.projet1.gestionnaire_utilisateur.json.UtilisateurJson;
import fr.univ.l3info.projet1.gestionnaire_utilisateur.serveur.ServeurGestUtil;
import fr.univ.l3info.projet1.portail_essai_php.ServeurPortailPHP;
import fr.univ.l3info.projet1.concessionnaire_essai_php.ServeurConcessionnaire;
import fr.univ.l3info.projet1.gestionnaire_usine.json.UsineEcriture;
import fr.univ.l3info.projet1.gestionnaire_usine.json.VoitureEcriture;

/**
 *
 * @author çpc
 */
public class ProjetConstructionAuto {

    /**
     * @param args the command line arguments
     */
    
    static UtilisateurJson u = new UtilisateurJson();
    static VoitureEcriture v = new VoitureEcriture();
    static UsineEcriture ue = new UsineEcriture();
    private static  ServeurGestUtil sgu = new ServeurGestUtil();
    static ServeurPortailPHP spp = new ServeurPortailPHP();
    private static ServeurGestUsine sgusine = new ServeurGestUsine(); 
    private static ServeurConcessionnaire sc = new ServeurConcessionnaire();
    
    public static void main(String[] args) {
        // TODO code application logic here
        //Est ce possible en executant cette classe, que cela demarre tous les serveurs ??????
        
        try {
        u.genereruserjson(); 
        v.generervoiturejson();
        ue.genererUsineJson();
        sgu.demarrerGestUtil();
        sgusine.demarrerGestUsine();
        sc.demarrerGestConces(); 
     //   spp.demarrerPortailPHP();
        
        
        
        } catch (Exception e) {
        }
        
    }
    
}
