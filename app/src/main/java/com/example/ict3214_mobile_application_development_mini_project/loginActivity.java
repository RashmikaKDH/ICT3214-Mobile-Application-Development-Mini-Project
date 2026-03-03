package com.example.ict3214_mobile_application_development_mini_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class loginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLoginSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //bind views
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLoginSubmit = findViewById(R.id.btnLoginSubmit);

        btnLoginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validation check add karnna one...

                // Type karala thiyena akuru tika allaganna (String walata ganna)
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Email eka hari password eka hari his nam error ekak pennanna
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(loginActivity.this, "Please enter Email and Password", Toast.LENGTH_SHORT).show();
                } else {
                    // Dekama gahala nam, type karapu email eka message ekak widihata pennanna
                    Toast.makeText(loginActivity.this, "Success! Email: " + email, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}