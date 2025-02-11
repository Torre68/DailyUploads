package es.curso.libro.repositories;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import es.curso.libro.negocio.Libro;
@Repository
public class LibroRepositoryMemoria {

    List<Libro> libros = new ArrayList<>();
    
    public LibroRepositoryMemoria () {

        libros.add(new Libro("12a", "Dune", "F. Herbert"));
        libros.add(new Libro("34b", "Harry Potter", "J. K. Rowling"));
        libros.add(new Libro("56c", "It", "S. King"));
        
    }
    
    public void borrarLibro(String isbn) {
        Libro l = new Libro(isbn);
        libros.remove(l);
        
    }

    
    public void insertarLibro(Libro libro) {
        libros.add(libro);

    }

    public List<Libro> buscarTodos(){

        return libros;
    }

    public Optional<Libro> buscarUno (String isbn){
        return libros.stream().filter((l) -> l.getIsbn().equals(isbn)).findFirst();
    }

    public List<Libro> buscarTodosOrdenados (String orden){

        List<Libro> listaOrdenada = new ArrayList<>();
        if (orden.equals("isbn")) {

            listaOrdenada = libros.stream().sorted(Comparator.comparing(Libro::getIsbn)).collect(Collectors.toList());

        } else if (orden.equals("titulo")) {

            listaOrdenada = libros.stream().sorted(Comparator.comparing(Libro::getTitulo)).collect(Collectors.toList());

        } else {
            listaOrdenada = libros.stream().sorted(Comparator.comparing(Libro::getAutor)).collect(Collectors.toList());
        }
        

        return listaOrdenada;





    }

}
