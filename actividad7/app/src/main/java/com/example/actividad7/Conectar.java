package com.example.actividad7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conectar extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    public Conectar(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, DATABASE_VERSION); // Cambiado a DATABASE_VERSION constante
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Variables.CREAR_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Variables.ELIMINAR_TABLA);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Variables.ELIMINAR_TABLA);
        onCreate(db);
    }
}
