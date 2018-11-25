package com.example.virajjoshi.bookmypanditji;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfile extends AppCompatActivity {

    TextView UserEmail, UserID;
    Button MyOrders, SignOut;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        UserEmail = (TextView) findViewById(R.id.emailText);
        UserID = (TextView) findViewById(R.id.useridText);

        MyOrders = (Button) findViewById(R.id.orderbutton);

        SignOut = (Button) findViewById(R.id.signoutrbutton);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        UserEmail.setText(user.getEmail());
        UserID.setText(user.getUid());

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in

                    String email = user.getEmail();


                } else {
                    // User is signed out

                    // ...

                }
            }

        };

    }
    public void signOut(View view) {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(UserProfile.this,UserRegistration.class));
        finish();
    }

    public void myorders(View view){
        startActivity(new Intent(UserProfile.this,MyOrders.class));
    }

    public void DeleteAccount(View view){
        startActivity(new Intent(UserProfile.this,DeleteUsers.class));
    }
}