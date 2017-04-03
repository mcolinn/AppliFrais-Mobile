package ppe_matthieu.myapplifraismobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import ppe_matthieu.myapplifraismobile.entity.LigneFraisForfait;

/**
 * Created by Matthieu_2 on 20/03/2017.
 */

public class BDDLigneFraisForfait {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "fraisMobile";

    private static final String TABLE_LIGNE_FRAIS_FORFAIT = "LigneFraisForfait";

    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_ID_VISITEUR = "IdVisiteur";
    private static final int NUM_COL_ID_VISITEUR = 1;
    private static final String COL_MOIS = "Mois";
    private static final int NUM_COL_MOIS = 2;
    private static final String COL_ID_FRAIS_FORFAIT = "IdFraisForfait";
    private static final int NUM_COL_ID_FRAIS_FORFAIT = 3;
    private static final String COL_QUANTITE = "Quantite";
    private static final int NUM_COL_QUANTITE = 4;

    private SQLiteDatabase bdd;

    private DBHandler maBaseSQLite;

    public BDDLigneFraisForfait(Context context) {
        //On crée la BDD et sa table
//        maBaseSQLite = new DBHandler(context, NOM_BDD, null, VERSION_BDD);
        maBaseSQLite = new DBHandler(context);
    }

    public void open() {
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close() {
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD() {
        return bdd;
    }

    //Ajout dans la table
    public long addLigneFraisForfait(LigneFraisForfait uneLigneFraisForfait) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ID, uneLigneFraisForfait.getId());
        values.put(COL_ID_VISITEUR, uneLigneFraisForfait.getIdVisiteur());
        values.put(COL_ID_FRAIS_FORFAIT, uneLigneFraisForfait.getIdFraisForfait());
        values.put(COL_MOIS, uneLigneFraisForfait.getMois());
        values.put(COL_QUANTITE, uneLigneFraisForfait.getQuantite());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_LIGNE_FRAIS_FORFAIT, null, values);
    }

    //MàJ de la table
    public int updateLigneFraisForfait(int id, LigneFraisForfait uneLigneFraisForfait){
        //La mise à jour d'un fras dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel frais on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_ID, uneLigneFraisForfait.getId());
        values.put(COL_ID_VISITEUR, uneLigneFraisForfait.getIdVisiteur());
        values.put(COL_ID_FRAIS_FORFAIT, uneLigneFraisForfait.getIdFraisForfait());
        values.put(COL_MOIS, uneLigneFraisForfait.getMois());
        values.put(COL_QUANTITE, uneLigneFraisForfait.getQuantite());
        return bdd.update(TABLE_LIGNE_FRAIS_FORFAIT, values, COL_ID + " = " +id, null);
    }

    public int deleteLigneFraisForfait(int id){
        //Suppression d'un frais de la BDD grâce à l'ID
        return bdd.delete(TABLE_LIGNE_FRAIS_FORFAIT, COL_ID + " = " +id, null);
    }
}