package com.example.listadoenemigos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MostrarEnemigo extends AppCompatActivity {

    TextView nombreTV, sexoTV, descripcionTV;
    Button atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_enemigo);

        nombreTV = findViewById(R.id.nombreTV);
        sexoTV = findViewById(R.id.sexoTV);
        descripcionTV = findViewById(R.id.descripcionTV);
        atras = findViewById(R.id.atras);

        //Este onclick hará que volvamos a la pantalla anterior
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MostrarEnemigo.this, MainActivity.class);
                startActivity(i);
            }
        });


        Intent recibirIntent = getIntent();
        String nombreEnemigo = recibirIntent.getStringExtra("nombreEnemigo");
        String sexoEnemigo = recibirIntent.getStringExtra("sexoEnemigo");
        String descripcionEnemigo = recibirIntent.getStringExtra("descripcionEnemigo");


        nombreTV.setText("Nombre: " + nombreEnemigo);
        sexoTV.setText("Sexo: " + sexoEnemigo);
        descripcionTV.setText("Motivo de la ofensa: " + descripcionEnemigo);


    }
}