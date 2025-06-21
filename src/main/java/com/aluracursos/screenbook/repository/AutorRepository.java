package com.aluracursos.screenbook.repository;

import com.aluracursos.screenbook.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Autor findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :year AND (a.fechaMuerte IS NULL OR a.fechaMuerte >= :year)")
    List<Autor> findAutoresVivosEnAnio(String year);

}
