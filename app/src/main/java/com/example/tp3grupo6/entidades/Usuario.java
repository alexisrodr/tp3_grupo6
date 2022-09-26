package com.example.tp3grupo6.entidades;

import java.io.Serializable;

public class Usuario implements Serializable {
    private Integer id;
    private String nombre;
    private String correo;
    private String password;

    public Usuario(Integer id,String nombre,String correo,String password){
        this.id=id;
        this.nombre=nombre;
        this.correo=correo;
        this.password=password;
    }
    public Usuario(String nombre,String correo,String password){
        this.nombre=nombre;
        this.correo=correo;
        this.password=password;
    }
    public Usuario(){}

    public Integer getId() { return id; }

    public String getNombre() { return nombre; }

    public String getCorreo() { return correo; }

    public String getPassword() { return password; }

    public void setId(Integer id) { this.id = id; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public void setCorreo(String correo) { this.correo = correo; }

    public void setPassword(String password) { this.password = password; }
}
