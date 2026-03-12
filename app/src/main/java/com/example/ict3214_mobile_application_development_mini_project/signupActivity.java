package com.example.ict3214_mobile_application_development_mini_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class signupActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etConfirmPassword;
    private Button btnSignupSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        // Handle window insets for edge-to-edge UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components by finding their IDs
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etSignupEmail);
        etPassword = findViewById(R.id.etSignupPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSignupSubmit = findViewById(R.id.btnSignupSubmit);

        // Handle registration button click event
        btnSignupSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input and remove leading/trailing spaces
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();

                //Validation
                if (name.isEmpty()) {
                    etName.setError("Full Name is required");
                    etName.requestFocus();
                    return; // Stop execution if validation fails
                }

                if (email.isEmpty()) {
                    etEmail.setError("Email is required");
                    etEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmail.setError("Please enter a valid email address");
                    etEmail.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    etPassword.setError("Password is required");
                    etPassword.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    etPassword.setError("Password must be at least 6 characters long");
                    etPassword.requestFocus();
                    return;
                }

                if (confirmPassword.isEmpty()) {
                    etConfirmPassword.setError("Please confirm your password");
                    etConfirmPassword.requestFocus();
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    etConfirmPassword.setError("Passwords do not match");
                    etConfirmPassword.requestFocus();
                    return;
                }

                // Database Operations
                // Initialize DatabaseHelper
                DatabaseHelper myDb = new DatabaseHelper(signupActivity.this);

                // Insert user data into the database
                boolean isInserted = myDb.insertData(name, email, password);

                if (isInserted) {
                    Toast.makeText(signupActivity.this, "Registration Step 1 Successful!", Toast.LENGTH_SHORT).show();

                    // Proceed to UserDetailsActivity for height and weight collection
                    Intent intent = new Intent(signupActivity.this, UserDetailsActivity.class);

                    // Pass the registered email to the next screen for database updates
                    intent.putExtra("USER_EMAIL", email);

                    startActivity(intent);

                    // Close the signup screen so the user cannot go back to it via the back button
                    finish();
                } else {
                    Toast.makeText(signupActivity.this, "Registration Failed! Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}