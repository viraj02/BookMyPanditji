package com.example.virajjoshi.bookmypanditji;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UserLogin extends AppCompatActivity {

    EditText EmailText, PasswordText;
    Button SignIn, NoAcc;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private AwesomeValidation awesomeValidation;

    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        myRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bookmypanditji-93e79.firebaseio.com/");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        EmailText = (EditText) findViewById(R.id.emailText);
        PasswordText = (EditText) findViewById(R.id.passwordText);

        SignIn = (Button) findViewById(R.id.signinbutton);
        NoAcc = (Button) findViewById(R.id.noaccoutbutton);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.emailText, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.passwordText, "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$", R.string.passworderror);


        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                } else {
                    // User is signed out

                }
                // ...
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

    public void SignIn(View v) {     // This Method is to check validation if validation then only login,and call SignInn Method..
        if (awesomeValidation.validate()) {
            SignInn();
        }
    }

    private void SignInn() {

        String email = EmailText.getText().toString();
        String password = PasswordText.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (task.isSuccessful()) {
                            checkifemailverified();
                        }
                        if (!task.isSuccessful()) {

                            Toast.makeText(UserLogin.this, "Invalid Password",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void resetPassword(View view) {

        String email = EmailText.getText().toString();

        if (email.length() == 0) {
            EmailText.setError("Enter an email address");
            return;
        }

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UserLogin.this, "Reset email has been sent", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(UserLogin.this, "Reset email fail to sent", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void checkifemailverified() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        String email = user.getEmail();
        String Pass = PasswordText.getText().toString();

        if (user.isEmailVerified()) {

            myRef.child("AdminData").child("UserInfo").child(userId).child("UserID").setValue(userId);
            myRef.child("AdminData").child("UserInfo").child(userId).child("Email").setValue(email);
            myRef.child("AdminData").child("UserInfo").child(userId).child("Password").setValue(Pass);

            Toast.makeText(UserLogin.this, "Welcome " + email, Toast.LENGTH_LONG).show();
            startActivity(new Intent(UserLogin.this, WelcomeUser.class));
            finish();
        } else {
            Toast.makeText(UserLogin.this, "Please Check your mail " + user.getEmail() + " to verify account", Toast.LENGTH_LONG).show();
            FirebaseAuth.getInstance().signOut();

        }
    }


    public void NoAcc(View v) {
        startActivity(new Intent(UserLogin.this, UserRegistration.class));
        finish();
    }




    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(UserLogin.this);
        builder.setMessage("Do You Want To Close App ");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                dialog.cancel();
            }
        });

        AlertDialog alert  = builder.create();
        alert.show();

    }

}