package ppe_matthieu.myapplifraismobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Matthieu_2 on 20/03/2017.
 */

public class BDDVisiteur {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "fraisMobile";

    private static final String TABLE_VISITEUR = "Visiteur";

    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NOM = "Nom";
    private static final int NUM_COL_NOM = 1;
    private static final String COL_PRENOM = "Prenom";
    private static final int NUM_COL_PRENOM = 2;
    private static final String COL_LOGIN = "Login";
    private static final int NUM_COL_LOGIN = 3;
    private static final String COL_PASSWORD = "password";
    private static final int NUM_COL_PASSWORD = 4;

    private SQLiteDatabase bdd;

    private DBHandler maBaseSQLite;

    public BDDVisiteur(Context context) {
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
    public long addVisiteur(Visiteur unVisiteur) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ID, unVisiteur.getId());
        values.put(COL_NOM, unVisiteur.getNom());
        values.put(COL_PRENOM, unVisiteur.getPrenom());
        values.put(COL_LOGIN, unVisiteur.getLogin());
        values.put(COL_PASSWORD, unVisiteur.getPassword());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_VISITEUR, null, values);
    }

    //MàJ de la table
    public int updateVisiteur(int id, Visiteur unVisiteur){
        //La mise à jour d'un fras dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel frais on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_ID, unVisiteur.getId());
        values.put(COL_NOM, unVisiteur.getNom());
        values.put(COL_PRENOM, unVisiteur.getPrenom());
        values.put(COL_LOGIN, unVisiteur.getLogin());
        values.put(COL_PASSWORD, unVisiteur.getPassword());
        return bdd.update(TABLE_VISITEUR, values, COL_ID + " = " +id, null);
    }

    public int deleteVisiteur(int id){
        //Suppression d'un frais de la BDD grâce à l'ID
        return bdd.delete(TABLE_VISITEUR, COL_ID + " = " +id, null);
    }
}