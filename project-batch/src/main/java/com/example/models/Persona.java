package com.example.models;

public class Persona {

    public Persona(long id, String nombre, String correo, String ip) {
        this.id = id;          
        this.nombre = nombre;  
        this.correo = correo;   
        this.ip = ip;          
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    private long id;
    private String nombre, correo, ip;


}


