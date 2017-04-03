package ppe_matthieu.myapplifraismobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent it = getIntent();
        String id = it.getStringExtra("id");
        Toast.makeText(getApplicationContext(), "id = " + id, Toast.LENGTH_SHORT).show();

        FloatingActionButton addFraisButton = (FloatingActionButton) findViewById(R.id.addFraisButton);
        addFraisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAddFrais = new Intent(MainActivity.this, AddFraisActivity.class);
                startActivity(intentAddFrais);
            }
        });
    }
}
