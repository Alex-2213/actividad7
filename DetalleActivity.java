package com.example.actividad7;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetalleActivity extends AppCompatActivity {
    TextView textId, textNombre, textTelefono, textFechaNacimiento, textEstatura, textSexo, textEdad, textPrimerApellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        setTitle("Detalle usuario");

        textId = findViewById(R.id.textId);
        textNombre = findViewById(R.id.textNombre);
        textTelefono = findViewById(R.id.textTelefono);
        textFechaNacimiento = findViewById(R.id.textFechaNacimiento);
        textEstatura = findViewById(R.id.textEstatura);
        textSexo = findViewById(R.id.textSexo);
        textEdad = findViewById(R.id.textEdad);
        textPrimerApellido = findViewById(R.id.textPrimerApellido);

        Bundle objeto = getIntent().getExtras();
        if (objeto != null) {
            Usuarios usu = (Usuarios) objeto.getSerializable("usuario");
            assert usu != null;
            textId.setText("Id: " + String.valueOf(usu.getId()));
            textNombre.setText(usu.getNombre());
            textTelefono.setText("Telefono: " + usu.getTelefono());
            textPrimerApellido.setText(usu.getPrimerApellido());
            textSexo.setText("Sexo: " + usu.getSexo());
            textEdad.setText("Edad: " + String.valueOf(usu.getEdad()));
            textEstatura.setText("Estatura: " + String.valueOf(usu.getEstatura()));
            textFechaNacimiento.setText("F. Nacimiento: " + formatearFecha(usu.getFechaNacimiento()));
        }
    }

    private String formatearFecha(String fechaTexto) {
        try {
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("ddmmyyyy", Locale.getDefault());
            Date fecha = formatoEntrada.parse(fechaTexto);

            SimpleDateFormat formatoSalida = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            return formatoSalida.format(fecha);
        } catch (Exception e) {
            e.printStackTrace();
            return fechaTexto;
        }
    }
}
