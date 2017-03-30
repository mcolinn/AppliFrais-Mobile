package ppe_matthieu.myapplifraismobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import ppe_matthieu.myapplifraismobile.entity.Etat;

/**
 * Created by Matthieu_2 on 20/03/2017.
 */

public class BDDEtat {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "fraisMobile";

    private static final String TABLE_ETAT = "Etat";

    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_LIBELLE = "Libelle";
    private static final int NUM_COL_LIBELLE = 1;

    private SQLiteDatabase bdd;

    private DBHandler maBaseSQLite;

    public BDDEtat(Context context) {
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
    public long addEtat(Etat unEtat) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ID, unEtat.getId());
        values.put(COL_LIBELLE, unEtat.getLibelle());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_ETAT, null, values);
    }

    //MàJ de la table
    public int updateEtat(int id, Etat unEtat){
        //La mise à jour dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel frais on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_ID, unEtat.getId());
        values.put(COL_LIBELLE, unEtat.getLibelle());
        return bdd.update(TABLE_ETAT, values, COL_ID + " = " +id, null);
    }

    public int deleteEtat(int id){
        //Suppression de la BDD grâce à l'ID
        return bdd.delete(TABLE_ETAT, COL_ID + " = " +id, null);
    }
}