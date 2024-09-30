package com.example.pasarparametrosentreactividades;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
private TextView txtUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        txtUser= findViewById(R.id.textUserPantalla2);
        Bundle variables =getIntent().getExtras();
        String user=variables.getString("USUARIO");
        txtUser.setText(user);
    }
}