package ppe_matthieu.myapplifraismobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import ppe_matthieu.myapplifraismobile.entity.LigneFraisHorsForfait;

/**
 * Created by Matthieu_2 on 20/03/2017.
 */

public class BDDLigneFraisHorsForfait {

    private int id;
    private int mois;
    private String libelle;
    private int montant;

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "fraisMobile";

    private static final String TABLE_LIGNE_FRAIS_HORS_FORFAIT = "LigneFraisHorsForfait";

    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_MOIS = "Mois";
    private static final int NUM_COL_MOIS = 1;
    private static final String COL_LIBELLE = "Libelle";
    private static final int NUM_COL_LIBELLE = 2;
    private static final String COL_MONTANT = "Montant";
    private static final int NUM_COL_MONTANT = 3;

    private SQLiteDatabase bdd;

    private DBHandler maBaseSQLite;

    public BDDLigneFraisHorsForfait(Context context) {
        //On crée la BDD et sa table
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
    public long addLigneFraisHorsForfait(LigneFraisHorsForfait uneLigneFraisHorsForfait) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ID,uneLigneFraisHorsForfait.getId());
        values.put(COL_MOIS, uneLigneFraisHorsForfait.getMois());
        values.put(COL_LIBELLE, uneLigneFraisHorsForfait.getLibelle());
        values.put(COL_MONTANT, uneLigneFraisHorsForfait.getMontant());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_LIGNE_FRAIS_HORS_FORFAIT, null, values);
    }

    //MàJ de la table
    public int updateLigneFraisHorsForfait(int id, LigneFraisHorsForfait uneLigneFraisHorsForfait){
        //La mise à jour d'un fras dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel frais on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_ID,uneLigneFraisHorsForfait.getId());
        values.put(COL_MOIS, uneLigneFraisHorsForfait.getMois());
        values.put(COL_LIBELLE, uneLigneFraisHorsForfait.getLibelle());
        values.put(COL_MONTANT, uneLigneFraisHorsForfait.getMontant());
        return bdd.update(TABLE_LIGNE_FRAIS_HORS_FORFAIT, values, COL_ID + " = " +id, null);
    }

    public int deleteFraisForfait(int id){
        //Suppression d'un frais de la BDD grâce à l'ID
        return bdd.delete(TABLE_LIGNE_FRAIS_HORS_FORFAIT, COL_ID + " = " +id, null);
    }
}