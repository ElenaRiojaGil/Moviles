package com.example.braveheart;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
private Button miBotonA;
private Button miBotonB;
private Button miBotonC;
private Button miBotonD;
private Button miBotonE;
private Button miBotonF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//InicializarComponentes
        inicializarComponentes();
        miBotonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Boton A", Toast.LENGTH_SHORT).show();
                
            }
        });
    }
    private void inicializarComponentes(){
        miBotonA=findViewById(R.id.btnA);
        miBotonB=findViewById(R.id.btnB);
        miBotonC=findViewById(R.id.btnC);
        miBotonD=findViewById(R.id.btnD);
        miBotonE=findViewById(R.id.btnE);
        miBotonF=findViewById(R.id.btnF);
    }
}