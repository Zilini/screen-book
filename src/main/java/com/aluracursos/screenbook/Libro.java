package com.aluracursos.screenbook;

import com.aluracursos.screenbook.model.DatosLibros;

public class Libro {
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
