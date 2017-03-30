package ppe_matthieu.myapplifraismobile.entity;

/**
 * Created by Matthieu_2 on 20/03/2017.
 */

public class FraisForfait {

    private int id;
    private String libelle;
    private int montant;

    public FraisForfait(){}

    public FraisForfait(int id, String libelle, int montant){
        this.id = id;
        this.libelle = libelle;
        this.montant = montant;
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

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
}
