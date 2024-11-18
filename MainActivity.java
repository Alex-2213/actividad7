package com.example.actividad7;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText campoId, campoNombre, campoTelefono, campoPrimerApellido, campoSexo, campoEdad, campoEstatura, campoFecha;
    Button insertar, buscar, editar, eliminar, ver, verListaOrdenadaNombre, buscarPorPrimerApellido, buscarPorEdad, buscarPorEstatura;
    Conectar conectar;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Control de usuarios");

        campoId = (EditText) findViewById(R.id.campoId);
        campoNombre = (EditText) findViewById(R.id.campoNombre);
        campoTelefono = (EditText) findViewById(R.id.campoTelefono);
        campoPrimerApellido = (EditText) findViewById(R.id.campoPrimerApellido);
        campoSexo = (EditText) findViewById(R.id.campoSexo);
        campoEdad = (EditText) findViewById(R.id.campoEdad);
        campoEstatura = (EditText) findViewById(R.id.campoEstatura);
        campoFecha = (EditText) findViewById(R.id.campoFecha);

        insertar = (Button) findViewById(R.id.insertar);
        buscar = (Button) findViewById(R.id.buscar);
        editar = (Button) findViewById(R.id.editar);
        eliminar = (Button) findViewById(R.id.eliminar);
        ver = (Button) findViewById(R.id.ver);
        verListaOrdenadaNombre = (Button) findViewById(R.id.verListaOrdenadaNombre);
        buscarPorPrimerApellido = (Button) findViewById(R.id.buscarPorPrimerApellido);
        buscarPorEdad = (Button) findViewById(R.id.buscarPorEdad);
        buscarPorEstatura = (Button) findViewById(R.id.buscarPorEstatura);

        insertar.setOnClickListener(this);
        buscar.setOnClickListener(this);
        editar.setOnClickListener(this);
        eliminar.setOnClickListener(this);
        ver.setOnClickListener(this);
        verListaOrdenadaNombre.setOnClickListener(this);
        buscarPorPrimerApellido.setOnClickListener(this);
        buscarPorEdad.setOnClickListener(this);
        buscarPorEstatura.setOnClickListener(this);

        //Conexion a la base de datos
        conectar = new Conectar(this, Variables.NOMBRE_BD, null, 1);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.insertar) {
            insertar();
        }
        if (v.getId() == R.id.ver) {
            i = new Intent(this, ListaActivity.class);
            startActivity(i);
        }
        if (v.getId() == R.id.buscar) {
            buscar();
        }
        if (v.getId() == R.id.editar) {
            editar();
        }
        if (v.getId() == R.id.eliminar) {
            eliminar();
        }
        if (v.getId() == R.id.verListaOrdenadaNombre) {
            listaOrdenadaNombre();
        }
        if (v.getId() == R.id.buscarPorPrimerApellido) {
            buscarApellido();
        }
        if (v.getId() == R.id.buscarPorEdad) {
            buscarEdad();
        }
        if (v.getId() == R.id.buscarPorEstatura) {
            buscarEstatura();
        }
    }

    private void buscarEstatura() {
        String estaturaBusqueda = campoEstatura.getText().toString();
        if (estaturaBusqueda.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese la estatura para buscar", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, ListaActivity.class);
        intent.putExtra("searchBy", "estatura");
        intent.putExtra("valorBusqueda", estaturaBusqueda);
        startActivity(intent);
    }

    private void buscarEdad() {
        String edadBusqueda = campoEdad.getText().toString();
        if (edadBusqueda.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese la edad para buscar", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, ListaActivity.class);
        intent.putExtra("searchBy", "edad");
        intent.putExtra("valorBusqueda", edadBusqueda);
        startActivity(intent);
    }

    private void buscarApellido() {
        String apellidoBusqueda = campoPrimerApellido.getText().toString();
        if (apellidoBusqueda.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese el primer apellido para buscar", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, ListaActivity.class);
        intent.putExtra("searchBy", "primerApellido");
        intent.putExtra("valorBusqueda", apellidoBusqueda);
        startActivity(intent);
    }

    private void listaOrdenadaNombre() {
        Intent intent = new Intent(this, ListaActivity.class);
        intent.putExtra("orderBy", "nombre");
        startActivity(intent);
    }

    private void eliminar() {
        SQLiteDatabase db = conectar.getWritableDatabase();
        String[] parametros = {campoId.getText().toString()};
        int n = db.delete(Variables.NOMBRE_TABLA, Variables.CAMPO_ID + "=?", parametros);
        Toast.makeText(this, "Usuarios eliminados: " + n, Toast.LENGTH_LONG).show();
        campoNombre.setText("");
        campoTelefono.setText("");
        campoId.setText("");
        campoPrimerApellido.setText("");
        campoSexo.setText("");
        campoEdad.setText("");
        campoEstatura.setText("");
        campoFecha.setText("");
        db.close();
    }

    private void editar() {
        SQLiteDatabase db = conectar.getWritableDatabase();
        String[] parametros = {campoId.getText().toString()};
        ContentValues valores = new ContentValues();
        valores.put(Variables.CAMPO_NOMBRE, campoNombre.getText().toString());
        valores.put(Variables.CAMPO_TELEFONO, campoTelefono.getText().toString());
        valores.put(Variables.CAMPO_PRIMER_APELLIDO, campoPrimerApellido.getText().toString());
        valores.put(Variables.CAMPO_SEXO, campoSexo.getText().toString());
        valores.put(Variables.CAMPO_EDAD, Integer.parseInt(campoEdad.getText().toString()));
        valores.put(Variables.CAMPO_ESTATURA, Float.parseFloat(campoEstatura.getText().toString()));
        valores.put(Variables.CAMPO_FECHA_NACIMIENTO, campoFecha.getText().toString());

        db.update(Variables.NOMBRE_TABLA, valores, Variables.CAMPO_ID + "=?", parametros);
        Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_SHORT).show();
        db.close();
    }

    private void buscar() {
        SQLiteDatabase db = conectar.getReadableDatabase();
        String[] parametros = {campoId.getText().toString()};
        String[] campos = {Variables.CAMPO_ID, Variables.CAMPO_NOMBRE, Variables.CAMPO_TELEFONO, Variables.CAMPO_PRIMER_APELLIDO, Variables.CAMPO_SEXO, Variables.CAMPO_EDAD, Variables.CAMPO_ESTATURA, Variables.CAMPO_FECHA_NACIMIENTO};
        try {
            Cursor cursor = db.query(Variables.NOMBRE_TABLA, campos, Variables.CAMPO_ID + "=?", parametros, null, null, null);
            if (cursor.moveToFirst()) {
                campoNombre.setText(cursor.getString(1));
                campoTelefono.setText(cursor.getString(2));
                campoPrimerApellido.setText(cursor.getString(3));
                campoSexo.setText(cursor.getString(4));
                campoEdad.setText(String.valueOf(cursor.getInt(5)));
                campoEstatura.setText(String.valueOf(cursor.getFloat(6)));
                campoFecha.setText(cursor.getString(7));

            } else {
                Toast.makeText(this, "No se encontraron resultados", Toast.LENGTH_LONG).show();
                campoNombre.setText("");
                campoTelefono.setText("");
                campoPrimerApellido.setText("");
                campoSexo.setText("");
                campoEdad.setText("");
                campoEstatura.setText("");
                campoFecha.setText("");
            }
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            campoNombre.setText("");
            campoTelefono.setText("");
            campoPrimerApellido.setText("");
            campoSexo.setText("");
            campoEdad.setText("");
            campoEstatura.setText("");
            campoFecha.setText("");
            throw new RuntimeException(e);
        }
        db.close();
    }

    private void insertar() {
        SQLiteDatabase db = conectar.getWritableDatabase();
        ContentValues valores = new ContentValues();
        try {
            valores.put(Variables.CAMPO_NOMBRE, campoNombre.getText().toString());
            valores.put(Variables.CAMPO_TELEFONO, campoTelefono.getText().toString());
            valores.put(Variables.CAMPO_PRIMER_APELLIDO, campoPrimerApellido.getText().toString());
            valores.put(Variables.CAMPO_SEXO, campoSexo.getText().toString());
            valores.put(Variables.CAMPO_EDAD, Integer.parseInt(campoEdad.getText().toString()));
            valores.put(Variables.CAMPO_ESTATURA, Float.parseFloat(campoEstatura.getText().toString()));
            valores.put(Variables.CAMPO_FECHA_NACIMIENTO, campoFecha.getText().toString());

            long n = db.insert(Variables.NOMBRE_TABLA, null, valores);
            Toast.makeText(this, "Usuario insertado: " + n, Toast.LENGTH_LONG).show();
            campoNombre.setText("");
            campoTelefono.setText("");
            campoPrimerApellido.setText("");
            campoSexo.setText("");
            campoEdad.setText("");
            campoEstatura.setText("");
            campoFecha.setText("");
        } catch (Exception e) {
            Toast.makeText(this, "Error al insertar: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
    }
}