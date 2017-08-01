package com.mappassignment.android.mapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecondAutentication extends AppCompatActivity {
    private Button mEnter;
    private EditText mKeyInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mEnter=(Button) findViewById(R.id.EnterKey);
        mKeyInput=(EditText) findViewById(R.id.KeyInput);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_autentication);
    }

    public String Hash(String PrivateKey, String   salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(PrivateKey.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }



}
