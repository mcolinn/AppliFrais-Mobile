package ppe_matthieu.myapplifraismobile.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper  {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "fraisMobile";

    // TABLE Visiteur
    private static final String TABLE_VISTEUR ="Visiteur";
    // Colonne TABLE Visiteur
    private static final String VISTEUR_KEY_ID = "id";
    private static final String VISTEUR_KEY_NOM = "nom";
    private static final String VISTEUR_KEY_PRENOM = "prenom";
    private static final String VISTEUR_KEY_LOGIN = "login";
    private static final String VISTEUR_KEY_PASSWORD = "password";

    // TABLE FicheFrais
    private static final String TABLE_FICHE_FRAIS ="FicheFrais";
    // Colonne TABLE FicheFrais
    private static final String FICHE_FRAIS_KEY_ID = "id";
    private static final String FICHE_FRAIS_KEY_ID_VISITEUR = "idVisiteur";
    private static final String FICHE_FRAIS_KEY_MOIS = "mois";
    private static final String FICHE_FRAIS_KEY_NB_JUSTIFICATIF = "nbJustificatif";
    private static final String FICHE_FRAIS_KEY_MONTANT_VALIDE = "montantValide";
    private static final String FICHE_FRAIS_KEY_DATE_MODIF = "dateModif";

    // TABLE LigneFraisForfait
    private static final String TABLE_LIGNE_FRAIS_FORFAIT ="LigneFraisForfait";
    // Colonne TABLE LigneFraisForfait
    private static final String LIGNE_FRAIS_FORFAIT_KEY_ID = "id";
    private static final String LIGNE_FRAIS_FORFAIT_KEY_ID_VISITEUR = "idVisiteur";
    private static final String LIGNE_FRAIS_FORFAIT_KEY_MOIS = "mois";
    private static final String LIGNE_FRAIS_FORFAIT_KEY_ID_FRAIS_FORFAIT = "idFraisForfait";
    private static final String LIGNE_FRAIS_FORFAIT_KEY_QUANTITE = "quantite";

    // TABLE LigneFraisHorsForfait
    private static final String TABLE_LIGNE_FRAIS_HORS_FORFAIT ="LigneFraisHorsForfait";
    // Colonne TABLE LigneFraisHorsForfait
    private static final String LIGNE_FRAIS_HORS_FORFAIT_KEY_ID = "id";
    private static final String LIGNE_FRAIS_HORS_FORFAIT_KEY_DATE = "date";
    private static final String LIGNE_FRAIS_HORS_FORFAIT_KEY_MONTANT = "montant";
    private static final String LIGNE_FRAIS_HORS_FORFAIT_KEY_LIBELLE = "libelle";

    // TABLE FraisForfait
    private static final String TABLE_FRAIS_FORFAIT ="FraisForfait";
    // Colonne TABLE FraisForfait
    private static final String FRAIS_FORFAIT_KEY_ID = "id";
    private static final String FRAIS_FORFAIT_KEY_LIBELLE = "libelle";
    private static final String FRAIS_FORFAIT_KEY_MONTANT = "montant";

    // TABLE Etat
    private static final String TABLE_ETAT ="Etat";
    // Colonne TABLE Etat
    private static final String ETAT_KEY_ID = "id";
    private static final String ETAT_KEY_LIBELLE = "libelle";

    public DBHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Création Table Visiteur
        String CREATE_TABLE_VISITEUR = "CREATE TABLE " + TABLE_VISTEUR +
                "(" + VISTEUR_KEY_ID + " INTEGER,"
                + VISTEUR_KEY_NOM + " TEXT,"
                + VISTEUR_KEY_PRENOM + " TEXT,"
                + VISTEUR_KEY_LOGIN + " TEXT,"
                + VISTEUR_KEY_PASSWORD + " TEXT,"
                + " PRIMARY KEY ("+VISTEUR_KEY_ID+")"
                +")";
        db.execSQL(CREATE_TABLE_VISITEUR);

        //Création Table FraisForfait
        String CREATE_TABLE_FRAIS_FORFAIT = "CREATE TABLE " + TABLE_FRAIS_FORFAIT +
                "(" + FRAIS_FORFAIT_KEY_ID + " INTEGER,"
                + FRAIS_FORFAIT_KEY_LIBELLE + " TEXT,"
                + FRAIS_FORFAIT_KEY_MONTANT + " INTEGER, "
                + " PRIMARY KEY ("+FRAIS_FORFAIT_KEY_ID+")"
                +")";
        db.execSQL(CREATE_TABLE_FRAIS_FORFAIT);

        //Création Table FicheFrais
        String CREATE_TABLE_FICHE_FRAIS = "CREATE TABLE " + TABLE_FICHE_FRAIS +
                "(" + FICHE_FRAIS_KEY_ID + " INTEGER,"
                + FICHE_FRAIS_KEY_ID_VISITEUR + " INTEGER FOREIGN KEY (" + FICHE_FRAIS_KEY_ID_VISITEUR + ") REFERENCES + " + TABLE_VISTEUR +"(" + VISTEUR_KEY_ID + ")"+","
                + FICHE_FRAIS_KEY_MOIS + " INTEGER,"
                + FICHE_FRAIS_KEY_NB_JUSTIFICATIF + " INTEGER,"
                + FICHE_FRAIS_KEY_MONTANT_VALIDE + " BOOLEAN,"
                + FICHE_FRAIS_KEY_DATE_MODIF + " DATETIME, "
                + " PRIMARY KEY ("+FICHE_FRAIS_KEY_ID+", "+FICHE_FRAIS_KEY_ID_VISITEUR+", "+FICHE_FRAIS_KEY_MOIS+")"
                +")";
        db.execSQL(CREATE_TABLE_FICHE_FRAIS);

        //Création Table LigneFraisForfait
        String CREATE_TABLE_LIGNE_FRAIS_FORFAIT = "CREATE TABLE " + TABLE_LIGNE_FRAIS_FORFAIT +
                "(" + LIGNE_FRAIS_FORFAIT_KEY_ID + " INTEGER,"
                + LIGNE_FRAIS_FORFAIT_KEY_ID_VISITEUR + " INTEGER FOREIGN KEY(" + LIGNE_FRAIS_FORFAIT_KEY_ID_VISITEUR + ") REFERENCES + " + TABLE_VISTEUR +"(" + VISTEUR_KEY_ID + ")"+","
                + LIGNE_FRAIS_FORFAIT_KEY_MOIS + " INTEGER,"
                + LIGNE_FRAIS_FORFAIT_KEY_ID_FRAIS_FORFAIT + " INTEGER FOREIGN KEY(" + LIGNE_FRAIS_FORFAIT_KEY_ID_FRAIS_FORFAIT + ") REFERENCES + " + TABLE_FRAIS_FORFAIT +"(" + FRAIS_FORFAIT_KEY_ID + ")"+","
                + LIGNE_FRAIS_FORFAIT_KEY_QUANTITE + " INTEGER,"
                + " PRIMARY KEY ("+LIGNE_FRAIS_FORFAIT_KEY_ID+", "+LIGNE_FRAIS_FORFAIT_KEY_MOIS+", "+LIGNE_FRAIS_FORFAIT_KEY_ID_FRAIS_FORFAIT+", "+LIGNE_FRAIS_FORFAIT_KEY_ID_VISITEUR+")"
                +")";
        db.execSQL(CREATE_TABLE_LIGNE_FRAIS_FORFAIT);

        //Création Table LigneFraisHorsForfait
        String CREATE_TABLE_LIGNE_FRAIS_HORS_FORFAIT = "CREATE TABLE " + TABLE_LIGNE_FRAIS_HORS_FORFAIT +
                "(" + LIGNE_FRAIS_HORS_FORFAIT_KEY_ID + " INTEGER PRIMARY KEY,"
                + LIGNE_FRAIS_HORS_FORFAIT_KEY_DATE + " DATETIME,"
                + LIGNE_FRAIS_HORS_FORFAIT_KEY_MONTANT + " INTEGER,"
                + LIGNE_FRAIS_HORS_FORFAIT_KEY_LIBELLE + " TEXT,"
                + " PRIMARY KEY ("+LIGNE_FRAIS_HORS_FORFAIT_KEY_ID+")"
                +")";
        db.execSQL(CREATE_TABLE_LIGNE_FRAIS_HORS_FORFAIT);

        //Création Table Etat
        String CREATE_TABLE_ETAT = "CREATE TABLE " + TABLE_ETAT +
                "(" + ETAT_KEY_ID + " INTEGER,"
                + ETAT_KEY_LIBELLE + " TEXT,"
                + " PRIMARY KEY ("+ETAT_KEY_ID+")"
                +")";
        db.execSQL(CREATE_TABLE_ETAT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if existed
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_VISTEUR + ", " + TABLE_FICHE_FRAIS + ", " + TABLE_LIGNE_FRAIS_FORFAIT + ", " + TABLE_LIGNE_FRAIS_HORS_FORFAIT + ", " + TABLE_FRAIS_FORFAIT + ", " + TABLE_ETAT);
        // Creating tables again
        onCreate(db);
    }
}
