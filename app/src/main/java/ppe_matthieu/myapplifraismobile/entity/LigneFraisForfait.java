package ppe_matthieu.myapplifraismobile.entity;

/**
 * Created by Matthieu_2 on 20/03/2017.
 */

public class LigneFraisForfait {

    private int id;
    private int idVisiteur;
    private int mois;
    private int idFraisForfait;
    private int quantite;

    public LigneFraisForfait(){}

    public LigneFraisForfait(int id, int idVisiteur, int mois, int idFraisForfait, int quantite){
        this.id = id;
        this.idVisiteur = idVisiteur;
        this.mois = mois;
        this.idFraisForfait = idFraisForfait;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getMois() {
        return mois;
    }

    public void setIdVisiteur(int idVisiteur) {
        this.idVisiteur = idVisiteur;
    }

    public int getIdVisiteur() {
        return idVisiteur;
    }

    public void setIdFraisForfait(int idFraisForfait) {
        this.idFraisForfait = idFraisForfait;
    }

    public int getIdFraisForfait() {
        return idFraisForfait;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }
}
