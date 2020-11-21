/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ.l3info.projet1.gestionnaire_utilisateur.model;

/**
 *
 * @author çpc
 */

public class Utilisateur {

private int idUtilisateur;
private String login;
private String password; 
private Role role = Role.Concessionnaire;

public Utilisateur() {}

public Utilisateur(int idUtilisateur, String login, String password, Role role) {
this.idUtilisateur=idUtilisateur;
this.login=login;
this.password=password;
this.role=role;
}

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    

    @Override
    public String toString() {
        return "L utilisateur de id: "+ idUtilisateur + " " +"a comme nom: " + login + " " + "a le role de : " + role;
    }
    
    


}
