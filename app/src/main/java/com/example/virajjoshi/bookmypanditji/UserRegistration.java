package com.example.virajjoshi.bookmypanditji;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserRegistration extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {


    EditText EmailText, PasswordText;
    Button CreateAccount, HaveAccount, TermCondition,panditRegistration;
    CheckBox c1;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.emailText, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.passwordText, "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$", R.string.passworderror);


        EmailText = (EditText) findViewById(R.id.emailText);
        PasswordText = (EditText) findViewById(R.id.passwordText);


        CreateAccount = (Button) findViewById(R.id.createaccButton);
        //  SignOut = (Button) findViewById(R.id.signoutButton);
        HaveAccount = (Button) findViewById(R.id.haveaccountButton);

        c1 = (CheckBox) findViewById(R.id.checkBox1);
        c1.setOnCheckedChangeListener(UserRegistration.this);


        TermCondition = (Button) findViewById(R.id.termcButton);
        panditRegistration = (Button) findViewById(R.id.buttonPanditReg);


        CreateAccount.setEnabled(false);


        mAuth = FirebaseAuth.getInstance();




        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in

                    String email = user.getEmail();

                    checkifemailverified();

                    /*
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Do you want to use " + email + "this account");
                    //  builder.setCancelable(false);

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(MainActivity.this, Main2Activity.class));
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
                    */


                } else {
                    // User is signed out

                    // ...

                }
            }

        };
    }



    public void sendVerifictionEmail(){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(UserRegistration.this, "Verification link is send to " + user.getEmail() ,Toast.LENGTH_LONG).show();
                                //checkifemailverified();
                                startActivity(new Intent(UserRegistration.this,  UserLogin.class));
                                finish();
                            }
                            else {
                                Toast.makeText(UserRegistration.this,"couldn't send verification email",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
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

    public void Validate(View v) {
        if (awesomeValidation.validate()) {
            createAccount();
        }
    }

    private void createAccount() {
        String email = EmailText.getText().toString();
        String password = PasswordText.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            sendVerifictionEmail();
                            //  Toast.makeText(MainActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                            //  startActivity(new Intent(MainActivity.this, Main2Activity.class));
                            //  finish();
                        }

                        if (!task.isSuccessful()) {
                            Toast.makeText(UserRegistration.this, "Account Creation Failed", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }


/*
    public void signOut(View view) {
        mAuth.signOut();

    }
    */


    public void HaveAc(View v) {
        startActivity(new Intent(this, UserLogin.class));
        //startActivity(new Intent(this,Pandit_Registration.class));
        finish();
    }

    public void Reg(View v){
        startActivity(new Intent(this,PanditRegistartion.class));

        //Toast.makeText(this, "Another app", Toast.LENGTH_SHORT).show();

    }




    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(UserRegistration.this);
        builder.setMessage("Do You Want To Close App ");

        // builder.setTitle("Hello");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //System.exit(0);
                finishAffinity();
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


    // This is not sure for authentication


    private void TermCondition() {
        String mail = "Enter The Valid Email Id This Will be useful while SignIn OR resetting the password";
        String password = "Enter Valid password .";
        String tm = "If your  mobile app allows users to create content and make that content public to other users, a Content section will inform users that they own the rights to the content they have created.The “Content” clause usually mentions that users must give you (the website or mobile app developer) a license so that you can share this content on your website/mobile app and to make it available to other users.Because the content created by users is public to other users, a DMCA notice clause (or Copyright Infringement ) section is helpful to inform users and copyright authors that, if any content is found to be a copyright infringement, you will respond to any DMCA takedown notices received and you will take down the content.";


        final AlertDialog.Builder builder = new AlertDialog.Builder(UserRegistration.this);
        builder.setTitle("Terms And Conditions");
        builder.setMessage(mail + "\n" + password + "\n" + tm);
        builder.setCancelable(true);

        builder.setPositiveButton("Agree", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                CreateAccount.setEnabled(true);
                c1.setChecked(true);
                dialog.cancel();
            }
        });

        builder.setNegativeButton("Not Agree", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                c1.setChecked(false);
                CreateAccount.setEnabled(false);
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(c1.isChecked() == true){
            TermCondition();
        }
        if(c1.isChecked() == false){
            CreateAccount.setEnabled(false);
        }
    }

    private void checkifemailverified() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        assert user != null;
        if (user.isEmailVerified()) {
            startActivity(new Intent(UserRegistration.this,WelcomeUser.class));
            finish();
        }
    }

}