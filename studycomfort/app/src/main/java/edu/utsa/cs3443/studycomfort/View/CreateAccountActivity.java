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

public class CreateAccountActivity extends AppCompatActivity {

    private final AuthController auth = new AuthController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        EditText etFirst = findViewById(R.id.etFirst);
        EditText etLast = findViewById(R.id.etLast);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);

        Button btn = findViewById(R.id.btnDoCreate);
        btn.setOnClickListener(v -> {
            try {
                User u = auth.register(
                        etFirst.getText().toString(),
                        etLast.getText().toString(),
                        etEmail.getText().toString(),
                        etPassword.getText().toString()
                );
                Toast.makeText(this, "Account created.", Toast.LENGTH_SHORT).show();
                // Sent straight to questionnaire after creating account
                Intent i = new Intent(this, QuestionnaireActivity.class);
                i.putExtra("userId", u.getUserId());
                startActivity(i);
                finish();
            } catch (IllegalArgumentException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            } catch (Exception ex) {
                Toast.makeText(this, "Failed to create account.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
