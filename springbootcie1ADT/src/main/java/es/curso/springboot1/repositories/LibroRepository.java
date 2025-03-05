package es.curso.springboot1.repositories;

import java.util.List;

import es.curso.springboot1.negocio.Libro;

public interface LibroRepository {

    List<Libro> buscarTodos ();

    void insertar (Libro libro);

    void borrar (Libro libro);

}
