package com.example.listadoenemigos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView enemyAdd;
    ArrayList<Enemigo> listaEnemigos;
    EditText nombreET, descripcionET;
    RadioButton hombre, mujer;
    Button agregarEnemigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enemyAdd = findViewById(R.id.enemyAdd);
        nombreET = findViewById(R.id.nombreET);
        hombre = findViewById(R.id.hombre);
        mujer = findViewById(R.id.mujer);
        descripcionET = findViewById(R.id.descripcionET);
        agregarEnemigo = findViewById(R.id.agregarenemigo);
        agregarEnemigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoNombre = nombreET.getText().toString();
                String sexoEnemigo = "";
                String textoDescripcion = descripcionET.getText().toString();

                if (hombre.isChecked()) {
                    sexoEnemigo = "Hombre";
                } else sexoEnemigo = "Mujer";
                if (nombreET.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Campo nombre vacío", Toast.LENGTH_SHORT).show();
                } else if (hombre.isChecked() ^ !mujer.isChecked()) {
                    Toast.makeText(MainActivity.this, "Campo de genero vacío", Toast.LENGTH_SHORT).show();
                } else {
                    listaEnemigos.add(new Enemigo(textoNombre, sexoEnemigo, textoDescripcion));

                    ArrayAdapter adapter = (ArrayAdapter) enemyAdd.getAdapter();
                    adapter.notifyDataSetChanged();
                }

                //Con esto ocultaremos el teclado cuando se añada el enemigo
                InputMethodManager hideKeyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                hideKeyboard.hideSoftInputFromWindow(enemyAdd.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
            }
        });
        listaEnemigos = new ArrayList<>();
        AdapterEnemigo adapter = new AdapterEnemigo(this, R.layout.item, listaEnemigos);
        enemyAdd.setAdapter(adapter);

        //Este onclick nos hará añadir un enemigo a la lista
        enemyAdd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, MostrarEnemigo.class);

                intent.putExtra("nombreEnemigo", listaEnemigos.get(position).nombre);
                intent.putExtra("sexoEnemigo", listaEnemigos.get(position).genero);
                intent.putExtra("descripcionEnemigo", listaEnemigos.get(position).descripcion);

                Enemigo enemigo = (Enemigo) parent.getItemAtPosition(position);

                startActivity(intent);
            }
        });


    }

}