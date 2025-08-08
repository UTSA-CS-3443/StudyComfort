package edu.utsa.cs3443.studycomfort.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.studycomfort.Controller.AuthController;
import edu.utsa.cs3443.studycomfort.Model.User;
import edu.utsa.cs3443.studycomfort.R;

public class SignInActivity extends AppCompatActivity {

    private final AuthController auth = new AuthController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btn = findViewById(R.id.btnSignIn);

        btn.setOnClickListener(v -> {
            try {
                User u = auth.signIn(etEmail.getText().toString(), etPassword.getText().toString());
                if (u != null) {
                    Intent i = new Intent(SignInActivity.this, RecommendationsActivity.class);
                    i.putExtra("userId", u.getUserId());
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            } catch (IllegalArgumentException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}