package com.mappassignment.android.mapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class TestPageForDatabase extends AppCompatActivity {
        private Button mGetKey;
        private EditText mKey;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_page_for_database);
        mAuth = FirebaseAuth.getInstance();
        mKey=(EditText) findViewById(R.id.KeyText);
        mGetKey = (Button) findViewById(R.id.GetTheKey);

        mGetKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
