package com.alim.relhan.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alim.relhan.Helper.FbsDatabaseUserHelper;
import com.alim.relhan.MyObject.User;
import com.alim.relhan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    EditText ed_name,ed_email,ed_password,ed_conpassword;
    Button btn_register;

    ActionBar actionBar;

    SharedPreferences sharedPreferences;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    String RegisterSucces="";
    String RegisterNotSucces="";
    String checkEmailPassword="";
    String errorFields="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.RegisBar));

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        ed_name = (EditText) findViewById(R.id.ed_name);
        ed_email = (EditText) findViewById(R.id.ed_email);
        ed_password = (EditText) findViewById(R.id.ed_password);
        ed_conpassword = (EditText) findViewById(R.id.ed_conpasswpord);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);

        settext();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(RegisterActivity.this);




        LoginActivity.mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("Testlog","Login RegActivity");

                } else {
                    Log.d("Testlog","Logout RegActivity");
                }

            }
        };

    }

    @Override
    public void onClick(View v) {

       final String email = ed_email.getText().toString();
       final String password = ed_password.getText().toString();


        if(ed_password.getText().toString().equals(ed_conpassword.getText().toString()) && !ed_name.getText().toString().equals("") && !ed_email.getText().toString().equals("")) {
            LoginActivity.mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, RegisterSucces, Toast.LENGTH_SHORT).show();
                        writeprefences(email,password);
                        writedatabase(new User(email,password,ed_name.getText().toString(),""));
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, RegisterNotSucces, Toast.LENGTH_SHORT).show();
                        Toast.makeText(RegisterActivity.this, checkEmailPassword, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(RegisterActivity.this, errorFields, Toast.LENGTH_LONG).show();
        }

    }

    public void writeprefences(String email,String password)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", email);
        editor.putString("Password", password);
        editor.commit();
    }

    public void writedatabase(User user) {
        new FbsDatabaseUserHelper().writeuser(user, new FbsDatabaseUserHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<User> users, List<String> keys) {

            }

            @Override
            public void DataIsInserted() {
                Log.d("Testlog", "UserAddtoDatabase");
            }

        });
    }

    public void settext()
    {
        if(MainActivity.language.equals("ru"))
        {
            ed_name.setHint(getString(R.string.Log_Name_ru));
            ed_password.setHint(getString(R.string.Password_ru));
            ed_conpassword.setHint(getString(R.string.Con_Password_ru));
            btn_register.setText(getString(R.string.Register_ru));
            RegisterSucces = getString(R.string.RegisterSucces_ru);
            RegisterNotSucces = getString(R.string.RegisterNotSucces_ru);
            checkEmailPassword = getString(R.string.checkEmailPassword_ru);
            errorFields = getString(R.string.ErFileds_ru);
        }else{
            ed_name.setHint(getString(R.string.Log_Name_kz));
            ed_password.setHint(getString(R.string.Password_kz));
            ed_conpassword.setHint(getString(R.string.Con_Password_kz));
            btn_register.setText(getString(R.string.Register_kz));
            RegisterSucces = getString(R.string.RegisterSucces_kz);
            RegisterNotSucces = getString(R.string.RegisterNotSucces_kz);
            checkEmailPassword = getString(R.string.checkEmailPassword_kz);
            errorFields = getString(R.string.ErFileds_kz);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
