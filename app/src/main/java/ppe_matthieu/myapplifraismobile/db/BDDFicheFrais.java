package ppe_matthieu.myapplifraismobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Matthieu_2 on 20/03/2017.
 */

public class BDDFicheFrais {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "fraisMobile";

    private static final String TABLE_FICHE_FRAIS = "FicheFrais";

    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_ID_VISITEUR = "IdVisiteur";
    private static final int NUM_COL_ID_VISITEUR = 1;
    private static final String COL_MOIS = "Mois";
    private static final int NUM_COL_MOIS = 2;
    private static final String COL_NB_JUSTIFICATIF = "NbJustificatif";
    private static final int NUM_COL_NB_JUSTIFICATIF = 3;
    private static final String COL_MONTANT_VALIDE = "MontantValide";
    private static final int NUM_COL_MONTANT_VALIDE = 4;
    private static final String COL_DATE_MODIF = "DateModif";
    private static final int NUM_CCOL_DATE_MODIF = 5;

    private SQLiteDatabase bdd;

    private DBHandler maBaseSQLite;

    public BDDFicheFrais(Context context) {
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
    public long addFicheFrais(FicheFrais uneFicheFrais) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ID, uneFicheFrais.getId());
        values.put (COL_ID_VISITEUR, uneFicheFrais.getIdVisiteur());
        values.put(COL_MOIS, uneFicheFrais.getMois());
        values.put(COL_NB_JUSTIFICATIF, uneFicheFrais.getNbJustficatifs());
        values.put(COL_MONTANT_VALIDE, uneFicheFrais.isMontantvalide());
        values.put(COL_DATE_MODIF, uneFicheFrais.getDateModif());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_FICHE_FRAIS, null, values);
    }

    //MàJ de la table
    public int updateFicheFrais(int id, FicheFrais uneFicheFrais){
        //La mise à jour d'un fras dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel frais on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_ID, uneFicheFrais.getId());
        values.put (COL_ID_VISITEUR, uneFicheFrais.getIdVisiteur());
        values.put(COL_MOIS, uneFicheFrais.getMois());
        values.put(COL_NB_JUSTIFICATIF, uneFicheFrais.getNbJustficatifs());
        values.put(COL_MONTANT_VALIDE, uneFicheFrais.isMontantvalide());
        values.put(COL_DATE_MODIF, uneFicheFrais.getDateModif());
        return bdd.update(TABLE_FICHE_FRAIS, values, COL_ID + " = " +id, null);
    }

    public int deleteFicheFrais(int id){
        //Suppression d'un frais de la BDD grâce à l'ID
        return bdd.delete(TABLE_FICHE_FRAIS, COL_ID + " = " +id, null);
    }
}