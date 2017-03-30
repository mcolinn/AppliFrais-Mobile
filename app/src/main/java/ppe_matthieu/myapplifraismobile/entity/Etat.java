package ppe_matthieu.myapplifraismobile.entity;

/**
 * Created by Matthieu_2 on 20/03/2017.
 */

public class Etat {

    private int  id;
    private String libelle;

    public Etat(){}

    public Etat(int id, String libelle){
        this.id = id;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
