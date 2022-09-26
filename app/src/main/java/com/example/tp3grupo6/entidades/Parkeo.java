package com.example.tp3grupo6.entidades;

import java.io.Serializable;

public class Parkeo implements Serializable {
    private Integer id;
    private String matricula;
    private Integer minutos;
    private Integer usuario_id;

    public Parkeo(){}

    public Parkeo(String matricula, Integer minutos, Integer usuario_id) {
        this.matricula = matricula;
        this.minutos = minutos;
        this.usuario_id = usuario_id;
    }

    public Parkeo(Integer id, String matricula, Integer minutos, Integer usuario_id) {
        this.id = id;
        this.matricula = matricula;
        this.minutos = minutos;
        this.usuario_id = usuario_id;
    }

    public Integer getId() { return id; }

    public String getMatricula() { return matricula; }

    public Integer getMinutos() { return minutos; }

    public Integer getUsuario_id() { return usuario_id; }

    public void setId(Integer id) { this.id = id; }

    public void setMatricula(String matricula) { this.matricula = matricula; }

    public void setMinutos(Integer minutos) { this.minutos = minutos; }

    public void setUsuario_id(Integer usuario_id) {this.usuario_id = usuario_id; }
}
