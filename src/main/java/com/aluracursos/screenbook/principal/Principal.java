package com.aluracursos.screenbook.principal;

import com.aluracursos.screenbook.dto.Datos;
import com.aluracursos.screenbook.dto.DatosAutor;
import com.aluracursos.screenbook.dto.DatosLibro;
import com.aluracursos.screenbook.model.*;
import com.aluracursos.screenbook.repository.AutorRepository;
import com.aluracursos.screenbook.repository.LibroRepository;
import com.aluracursos.screenbook.service.ConsumoAPI;
import com.aluracursos.screenbook.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    Scanner teclado = new Scanner(System.in);
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private String json;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void mostrarMenu () {

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                --------------------
                
                1 - Buscar  por título.
                2 - Ver los libros registrados.
                3 - Ver los autores registrados.
                4 - Ver los autores vivos en un determinado año.
                5 - Ver libros por idioma.
                
                0 - Salir.
                
                --------------------
                """;
            json = consumoAPI.obtenerDatos(URL_BASE);
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    buscarLibrosGuardados();
                    break;
                case 3:
                    buscarAutoresGuardados();
                    break;
                case 4:
                    buscarAutoresVivosEnAnho();
                case 0:
                    System.out.println("Gracias por visitar ScreenBook.");
                    System.out.println("Cerrando aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida. Selecciona un opción válida.");
            }
        }
    }

    private DatosLibro getDatosLibros () {
        System.out.println("Escribe el título del Libro que quieres buscar: ");
        var nombreLibro = teclado.nextLine();
        json = consumoAPI.obtenerDatos(URL_BASE +
                "?search=" +
                nombreLibro.toLowerCase().replace(" ", "%20"));
        Datos datosBuscados = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibro> libroBuscado = datosBuscados.resultados().stream()
                .filter(libro -> libro.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();

        if (libroBuscado.isPresent()) {
            return libroBuscado.get();
        } else {
            return null;
        }
    }

    private void buscarLibroPorTitulo() {
        DatosLibro datosLibros = getDatosLibros();

        if (datosLibros != null) {
            Libro libro;
            DatosAutor datosAutor = datosLibros.autor().get(0);
            Autor autorGuardado = autorRepository.findByNombre(datosAutor.nombre());
            if (autorGuardado != null) {
                libro = new Libro(datosLibros, autorGuardado);
            } else {
                Autor nuevoAutor = new Autor(datosAutor);
                libro = new Libro(datosLibros, nuevoAutor);
                autorRepository.save(nuevoAutor);
            }
            try {
                libroRepository.save(libro);
                System.out.println(libro);
            } catch (Exception e) {
                System.out.println("No se puede registrar el mismo libro dos veces.");
            }
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private void buscarLibrosGuardados() {
        List<Libro> libro = libroRepository.findAllWithAutor();

        if (libro.isEmpty()) {
            System.out.println("Aún no se ha guardado ningún libro en la base de datos.");
        }

        libro.stream()
                .sorted((l1, l2) -> l1.getAutor().getNombre().compareToIgnoreCase(l2.getAutor().getNombre()))
                .forEach(System.out::println);
    }

    private void buscarAutoresGuardados() {
        List<Autor> autor = autorRepository.findAll();

        if (autor.isEmpty()) {
            System.out.println("Aún no se ha registrado ningún autor.");
        }

        autor.stream()
                .sorted((a1, a2) -> a1.getNombre().compareToIgnoreCase(a2.getNombre()))
                .forEach(System.out::println);
    }

    private void buscarAutoresVivosEnAnho() {
        System.out.println("Ingresa el año para ver los autores vivos: ");
        var anho = teclado.nextLine();
        List<Autor> autoresVivos = autorRepository.findAutoresVivosEnAnio(anho);
        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontro ningún autor vivo para el año %s.".formatted(anho));
        } else {
            System.out.println("Autores que vivieron en el año %s.".formatted(anho));
            autoresVivos.forEach(System.out::println);
            System.out.println("----------------------------------------\n");
        }
    }
}