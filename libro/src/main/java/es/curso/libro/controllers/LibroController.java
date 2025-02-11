package es.curso.libro.controllers;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.curso.libro.negocio.Libro;
import es.curso.libro.repositories.LibroRepositoryMemoria;

@Controller
public class LibroController {

    @Autowired
    private LibroRepositoryMemoria libroRepository;

    public LibroController() {

       
    }

    @PostMapping("/insertarlibro")
    public String insertarLibro(@ModelAttribute Libro libro) {
        libroRepository.insertarLibro(libro);

        return "redirect:listalibros";

    }
    @PostMapping("/salvarlibro")
    public String salvarLibro(@ModelAttribute Libro libro,@RequestParam String nombreAntiguo) {
        Optional<Libro> oLibro=libroRepository.buscarUno(nombreAntiguo);

        if (oLibro.isPresent()){
            Libro libroActual=oLibro.get();
            libroActual.setIsbn(libro.getIsbn());
            libroActual.setTitulo(libro.getTitulo());
            libroActual.setAutor(libro.getAutor());
        }

        return "redirect:listalibros";

    }

    @GetMapping("/borrar")
    public String borrarLibro(@RequestParam("isbn") String isbn) {
        libroRepository.borrarLibro(isbn);
        return "redirect:listalibros";
    }

    @GetMapping("/detalle")
    public String detalleLibro(@RequestParam("isbn") String isbn, Model modelo) {
        Optional<Libro> oLibro= libroRepository.buscarUno(isbn);
        if (oLibro.isPresent()) {
            modelo.addAttribute("libro", oLibro.get());
        }

        return "detallelibro";
    }
    @GetMapping("/editar")
    public String editarLibro(@RequestParam("isbn") String isbn, Model modelo) {
        Optional<Libro> oLibro= libroRepository.buscarUno(isbn);
        if (oLibro.isPresent()) {
            modelo.addAttribute("libro", oLibro.get());
        }

        return "formularioeditarlibro";
    }

    @GetMapping("/formulariolibro")
    public String formulariolibro() {

        return "formulariolibro";
    }

    @GetMapping("/listalibros")
    public String listalibros(Model modelo) {
        modelo.addAttribute("listalibros", libroRepository.buscarTodos());
        return "listaslibros";
    }

    @GetMapping(value = "/listalibros", params = "orden")
    public String listalibros(Model modelo, @RequestParam String orden) {
        
        List<Libro> listaOrdenada= libroRepository.buscarTodosOrdenados(orden);
        modelo.addAttribute("listalibros", listaOrdenada);
        return "listalibros";
    }

   
}
