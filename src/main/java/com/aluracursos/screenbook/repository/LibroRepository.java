package com.aluracursos.screenbook.repository;

import com.aluracursos.screenbook.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByTituloContainsIgnoreCase(String titulo);
}
