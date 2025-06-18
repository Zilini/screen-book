package com.aluracursos.screenbook.principal;

import com.aluracursos.screenbook.model.*;
import com.aluracursos.screenbook.repository.AutorRepository;
import com.aluracursos.screenbook.repository.LibroRepository;
import com.aluracursos.screenbook.service.ConsumoAPI;
import com.aluracursos.screenbook.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    Scanner teclado = new Scanner(System.in);
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private String json;
    private List<Libro> libro;
    private Optional<Autor> autor;
    private DatosAutor datosAutor;


    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void mostrarMenu () {

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                --------------------
                
                1 - Buscar Libro.
                2 - Ver los libros buscados.
                
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
                case 2:
                    buscarLibrosGuardados();
                    break;
                case 3:
                    buscarLibrosPorAutor();
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
        json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.toLowerCase().replace(" ", "%20"));
        Datos datos = conversor.obtenerDatos(json, Datos.class);

        if (datos.resultados().isEmpty()) {
            System.out.println("El libro que intentas buscar, no se encuentra disponible");
            return null;
        }

        return datos.resultados().get(0);
    }

    private void buscarLibroWeb() {
        DatosLibros datos = getDatosLibros();
        if (datos == null) {
            return;
        }
        libro = libroRepository.findByTituloContainsIgnoreCase(datos.titulo());

        if (!libro.isEmpty()) {
            System.out.println(datos.titulo() + " ya se encuentra registrado.");
            for (Libro libro : libro) {
                System.out.println(libro);
            }
        } else {
            Autor autorDeLibro = null;
            if (autorDeLibro != null && datos.autor().isEmpty()) {
                datosAutor = datos.autor().get(0);

                autor = autorRepository.findByNombreContainsIgnoreCase(datosAutor.nombre());

                if (autor.isPresent()) {
                    autorDeLibro = autor.get();
                } else {
                    autorDeLibro = new Autor(datosAutor);
                    autorRepository.save(autorDeLibro);
                }
            } else {
                System.out.println("No se encontro ninguna información del autor de el libro que buscaste");
            }

            Libro libro = new Libro(datos);
            if (autorDeLibro != null) {
                libro.setAutor(autorDeLibro);
                autorDeLibro.agregarLibro(libro);
            }
//
            libroRepository.save(libro);
            System.out.println("El libro " + datos.titulo() + " ha sido guardado.");
            System.out.println(libro);
        }
    }

    private void buscarLibrosGuardados() {
        libro = libroRepository.findAll();

        libro.stream()
                .forEach(System.out::println);
    }

    private void buscarLibrosPorAutor() {
        System.out.println("Escribe el nombre del autor del que quieres ve sus libros: ");
        var nombreAutor = teclado.nextLine();
        autor = autorRepository.findByNombreContainsIgnoreCase(nombreAutor);
        if (autor.isPresent()) {
            System.out.println(autor.get());
            System.out.println();
        }
    }
}
