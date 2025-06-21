package com.aluracursos.screenbook.repository;

import com.aluracursos.screenbook.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    //Cargas los libros junto a los autores.
    @Query("SELECT l FROM Libro l JOIN FETCH l.autor")
    List<Libro> findAllWithAutor();

    //Busca por el libro por título y carga el autor del mismo.
    @Query("SELECT l FROM Libro l JOIN FETCH l.autor WHERE l.titulo LIKE %:titulo%")
    List<Libro> findByTituloContainsIgnoreCase(String titulo);
}
