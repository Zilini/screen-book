package com.aluracursos.screenbook.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private String idiomas;
    Double descargas;

    public Libro (DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.autor = String.valueOf(datosLibros.autor());
        this.idiomas = String.valueOf(datosLibros.idiomas());
        this.descargas = datosLibros.descargas();
    }

    @Override
    public String toString() {
        return
                "Titulo: " + titulo + '\'' +
                ", Autor: " + autor + '\'' +
                ", Idiomas: " + idiomas + '\'' +
                ", Descargas: " + descargas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }
}
