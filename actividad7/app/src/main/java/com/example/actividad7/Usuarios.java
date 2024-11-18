package com.example.actividad7;

import java.io.Serializable;

public class Usuarios implements Serializable {
    private Integer id;
    private String nombre;
    private String telefono;
    private String primerApellido;
    private String sexo;
    private Integer edad; // Cambiado a tipo int para manejarlo correctamente como número
    private Float estatura; // Cambiado a tipo float para manejarlo como número decimal
    private String fechaNacimiento;

    // Constructor completo
    public Usuarios(Integer id, String nombre, String telefono, String primerApellido, String sexo, Integer edad, Float estatura, String fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.primerApellido = primerApellido;
        this.sexo = sexo;
        this.edad = edad;
        this.estatura = estatura;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Usuarios() {}

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public Integer  getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Float getEstatura() {
        return estatura;
    }

    public void setEstatura(Float estatura) {
        this.estatura = estatura;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
