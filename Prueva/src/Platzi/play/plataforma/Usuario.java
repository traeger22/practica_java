package Platzi.play.plataforma;

import Platzi.play.contenido.Pelicula;

import java.time.LocalDate;

public class Usuario{
    private String nombre;
    private String email;
    public LocalDate fechaRegistro;

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        fechaRegistro = LocalDate.now();
    }

    public void ver(Pelicula pelicula) {
        System.out.println(nombre + " está viendo " );
        pelicula.reproducir();
    }

    public String getNombre() {
        return nombre;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
