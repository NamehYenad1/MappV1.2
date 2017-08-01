package com.mappassignment.android.mapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Strife on 5/18/2017.
 */

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText mEmailField;
    private EditText  mStatusTextView;
    private EditText mDetailTextView;
    private Button mCreate;
    private EditText mPasswordField;
    private static final String TAG = "EmailPassword";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup);
        mEmailField=(EditText) findViewById(R.id.editText3);
        mPasswordField = (EditText) findViewById(R.id.editText4);
// ...
        mCreate = (Button) findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();
        Log.d(TAG,"success");
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
                Log.d(TAG,mEmailField.getText().toString() + mPasswordField.getText().toString());
            }
        } );}
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void createAccount(String email, String password){
       if( validateForm()) {
        final ProgressDialog progressDialog = ProgressDialog.show(SignUp.this, "Please wait...", "Processing request...", true);
           mAuth.createUserWithEmailAndPassword(email, password)
                   .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                            progressDialog.dismiss();
                           // If sign in fails, display a message to the user. If sign in succeeds
                           // the auth state listener will be notified and logic to handle the
                           // signed in user can be handled in the listener.
                           if (task.isSuccessful()) {
                               Intent i = new Intent(SignUp.this, notice.class);
                               startActivity(i);
                           }

                          else{
                               Toast.makeText(SignUp.this, "Authentication failed.",
                                       Toast.LENGTH_SHORT).show();
                           }

                           // ...
                       }
                   });
       }
        // [END create_user_with_email]
    }

    public Boolean validateForm(){
        boolean valid =true;
        String email=mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)){
            mEmailField.setError("Required");
            valid = false;

        }
        else {
            mEmailField.setError(null);
        }
        String password = mPasswordField.getText().toString();
        if(TextUtils.isEmpty(password)){
            mPasswordField.setError("Required");
            valid = false;
        }
        else{
            mPasswordField.setError(null);
        }
        return valid;
    }

    private void updateUI(FirebaseUser user){

        if(user!=null){
            mStatusTextView.setText(R.string.emailpasswordstatus +
                    user.getEmail() + user.isEmailVerified());
            mDetailTextView.setText(R.string.firebase_status_fmt + user.getUid());

        }
        mStatusTextView.setText(R.string.signed_out);
        mDetailTextView.setText(null);



    }



} 