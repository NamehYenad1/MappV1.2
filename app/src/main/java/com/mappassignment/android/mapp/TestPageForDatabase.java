package com.mappassignment.android.mapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TestPageForDatabase extends AppCompatActivity {
        private Button mGetKey;
        private EditText mKey;
    private FirebaseAuth mAuth;
    private static final String TAG = "TestDatabase";
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_page_for_database);
        mAuth = FirebaseAuth.getInstance();


        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mKey=(EditText) findViewById(R.id.KeyText);
        mGetKey = (Button) findViewById(R.id.GetTheKey);

        mGetKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add in the userId here too, get currentUser doesnt give Uid (note2self)
                mDatabaseReference.child(mAuth.getCurrentUser().getUid()).child("HashedPrivateKey").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = (String) dataSnapshot.getValue();
                        Log.d(TAG,"value of hased private key is:"+value);
                        Log.d(TAG,"mAuth:"+mAuth.toString());
                        Log.d(TAG,"mAuthUser:"+mAuth.getCurrentUser().toString());
                        Log.d(TAG, "DatabaseReference:"+mDatabaseReference.toString());
                        // do your stuff here with value

                    }

                    @Override
                    public void onCancelled(DatabaseError firebaseError) {

                    }
                });

            }
        });
    }
}
