package ppe_matthieu.myapplifraismobile;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static android.R.id.message;

public class AddFraisActivity extends AppCompatActivity {

//    private String libelle = "Libelle";
//    private int montant = 0;
// Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    // url to get all products list
    //TODO : Ajouter la route dans le controller
    private static String url_ajout_frais = "http://10.10.0.2/web/app_dev.php/api/ajouter_frais";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_frais);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener()
            final Button button = (Button) findViewById(R.id.buttonAjouter);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //récupération des champs du formulaire
                    final EditText libelleField = (EditText) findViewById(R.id.editTextLibelleFrais);
                    String libelle = libelleField.getText().toString();
                    final EditText montantField = (EditText) findViewById(R.id.editTextMontantFrais);
                    String montant = montantField.getText().toString();

                    //ajout du frais à la BDD
                    new ajoutFrais().execute(libelle, montant);

                }
            });
    }
    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class ajoutFrais extends AsyncTask<String, String, String> {
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AddFraisActivity.this);
            pDialog.setMessage("Traitement des données en cours. Veuillez patientez...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting user ids from url
         * */
        protected String doInBackground(String... args) {

            String libelle = args[0];
            String montant = args[1];
            // Building Parameters
            HashMap<String, String> params = new HashMap<>();
            params.put("libelle", libelle);
            params.put("montant", montant);

            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_ajout_frais, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("Résultat: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                boolean success = json.getBoolean(TAG_SUCCESS);
                String message = json.getString(TAG_MESSAGE);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url, final boolean success) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    if (success){
                        Toast.makeText(null, "id = " + message, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(null, "Erreur: " + message, Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }


}
