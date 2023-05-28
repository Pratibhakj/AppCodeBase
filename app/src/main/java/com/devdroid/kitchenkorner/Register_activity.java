package com.devdroid.kitchenkorner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register_activity extends AppCompatActivity {
    TextView textView;
    EditText inputEmail,inputPassword,inputconfirmpassword;
    Button regbutton;
    String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textView=findViewById(R.id.newaccount);
        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);
        inputconfirmpassword=findViewById(R.id.inputconfirmpassword);
        regbutton=findViewById(R.id.regbutton);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register_activity.this,login_activity.class));
            }
        });
        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerforAuth();
            }
        });
    }

    private void PerforAuth() {
        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();
        String confirmpassword=inputconfirmpassword.getText().toString();

        if(!email.matches(emailpattern)){
            inputEmail.setError("Enter correct email");
        } else if (password.isEmpty()||password.length()<6) {
            inputPassword.setError("Enter correct password");

        } else if (!password.equals(confirmpassword)) {
            inputconfirmpassword.setError("Password did not matched");

        }else{
            progressDialog.setMessage("Please wait while registration");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(Register_activity.this, "Registration Sucessful", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Register_activity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(Register_activity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}