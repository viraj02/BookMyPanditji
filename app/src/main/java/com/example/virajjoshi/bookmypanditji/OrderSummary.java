package com.example.virajjoshi.bookmypanditji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderSummary extends AppCompatActivity {

    TextView orderid,name,mobile,email,typeofpooja,date,time,totalamount;

    String order;

    Button addorderid;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    // Add Data to Firebase Database...

    //   private FirebaseDatabase mFirebaseDatabase;
    DatabaseReference myRef1,myRef2,myRef3,myRef4,myRef5,myRef6,myRef7;
   // DatabaseReference bf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

       // textView = (TextView) findViewById(R.id.idtextView);
     //   textView1 = (TextView) findViewById(R.id.idtextView1);



        orderid = (TextView) findViewById(R.id.Orderid);
        name = (TextView) findViewById(R.id.Name);

        mobile = (TextView) findViewById(R.id.Mobile);
        email = (TextView) findViewById(R.id.Email);
        typeofpooja = (TextView) findViewById(R.id.TypePooja);
        date = (TextView) findViewById(R.id.Date);
        time = (TextView) findViewById(R.id.Time);
        totalamount = (TextView) findViewById(R.id.TotalAmount);

        order = getIntent().getExtras().getString("value");



       // String intentorder = order;
      //  textView.setText(order);

       // FirebaseUser user = mAuth.getCurrentUser();






        myRef1 = FirebaseDatabase.getInstance().getReference().child("OrderSummary").child(order).child("Name");
        myRef2 = FirebaseDatabase.getInstance().getReference().child("OrderSummary").child(order).child("Mobile");
        myRef3 = FirebaseDatabase.getInstance().getReference().child("OrderSummary").child(order).child("Email");
        myRef4 = FirebaseDatabase.getInstance().getReference().child("OrderSummary").child(order).child("TypeOfPooja");
        myRef5 = FirebaseDatabase.getInstance().getReference().child("OrderSummary").child(order).child("Date");
        myRef6 = FirebaseDatabase.getInstance().getReference().child("OrderSummary").child(order).child("Time");
        myRef7 = FirebaseDatabase.getInstance().getReference().child("OrderSummary").child(order).child("TotalAmount");






        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orderid.setText(order);
                name.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mobile.setText(dataSnapshot.getValue().toString());



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                email.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                typeofpooja.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                date.setText(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                time.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                totalamount.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
