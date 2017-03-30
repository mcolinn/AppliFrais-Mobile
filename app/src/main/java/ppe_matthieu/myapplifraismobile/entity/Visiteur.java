package ppe_matthieu.myapplifraismobile.entity;

/**
 * Created by Matthieu_2 on 20/03/2017.
 */

public class Visiteur {

    private int id;

    private String nom;
    private String prenom;
    private String login;
    private String password;

    public Visiteur(){}

    public Visiteur(String nom, String prenom, String login, String password ){
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String libelle) {
        this.prenom = libelle;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
