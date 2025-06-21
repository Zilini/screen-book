package com.aluracursos.screenbook.repository;

import com.aluracursos.screenbook.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Autor findByNombre(String nombre);

}
