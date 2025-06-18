package com.aluracursos.screenbook.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private String fechaNacimiento;
    private String fechaMuerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor () {}

    public Autor (DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        if (datosAutor.fechaNacimiento() == null) {
            this.fechaNacimiento = "Desconocida";
        } else {
            this.fechaNacimiento = datosAutor.fechaNacimiento();
        }

        if (datosAutor.fechaMuerte() == null) {
            this.fechaMuerte = "Desconocida";
        } else {
            this.fechaMuerte = datosAutor.fechaMuerte();
        }
    }

    public void  agregarLibro(Libro libro) {
        this.libros.add((libro));
        libro.setAutor(this);
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(String fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return """
                Autor: %s
                Fecha de Nacimiento: %s
                Fecha de Fallecimiento: %s
                """.formatted(nombre, fechaNacimiento, fechaMuerte);
    }
}
