package com.aluracursos.screenbook.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosAutor(
        @JsonAlias("name") String nombre
) {
}
