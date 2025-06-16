package com.aluracursos.screenbook.principal;

import com.aluracursos.screenbook.model.Libro;
import com.aluracursos.screenbook.model.DatosLibros;
import com.aluracursos.screenbook.repository.LibroRepository;
import com.aluracursos.screenbook.service.ConsumoAPI;
import com.aluracursos.screenbook.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    Scanner teclado = new Scanner(System.in);
    private static final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorio;

    public Principal(LibroRepository repository) {
        this.repositorio = repository;
    }

    public void mostrarMenu () {

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                --------------------
                
                1 - Buscar Libro.
                
                0 - Salir.
                
                --------------------
                """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 0:
                    System.out.println("Gracias por visitar ScreenBook.");
                    System.out.println("Cerrando aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida. Selecciona un opción válida.");
            }
        }
    }

    private DatosLibros getDatosLibros () {
        System.out.println("Escribe el nombre del Libro que quieres buscar: ");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreLibro.toLowerCase().replace(" ", "%20"));
        System.out.println(json);
        DatosLibros datos = conversor.obtenerDatos(json, DatosLibros.class);
        return datos;
    }

    private void buscarLibroWeb() {
        DatosLibros datos = getDatosLibros();
        Libro libro = new Libro(datos);
        repositorio.save(libro);
        System.out.println(datos);
    }


}
