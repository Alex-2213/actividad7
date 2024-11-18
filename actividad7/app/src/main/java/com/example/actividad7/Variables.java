package com.example.actividad7;

public class Variables {
    public static final String NOMBRE_BD = "bd_usuarios";
    public static final String NOMBRE_TABLA = "usuarios";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_TELEFONO = "telefono";
    public static final String CAMPO_PRIMER_APELLIDO = "primerApellido";
    public static final String CAMPO_SEXO = "sexo";
    public static final String CAMPO_EDAD = "edad";
    public static final String CAMPO_ESTATURA = "estatura";
    public static final String CAMPO_FECHA_NACIMIENTO = "fechaNacimiento";

    public static final String CREAR_TABLA = "CREATE TABLE IF NOT EXISTS " + NOMBRE_TABLA + " (" +
            CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CAMPO_NOMBRE + " TEXT, " +
            CAMPO_TELEFONO + " TEXT, " +
            CAMPO_PRIMER_APELLIDO + " TEXT, " +
            CAMPO_SEXO + " TEXT, " +
            CAMPO_EDAD + " INTEGER, " +
            CAMPO_ESTATURA + " REAL, " +
            CAMPO_FECHA_NACIMIENTO + " TEXT)";

    public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS " + NOMBRE_TABLA;

}
