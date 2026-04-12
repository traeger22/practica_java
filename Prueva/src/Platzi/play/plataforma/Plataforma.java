package Platzi.play.plataforma;
import Platzi.play.contenido.Genero;
import Platzi.play.contenido.Pelicula;
import Platzi.play.contenido.ResumenContenido;
import Platzi.play.excepcion.PeliculaExistenteException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido;

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
    }

    public void agregar(Pelicula elemento) {
        Pelicula contenido = this.buscarPorTitulo(elemento.getTitulo());

        if (contenido != null) {
            throw new PeliculaExistenteException(elemento.getTitulo());
        }

        this.contenido.add(elemento);
    }

    public void eliminar(Pelicula elemento){
        this.contenido.remove(elemento);
        System.out.println("se elimino exitosamente");
    }
    public Pelicula buscarPorTitulo(String titulo){
//        for (Pelicula pelicula : contenido) {
//            if (pelicula.getTitulo().equalsIgnoreCase(titulo)) {
//                return pelicula;
//            }
//        }
//        return null;

        return contenido.stream()
                .filter(contenido -> contenido.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }

    public List<Pelicula> buscarPorGenero(Genero genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .toList();
    }

    public  List<Pelicula> getPopulares(int cantidad){
        return contenido.stream()
                .sorted(Comparator.comparingDouble(Pelicula::getCalificacion).reversed())
                .limit(cantidad)
                .toList();
    }
    public List<ResumenContenido> getResumenes(){
        return contenido.stream()
                .map(conte -> new ResumenContenido(conte.getTitulo(), conte.getDuracion(), conte.getGenero()))
                .toList();

    }



    public int getDuracionTotal (){
        return contenido.stream()
                .mapToInt(contenido->contenido.getDuracion())
                .sum();
    }

    public List<String> mostrarTitulos (){
        // for(int i = 0; i < contenido.size(); i++) {
          //  System.out.println(contenido.get(i).getTitulo());
//        for (Pelicula pelicula : contenido) {
//            System.out.println(pelicula.getTitulo());
//        }
       // contenido.forEach(pelicula -> System.out.println(pelicula.getTitulo()));
        return contenido.stream()
            .map(Pelicula::getTitulo) // map tranformar=  .map(n-> n*2) y tranformacion a metodo de referencia
                .toList();

    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }
}
