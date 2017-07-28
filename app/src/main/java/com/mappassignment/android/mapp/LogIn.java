package com.mappassignment.android.mapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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




public class LogIn extends AppCompatActivity {

    private Button mLoginButton;
    private Button mSignUpButton;
    private EditText mPasswordInput;
    private EditText mEmailInput;
    private static final String TAG = "LOGIN";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

         mAuth=FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mSignUpButton =(Button) findViewById(R.id.SignUp);
        mLoginButton =(Button) findViewById(R.id.login);
        mSignUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogIn.this, SignUp.class);
                startActivity(i);

            }}
        );

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPasswordInput=(EditText) findViewById(R.id.editText2);
                mEmailInput=(EditText) findViewById(R.id.editText);
                String mPassword=mPasswordInput.getText().toString();
                String mEmail=mEmailInput.getText().toString();

    checkCredentials(mEmail,mPassword);
}
        });
    }












    public void checkCredentials(String Email, String Password){

        mAuth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();



                            Intent i = new Intent(LogIn.this, notice.class);
            startActivity(i);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LogIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }




                    }
                });




}
    }