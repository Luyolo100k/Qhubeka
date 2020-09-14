  package com.fishfingers.qhubeka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.fishfingers.qhubeka.R.id.Password2;

  public class Register extends AppCompatActivity {
    EditText mName2,mEmail2,mPassword2;
    Button mRegisterBtn2;
    TextView mLoginBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mName2 = findViewById(R.id.Name);
        mEmail2 = findViewById(R.id.Email);
        mPassword2 = findViewById(R.id.Password2);
        mRegisterBtn2 = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.textView3);

        fAuth = FirebaseAuth.getInstance();
        //check if user is already logged in

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail2.getText().toString().trim();
                String password = mPassword2.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail2.setError("Enter Email");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword2.setError("Password required");
                }

                //Register the user

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "Succes User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(Register.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}