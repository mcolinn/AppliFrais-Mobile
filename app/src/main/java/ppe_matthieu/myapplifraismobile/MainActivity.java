package ppe_matthieu.myapplifraismobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

/*    //Parametre à envoyer au MainActivity
    final String EXTRA_ID_USER = "user_id";*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent it = getIntent();
        String id = it.getStringExtra("id");
//        Toast.makeText(getApplicationContext(), "id = " + id, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Bonjour " + id, Toast.LENGTH_SHORT).show();

        new connect().execute(id);

/*
        Button showFraisButton = (Button) findViewById(R.id.buttonAfficher);
        showFraisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentShowFrais = new Intent(MainActivity.this, ShowFraisActivity.class);
                startActivity(intentShowFrais);
            }
        });

        Button addFraisButton = (Button) findViewById(R.id.buttonAjouter);
        addFraisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAddFrais = new Intent(MainActivity.this, AddFraisActivity.class);
                startActivity(intentAddFrais);
            }
        });
*/
    }

    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    private class connect extends AsyncTask<String, String, String> {

        String message;
        boolean success;
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Chargement des données. Veuillez patientez...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting user ids from url
         * */
        @Override
        protected String doInBackground(String... args) {

            String id = args[0];
            // Building Parameters
            HashMap<String, String> params = new HashMap<>();
            params.put("id", id);

            // getting JSON string from URL
            String url_connexion = "http://10.0.2.2/appliFrais/web/app_dev.php/api/fiches";
            JSONObject json = jParser.makeHttpRequest(url_connexion, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("Résultat: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                success = json.getBoolean(TAG_SUCCESS);
                message = json.getString(TAG_MESSAGE);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after connection
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    if (success){
                        Intent listeFiches = new Intent(getApplicationContext(), MainActivity.class);
                        listeFiches.putExtra("id", message);
                        startActivity(listeFiches);


                    }else{
                        Toast.makeText(getApplicationContext(), "Erreur: " + message, Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }
}
