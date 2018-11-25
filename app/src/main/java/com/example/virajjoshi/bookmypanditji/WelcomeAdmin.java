package com.example.virajjoshi.bookmypanditji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_admin);
    }

    public void signOut(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(WelcomeAdmin.this,UserRegistration.class));
        finish();
    }

    public void PanditProfile(View view){
        startActivity(new Intent(WelcomeAdmin.this,ShowImagesActivity.class));
    }

    public void OrdersSummary(View view){
        startActivity(new Intent(WelcomeAdmin.this,UsersOrdersAll.class));
    }

    public void Delete(View view){
        startActivity(new Intent(WelcomeAdmin.this,DelAccAdmin.class));
        finish();
    }



}
