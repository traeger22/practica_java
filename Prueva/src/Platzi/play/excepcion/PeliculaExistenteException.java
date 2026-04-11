package Platzi.play.excepcion;

public class PeliculaExistenteException extends RuntimeException {
    public PeliculaExistenteException(String titulo) {

        super("la pelicula " + titulo + "ya existe");
    }
}
