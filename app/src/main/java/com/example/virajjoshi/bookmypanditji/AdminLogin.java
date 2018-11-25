package com.example.virajjoshi.bookmypanditji;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminLogin extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;



    EditText EmailText,PasswordText;

    Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        EmailText = (EditText) findViewById(R.id.EmailAdmin);
        PasswordText = (EditText) findViewById(R.id.PasswordAdmin);

        Login = (Button) findViewById(R.id.AdminLogin);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInn();
            }
        });




        mAuth = FirebaseAuth.getInstance();

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

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void SignInn() {

        String email = EmailText.getText().toString();
        String password = PasswordText.getText().toString();

        if (email.equals("") && password.equals("")) {
            Toast.makeText(getApplicationContext(), "Should Not Be Empty", Toast.LENGTH_LONG).show();
        } else {


            if (email.equals("jviraj107@gmail.com")) {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                                if (task.isSuccessful()) {
                                    //   Toast.makeText(Main2Activity.this, "Login Successfully",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(AdminLogin.this, WelcomeAdmin.class));
                                    //    finish();

                                    //  Toast.makeText(Main2Activity.this, "Email has sent to " + user.getEmail() ,Toast.LENGTH_SHORT).show();


                                }

                                if (!task.isSuccessful()) {
                                    // Log.w(TAG, "signInWithEmail:failed", task.getException());
                                    Toast.makeText(AdminLogin.this, "Account Creation Failed",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            } else {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                                if (task.isSuccessful()) {
                                    //   Toast.makeText(Main2Activity.this, "Login Successfully",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(AdminLogin.this, UpdatePanditDetails.class));
                                    //    finish();

                                    //  Toast.makeText(Main2Activity.this, "Email has sent to " + user.getEmail() ,Toast.LENGTH_SHORT).show();


                                }

                                if (!task.isSuccessful()) {
                                    // Log.w(TAG, "signInWithEmail:failed", task.getException());
                                    Toast.makeText(AdminLogin.this, "Account Creation Failed",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
        }
    }

}

