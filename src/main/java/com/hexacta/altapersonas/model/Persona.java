package com.hexacta.altapersonas.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String direccion;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date nacimiento;
    private int edad;
    private String categoria;

    private static final int LIMITE_NINO = 11;
    private static final int LIMITE_JOVEN = 18;
    private static final int LIMITE_ADULTO = 80;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public void categorizar() {
        if (this.edad<0){
            throw new ArithmeticException();
        }
        String categoria = "Niños";
        if (this.edad >= LIMITE_NINO && this.edad < LIMITE_JOVEN) {
            categoria = "Adolescentes";
        }
        else if (this.edad < LIMITE_ADULTO && this.edad >= LIMITE_JOVEN) {
            categoria = "Adultos";
        }
        else if (this.edad >= LIMITE_ADULTO){
            categoria = "Octogenario";
        }
        this.setCategoria(categoria);
    }
}
