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
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DelAccAdmin extends AppCompatActivity {

    EditText Email, Password;
    Button DeleteAcc, Login;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_acc_admin);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Email = (EditText) findViewById(R.id.emaildelete);

        Password = (EditText) findViewById(R.id.passworddelete);

        Login = (Button) findViewById(R.id.deletelogin);

        DeleteAcc = (Button) findViewById(R.id.deleteacc);
        DeleteAcc.setText("Login To Delete Account");
        DeleteAcc.setEnabled(false);

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


    public void SignInnDelete(View view) {

        String email = Email.getText().toString();
        String password = Password.getText().toString();

        if(password.equals("")){
            Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_LONG).show();
        }

        else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                            if (task.isSuccessful()) {
                                Toast.makeText(DelAccAdmin.this, "Login Success",
                                        Toast.LENGTH_SHORT).show();
                                DeleteAcc.setText("Delete Account");
                                DeleteAcc.setEnabled(true);

                            }

                            if (!task.isSuccessful()) {
                                // Log.w(TAG, "signInWithEmail:failed", task.getException());
                                Toast.makeText(DelAccAdmin.this, "Wrong Password",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
    }

    public void deleteAccount(View view){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        // Get auth credentials from the user for re-authentication. The example below shows
        // email and password credentials but there are multiple possible providers,
        // such as GoogleAuthProvider or FacebookAuthProvider.

        String userid = user.getUid();
        String email = user.getEmail();
        String password = Password.getText().toString();



        DatabaseReference deleteusers = FirebaseDatabase.getInstance().getReference().child("AdminData").child("UserInfo").child(userid);
        deleteusers.removeValue();

        AuthCredential credential = EmailAuthProvider
                .getCredential(email,password);

        // Prompt the user to re-provide their sign-in credentials
        user.delete()
            .addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()) {


                Toast.makeText(DelAccAdmin.this,"User Deleted",Toast.LENGTH_LONG).show();
                startActivity(new Intent(DelAccAdmin.this,WelcomeAdmin.class));
                finish();
            }
        }
    });
}

}