package com.example.ict3214_mobile_application_development_mini_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {

    TextView tvProfileName, tvProfileEmail, tvProfileHeight, tvProfileWeight;
    ImageButton btnBack;
    TextView btnLogout;
    DatabaseHelper myDb;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Initialize UI elements
        tvProfileName = findViewById(R.id.tvProfileName);
        tvProfileEmail = findViewById(R.id.tvProfileEmail);
        tvProfileHeight = findViewById(R.id.tvProfileHeight);
        tvProfileWeight = findViewById(R.id.tvProfileWeight);
        btnBack = findViewById(R.id.btnBack);
        btnLogout = findViewById(R.id.btnLogout);

        myDb = new DatabaseHelper(this);
        userEmail = getIntent().getStringExtra("LOGGED_IN_EMAIL");

        // Back button functionality
        btnBack.setOnClickListener(v -> finish());

        // Logout functionality
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfileActivity.this, loginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        if (userEmail != null) {
            loadUserProfile();
        }
    }

    private void loadUserProfile() {
        Cursor res = myDb.getUserDetails(userEmail);

        if (res != null && res.moveToFirst()) {
            String name = res.getString(0);
            String height = res.getString(1);
            String weight = res.getString(2);

            tvProfileName.setText(name);
            tvProfileEmail.setText(userEmail);
            tvProfileHeight.setText(height + " cm");
            tvProfileWeight.setText(weight + " kg");

            res.close();
        }
    }
}
