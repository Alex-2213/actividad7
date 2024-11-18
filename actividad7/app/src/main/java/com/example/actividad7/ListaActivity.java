package com.example.actividad7;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    ArrayList<String> listausuarios;
    ArrayList<Usuarios> datosusuario; // ArrayList de la clase Usuarios.java
    Conectar conectar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        setTitle("Ver usuarios");

        lista = findViewById(R.id.lista);

        mostrar();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listausuarios);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(this);
    }

    private void mostrar() {
        conectar = new Conectar(this, Variables.NOMBRE_BD, null, 2);
        SQLiteDatabase db = conectar.getReadableDatabase();
        datosusuario = new ArrayList<>();
        listausuarios = new ArrayList<>();

        String searchBy = getIntent().getStringExtra("searchBy");
        String valorBusqueda = getIntent().getStringExtra("valorBusqueda");
        String orderBy = getIntent().getStringExtra("orderBy");

        String query = "SELECT * FROM " + Variables.NOMBRE_TABLA;
        String[] argumentos = null;

        if (searchBy != null) {
            query += " WHERE " + searchBy + " = ?";
            argumentos = new String[]{valorBusqueda};
        } else if (orderBy != null) {
            query += " ORDER BY " + orderBy;
        }

        Cursor cursor = db.rawQuery(query, argumentos);
        if(cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Usuarios usuario = new Usuarios(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getFloat(6),
                        cursor.getString(7)
                );
                datosusuario.add(usuario);
                listausuarios.add(usuario.getId() + " - " + usuario.getNombre() + " " + usuario.getPrimerApellido());
            }
            cursor.close();
        } else {
            Toast.makeText(this, "No hay usuarios registrados", Toast.LENGTH_SHORT).show();
        }
        db.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listausuarios);
        lista.setAdapter(adapter);
    }

    private void agregarlista() {
        listausuarios = new ArrayList<>();
        for (int i = 0; i < datosusuario.size(); i++) {
            Usuarios usuario = datosusuario.get(i);
            listausuarios.add(usuario.getId() + " - " + usuario.getNombre() + " " + usuario.getPrimerApellido());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            Usuarios usuario = datosusuario.get(position);
            Intent ii = new Intent(this, DetalleActivity.class);
            Bundle b = new Bundle();
            b.putSerializable("usuario", usuario);
            ii.putExtras(b);
            startActivity(ii);
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
