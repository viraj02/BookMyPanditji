package com.example.virajjoshi.bookmypanditji;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ItemsReqForPooja extends AppCompatActivity {

    Button ganpati,satya,ghriha,durga,lakshmi,visnu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_req_for_pooja);

        ganpati = (Button) findViewById(R.id.Ganpati);
        satya = (Button) findViewById(R.id.satyanarayan);
        ghriha = (Button) findViewById(R.id.GrihaP);
        durga = (Button) findViewById(R.id.Durga);
        lakshmi = (Button) findViewById(R.id.Lakshmi);
        visnu = (Button) findViewById(R.id.Vishnu);
    }

    public void GanpatiA(View view){
        startActivity(new Intent(ItemsReqForPooja.this,Ganpati.class));
    }

    public void SatyaA(View view){
        startActivity(new Intent(ItemsReqForPooja.this,Satyanarayan.class));
    }

    public void GhrihaA(View view){
        startActivity(new Intent(ItemsReqForPooja.this,GrihaPravesh.class));
    }

    public void DurgaA(View view){
        startActivity(new Intent(ItemsReqForPooja.this,Durga.class));
    }

    public void LakshmiA(View view){
        startActivity(new Intent(ItemsReqForPooja.this,Lakshmi.class));
    }

    public void VishnuA(View view){
        startActivity(new Intent(ItemsReqForPooja.this,Vishnu.class));
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ItemsReqForPooja.this);
        builder.setMessage("Close This Activity ");

        // builder.setTitle("Hello");
        // builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(ItemsReqForPooja.this,WelcomeUser.class));
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        AlertDialog alert = builder.create();
        alert.show();

    }

}
