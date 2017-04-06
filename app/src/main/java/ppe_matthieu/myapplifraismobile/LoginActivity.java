package ppe_matthieu.myapplifraismobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    /**
     * Ajout de M.Benadi
     * 28/03/2017
     * */
    //Code de connection à la BDD et vérification user

    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    //Parametre à envoyer au MainActivity
    final String EXTRA_ID_USER = "user_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnConnexion = (Button) findViewById(R.id.connect_button);
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {

                int id = button.getId();

                switch (id) {
                    case R.id.connect_button:
                        final EditText username_txt = (EditText) findViewById(R.id.username_txt);
                        final EditText password_txt = (EditText) findViewById(R.id.password_txt);

                        String username = username_txt.getText().toString();
                        String password = password_txt.getText().toString();

                        //fait bugg
                       new connect().execute(username, password);
                }
            }
        });

    }

    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    private class connect extends AsyncTask<String, String, String>{

        String message;
        boolean success;
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Connection en cours. Veuillez patientez...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting user ids from url
         * */
        @Override
        protected String doInBackground(String... args) {

            String username = args[0];
            String password = args[1];
            // Building Parameters
            HashMap<String, String> params = new HashMap<>();
            params.put("username", username);
            params.put("password", password);

            // getting JSON string from URL
            String url_connexion = "http://10.0.2.2/appliFrais/web/app_dev.php/api/connect";
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


