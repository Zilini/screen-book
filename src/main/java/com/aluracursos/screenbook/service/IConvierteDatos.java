package com.aluracursos.screenbook.service;

public interface IConvierteDatos {
    <T> T obtenerDatos (String json, Class<T> clase);
}
