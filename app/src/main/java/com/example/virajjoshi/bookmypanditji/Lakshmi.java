    package com.example.virajjoshi.bookmypanditji;

    import android.app.AlertDialog;
    import android.content.DialogInterface;
    import android.content.Intent;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;

    public class Lakshmi extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lakshmi);
        }
        @Override
        public void onBackPressed() {
            final AlertDialog.Builder builder = new AlertDialog.Builder(Lakshmi.this);
            builder.setMessage("All Your Check will be lost ");

            // builder.setTitle("Hello");
            builder.setCancelable(true);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    startActivity(new Intent(Lakshmi.this,ItemsReqForPooja.class));
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
