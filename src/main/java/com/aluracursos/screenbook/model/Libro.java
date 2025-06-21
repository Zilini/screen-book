package com.aluracursos.screenbook.model;

import com.aluracursos.screenbook.dto.DatosLibro;
import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    private Autor autor;
    private String idiomas;
    private Double descargas;

    public Libro (){}

    public Libro (DatosLibro datosLibros, Autor autor) {
        this.titulo = datosLibros.titulo();
        this.autor = autor;
        this.idiomas = datosLibros.idiomas().get(0);
        this.descargas = datosLibros.descargas();
    }

    @Override
    public String toString() {
        //Se asegura de que el autor no vaya a ser nulo
        String nombreAutor = (autor != null) ? autor.getNombre() : "Desconocido";

        return """
                ------ LIBRO ------
                Titulo: %s
                Autor: %s
                Idioma: %s
                Descargas: %.0f
                ------------------
                """.formatted(titulo, nombreAutor, idiomas, descargas);
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
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
