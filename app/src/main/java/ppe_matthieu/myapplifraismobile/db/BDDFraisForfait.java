package ppe_matthieu.myapplifraismobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthieu_2 on 20/03/2017.
 */

public class BDDFraisForfait {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "fraisMobile";

    private static final String TABLE_FRAIS_FORFAIT = "FraisForfait";

    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_LIBELLE = "Libelle";
    private static final int NUM_COL_ISBN = 1;
    private static final String COL_MONTANT = "Montant";
    private static final int NUM_COL_TITRE = 2;

    private SQLiteDatabase bdd;

    private DBHandler maBaseSQLite;

    public BDDFraisForfait(Context context) {
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
    public long addFraisForfait(FraisForfait unFraisForfait) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ID, unFraisForfait.getId());
        values.put(COL_LIBELLE, unFraisForfait.getLibelle());
        values.put(COL_MONTANT, unFraisForfait.getMontant());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_FRAIS_FORFAIT, null, values);
    }

    //MàJ de la table
    public int updateFraisForfait(int id, FraisForfait unFraisForfait){
        //La mise à jour d'un fras dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel frais on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_ID, unFraisForfait.getId());
        values.put(COL_LIBELLE, unFraisForfait.getLibelle());
        values.put(COL_MONTANT, unFraisForfait.getMontant());
        return bdd.update(TABLE_FRAIS_FORFAIT, values, COL_ID + " = " +id, null);
    }

    public int deleteFraisForfait(int id){
        //Suppression d'un frais de la BDD grâce à l'ID
        return bdd.delete(TABLE_FRAIS_FORFAIT, COL_ID + " = " +id, null);
    }
}