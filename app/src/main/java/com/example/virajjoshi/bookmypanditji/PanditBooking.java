package com.example.virajjoshi.bookmypanditji;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class PanditBooking extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    EditText Name, Gmail, Phone, Address, TotalPrice;
    Spinner TypeofPooja;
    CheckBox termsCN;
    Button RequesttoPandit;
    RadioGroup RadioGgroup;
    TextView Date,Time;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private AwesomeValidation awesomeValidation;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    // Add Data to Firebase Database...

    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pandit_booking);

        myRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bookmypanditji-93e79.firebaseio.com/");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.nameText, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.gmailText, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.phoneText, "^[0-9]{2}[0-9]{8}$", R.string.mobilerror);



        Name = (EditText) findViewById(R.id.nameText);
        Gmail = (EditText) findViewById(R.id.gmailText);
        Gmail.setText(user.getEmail());
        Phone = (EditText) findViewById(R.id.phoneText);


        Date = (TextView) findViewById(R.id.datePooja);
        Date.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR );
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(PanditBooking.this , android.R.style.Theme_Holo_Light_Dialog_MinWidth ,mDateSetListener,year,month,day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + month+1);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date = dayOfMonth + "/" + month + "/" + year;
                Date.setText(date);
            }
        };

        Time = (TextView) findViewById(R.id.timePooja);
        Time.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(PanditBooking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Time.setText( selectedHour + ":" + selectedMinute);

                        Address.setEnabled(false);
                        if(selectedHour>=6 && selectedHour<=18){
                            Address.setEnabled(true);
                        }
                        else {
                            Address.setEnabled(false);
                            Address.setText("");
                            Toast.makeText(getApplicationContext(),"Select Timing between 6 to 18",Toast.LENGTH_LONG).show();
                        }
                    }
                }, hour, minute, true);//Yes 24 hour time



                mTimePicker.setTitle("Select Timing For Pooja");
                mTimePicker.show();

            }
        });

        Address = (EditText) findViewById(R.id.addressText);
        TotalPrice = (EditText) findViewById(R.id.totalPrice);
        TotalPrice.setEnabled(false);

        termsCN = (CheckBox) findViewById(R.id.termsC);
        termsCN.setOnCheckedChangeListener(PanditBooking.this);


        RadioGgroup = (RadioGroup) findViewById(R.id.radiogrp);
        RadioGgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int i) {

                switch (i) {
                    case R.id.pandit:
                        TotalPrice.setText("501");



                        break;


                    case R.id.pandititems:
                        TotalPrice.setText("901");
                        break;

                }
            }
        });


        TypeofPooja = (Spinner) findViewById(R.id.typeofPooja);

        String[] pooja = {"Satyanarayan Pooja", "Griha Pravesh Pooja", "Durga Pooja", "Lakshmi Pooja", "Vishnu Poojas" , "Ganpanti Pooja"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, pooja);


        TypeofPooja.setAdapter(adapter);


        RequesttoPandit = (Button) findViewById(R.id.reqquestButton);
        RequesttoPandit.setEnabled(false);


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

    public void AddToDatabase() {

        FirebaseUser user = mAuth.getCurrentUser();

        String UserName = Name.getText().toString();
        String UEmail = Gmail.getText().toString();
        String UMobile = Phone.getText().toString();
        String DateofPooja = Date.getText().toString();
        String TimeofPooja = Time.getText().toString();
        String UAddress = Address.getText().toString();
        String TypeOfP = (String) TypeofPooja.getSelectedItem();
        String totalprice = TotalPrice.getText().toString();

        String userId = null;
        if (user != null) {
            userId = user.getUid();
        }

        if(!UserName.equals("") && !UEmail.equals("") && !UMobile.equals("")&& !DateofPooja.equals("")&& !UAddress.equals("")&& !TypeOfP.equals("") && !DateofPooja.equals("") &&  !TimeofPooja.equals("") && !totalprice.equals("")){

            if (user != null) {
                String gmail = user.getEmail();
            }

            String Id = UUID.randomUUID().toString();       // that user each new order...
            String orderId = UUID.randomUUID().toString();      // order id for each new order...

            //Order Summary Table
            myRef.child("OrderSummary").child(orderId).child("OrderID").setValue(orderId);

            myRef.child("OrderSummary").child(orderId).child("UserID").setValue(userId);
            myRef.child("OrderSummary").child(orderId).child("Name").setValue(UserName);
            myRef.child("OrderSummary").child(orderId).child("Email").setValue(UEmail);
            myRef.child("OrderSummary").child(orderId).child("Date").setValue(DateofPooja);
            myRef.child("OrderSummary").child(orderId).child("Time").setValue(TimeofPooja);
            //  myRef.child("OrderSummary").child(orderId).child("Time").setValue(total);
            myRef.child("OrderSummary").child(orderId).child("Mobile").setValue(UMobile);
            myRef.child("OrderSummary").child(orderId).child("Address").setValue(UAddress);
            myRef.child("OrderSummary").child(orderId).child("TypeOfPooja").setValue(TypeOfP);
            myRef.child("OrderSummary").child(orderId).child("TotalAmount").setValue(totalprice);


            //User Table
            if (userId != null) {
                myRef.child("Users").child(userId).child(orderId).child("OrderID").setValue(orderId);


                myRef.child("Users").child(userId).child(orderId).child("Name").setValue(UserName);
                myRef.child("Users").child(userId).child(orderId).child("Email").setValue(UEmail);
                myRef.child("Users").child(userId).child(orderId).child("Mobile").setValue(UMobile);

                myRef.child("Users").child(userId).child(orderId).child("Date").setValue(DateofPooja);
                myRef.child("Users").child(userId).child(orderId).child("Time").setValue(TimeofPooja);

                myRef.child("Users").child(userId).child(orderId).child("Address").setValue(UAddress);
                myRef.child("Users").child(userId).child(orderId).child("TypeOfPooja").setValue(TypeOfP);
                myRef.child("Users").child(userId).child(orderId).child("TotalAmount").setValue(totalprice);

            }
                       Toast.makeText(PanditBooking.this,"Request send successfully!!!",Toast.LENGTH_LONG).show();
           // startActivity(new Intent(PanditBooking.this,WelcomeUser.class));
            finish();
            //reset the text
            //mNewFood.setText("");
                Artist artist = new Artist(orderId,UserName,UAddress,DateofPooja,TimeofPooja,UEmail,UMobile,TypeOfP,totalprice);
              //  myRef.child(orderId).setValue(artist);

        myRef.child("UserOrderID").child(user.getUid()).child("Orderidforuser").setValue(orderId);

            Intent i = new Intent(PanditBooking.this,OrderSummary.class);
            i.putExtra("value",orderId);
            startActivity(i);
            finish();


        }
    }


    private void TermCondition() {
        String mail = "Enter The Valid Email Id This Will be useful to contact.";

        String password = "For Now no online payment is available.";


        final AlertDialog.Builder builder = new AlertDialog.Builder(PanditBooking.this);
        builder.setTitle("Terms And Conditions");
        builder.setMessage(mail + "\n" + password + "\n");
        builder.setCancelable(true);

        builder.setPositiveButton("Agree", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                RequesttoPandit.setEnabled(true);
                termsCN.setChecked(true);
                dialog.cancel();
            }
        });

        builder.setNegativeButton("Not Agree", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                termsCN.setChecked(false);
                RequesttoPandit.setEnabled(false);
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void Validation(View view){
        if(awesomeValidation.validate()) {
            AddToDatabase();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(termsCN.isChecked()==true){
            TermCondition();
        }

        if(termsCN.isChecked()==false){
            RequesttoPandit.setEnabled(false);
        }
    }
}
