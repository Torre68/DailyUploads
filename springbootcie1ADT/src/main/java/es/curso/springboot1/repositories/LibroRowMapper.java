package es.curso.springboot1.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import es.curso.springboot1.negocio.Libro;

public class LibroRowMapper implements RowMapper<Libro> {

    @Override
    public Libro mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Libro (rs.getString("isbn"), rs.getString("titulo"), 
       rs.getString("autor"), rs.getInt("paginas"));
       
    }

}
