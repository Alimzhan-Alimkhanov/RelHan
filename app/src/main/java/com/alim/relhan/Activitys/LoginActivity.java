package com.alim.relhan.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alim.relhan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    public static FirebaseAuth mAuth;
    public static FirebaseAuth.AuthStateListener mAuthListener;

    SharedPreferences sharedPreferences;

    private EditText ed_email;
    private EditText ed_password;
    private Button btn_login;

    private TextView txt_noyac;


    String authsucces;
    String authNotsucces;
    String notFull;




    ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        actionBar = (ActionBar) getSupportActionBar();
        actionBar.setTitle(R.string.Authorize);

        if(MainActivity.language.equals("ru"))
        {
            authsucces = getString(R.string.Authorizesucces_ru);
            authNotsucces = getString(R.string.Authorizeonsucces_ru);
            notFull = "Не всё заполненно";
        }else
        {
            authsucces = getString(R.string.Authorizesucces_kz);
            authNotsucces = getString(R.string.Authorizeonsucces_kz);
            notFull = "Барлығы толық емес";
        }

        mAuth = FirebaseAuth.getInstance();

        ed_email = (EditText) findViewById(R.id.ed_email);
        ed_password = (EditText) findViewById(R.id.ed_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        txt_noyac = (TextView) findViewById(R.id.txv_noyaccount);

        settext();


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);


        txt_noyac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        btn_login.setOnClickListener(this);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("Testlog","Login LogActivity");

                } else {
                    Log.d("Testlog","Logout LogActivity");
                }

            }
        };


    }

    @Override
    public void onClick(View v) {

        final String email = ed_email.getText().toString();
        final String password = ed_password.getText().toString();

        if(!email.isEmpty() && !password.isEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, authsucces, Toast.LENGTH_SHORT).show();
                        writeprefences(email, password);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                        startActivity(intent);
                    } else
                        Toast.makeText(LoginActivity.this, authNotsucces, Toast.LENGTH_SHORT).show();

                }
            });
        }else {
            Toast.makeText(LoginActivity.this, notFull, Toast.LENGTH_SHORT).show();
        }

    }

    public void writeprefences(String email,String password)
    {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", email);
        editor.putString("Password", password);
        editor.commit();
    }

    public void settext()
    {
        if(MainActivity.language.equals("ru"))
        {
            ed_password.setHint(getString(R.string.Password_ru));
            btn_login.setText(getString(R.string.Login_ru));
            txt_noyac.setText(getString(R.string.NoyAccount_ru));
        }else {
            ed_password.setHint(getString(R.string.Password_kz));
            btn_login.setText(getString(R.string.Login_kz));
            txt_noyac.setText(getString(R.string.NoyAccount_kz));
        }

    }



    @Override
    public void onBackPressed() {
        if(false) {
            super.onBackPressed();
        }
    }
}
