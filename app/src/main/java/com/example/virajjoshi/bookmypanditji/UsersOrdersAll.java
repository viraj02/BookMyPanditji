package com.example.virajjoshi.bookmypanditji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UsersOrdersAll extends AppCompatActivity {

    ListView listViewartist;
    List<UserItemName> orderList;

    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_orders_all);

        myRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://bookmypanditji-93e79.firebaseio.com/").child("OrderSummary");

        listViewartist = (ListView) findViewById(R.id.listViewArtist);

        orderList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orderList.clear();
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {
                    UserItemName artist = artistSnapshot.getValue(UserItemName.class);
                    orderList.add(artist);

                }
                UsersOrderList adapter = new UsersOrderList(UsersOrdersAll.this, orderList);
                listViewartist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}