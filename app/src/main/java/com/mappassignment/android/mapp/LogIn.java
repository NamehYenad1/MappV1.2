package com.mappassignment.android.mapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.text.Text;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;




public class LogIn extends AppCompatActivity {
    private String mEmail="a@yahoo.com";
    private String mPassword="password";
    private Button mLoginButton;
    private TextView mSignUpButton;
    private EditText mPasswordInput;
    private EditText mEmailInput;
    private static final String TAG = "LOGIN";
    private String mPasswordInputFinal;
    private String mEmailInputFinal;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPasswordInput=(EditText) findViewById(R.id.editText2);
        mEmailInput=(EditText) findViewById(R.id.editText);
         mAuth=FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mSignUpButton =(TextView) findViewById(R.id.Register);
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
    checkCredentials();
}
        });
    }












    public void checkCredentials(){
        mAuth.signInWithEmailAndPassword(mEmailInput.toString(), mPasswordInput.toString())
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