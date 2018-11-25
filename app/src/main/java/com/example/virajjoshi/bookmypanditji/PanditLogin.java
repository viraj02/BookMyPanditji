package com.example.virajjoshi.bookmypanditji;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class PanditLogin extends AppCompatActivity {


    EditText EmailText,PasswordText;
    Button SignIn;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private AwesomeValidation awesomeValidation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pandit_login);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



        EmailText = (EditText) findViewById(R.id.emailText);
        //EmailText.setText(emaill);
        //EmailText.setEnabled(false);

        PasswordText = (EditText) findViewById(R.id.passwordText);

        SignIn = (Button) findViewById(R.id.signinbutton);


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.emailText, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.passwordText, "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$", R.string.passworderror);


        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    EmailText.setText(user.getEmail());

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
    public void SignIn(View v){     // This Method is to check validation if validation then only login,and call SignInn Method..
        if(awesomeValidation.validate()){
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
                        //Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                        if(task.isSuccessful()){
                            //   Toast.makeText(Main2Activity.this, "Login Successfully",Toast.LENGTH_SHORT).show();
                            //  startActivity(new Intent(Main2Activity.this,Welcome.class));
                            //    finish();

                            //  Toast.makeText(Main2Activity.this, "Email has sent to " + user.getEmail() ,Toast.LENGTH_SHORT).show();
                            checkifemailverified();

                        }

                        if (!task.isSuccessful()) {
                            // Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(PanditLogin.this, "Account Creation Failed",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }




    private void checkifemailverified(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user.isEmailVerified()){
            Toast.makeText(PanditLogin.this,"Email is verified",Toast.LENGTH_LONG).show();
            startActivity(new Intent(PanditLogin.this, PanditDetails.class));

            finish();
        }
        else{
            Toast.makeText(PanditLogin.this,"Please Check your mail " + user.getEmail()+" to verify account",Toast.LENGTH_LONG).show();
            FirebaseAuth.getInstance().signOut();

        }
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
                            Toast.makeText(PanditLogin.this,"Reset email has been sent",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(PanditLogin.this);
        builder.setMessage("Do You Want To Close App ");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
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

