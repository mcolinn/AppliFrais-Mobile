package ppe_matthieu.myapplifraismobile.entity;

/**
 * Created by Matthieu_2 on 20/03/2017.
 */

public class FicheFrais {

    private int id;
    private int idVisiteur;
    private int mois;
    private int nbJustficatifs;
    private boolean montantvalide;
    private int dateModif;

    public FicheFrais(){}

    public FicheFrais(int id, int idVisiteur, int mois, int nbJustficatifs){
        this.id = id;
        this.idVisiteur = idVisiteur;
        this.mois = mois;
        this.nbJustficatifs = nbJustficatifs;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdVisiteur() {
        return idVisiteur;
    }
    public void setIdVisiteur(int idVisiteur) {
        this.idVisiteur = idVisiteur;
    }

    public int getMois() {
        return mois;
    }
    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getDateModif() {
        return dateModif;
    }

    public void setDateModif(int dateModif) {
        this.dateModif = dateModif;
    }

    public boolean isMontantvalide() {
        return montantvalide;
    }

    public void setMontantvalide(boolean montantvalide) {
        this.montantvalide = montantvalide;
    }

    public int getNbJustficatifs() {
        return nbJustficatifs;
    }

    public void setNbJustficatifs(int nbJustficatifs) {
        this.nbJustficatifs = nbJustficatifs;
    }
}
