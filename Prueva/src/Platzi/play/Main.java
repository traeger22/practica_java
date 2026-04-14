package Platzi.play;

import Platzi.play.contenido.Genero;
import Platzi.play.contenido.Pelicula;
import Platzi.play.contenido.ResumenContenido;
import Platzi.play.excepcion.PeliculaExistenteException;
import Platzi.play.plataforma.Plataforma;
import Platzi.play.plataforma.Usuario;
import Platzi.play.util.ScannerUtils;


import java.util.List;


public class Main {
    public static final String NOMBRE_PLATAFORMA = "PLATZI PLAY";
    public static final String VERSION = "1.0.1";
    public static final int SALIR = 8;
    public static final int AGREGAR = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULOS = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int VER_POPULARES = 6;
    public static final int ELIMINAR = 5;
    public static final int REPRODUCIR = 7;

    public static void main(String[] args) {

        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);

        System.out.println(NOMBRE_PLATAFORMA + "v" + VERSION);

        cargarPeliculas(plataforma);

        System.out.println("mas de " + plataforma.getDuracionTotal() + "  minutos de contenidos \n");

        //1. agregar contenido
        //2. mostrar_todo
        //3. buscar por titulos
        //4. eliminar
        //5. SALIR

        while (true) {
            int opcionElegida = ScannerUtils.capturarNumero("""
                    1. agregar contenido
                    2. mostrar todo
                    3. buscar por titulos
                    4. buscar por genero
                    5. eliminar
                    6. ver populares
                    7. Reproducir
                    8. salir  
                    """);

            switch (opcionElegida) {
                case AGREGAR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("Calificacion del contenido");

                    try{
                        plataforma.agregar( new Pelicula(nombre, duracion, genero, calificacion));
                    }catch (PeliculaExistenteException e){
                        System.out.println(e.getMessage());
                    }


                }
                case MOSTRAR_TODO -> {
//                    plataforma.mostrarTitulos();
//                    List<String> titulos = plataforma.mostrarTitulos();
//                    titulos.forEach(System.out::println);
                   List<ResumenContenido> contenidosResumidos = plataforma.getResumenes();
                   contenidosResumidos.forEach(resumen -> System.out.println(resumen.toString()));

                }
                case BUSCAR_POR_TITULOS -> {
                    String nombreBuscado = ScannerUtils.capturarTexto("buscar el nombre de la pelicula: ");
                    Pelicula pelicula = plataforma.buscarPorTitulo(nombreBuscado);
                    if (pelicula != null){
                        System.out.println(pelicula.obtenerFichaTecnica());
                    }else {
                        System.out.println("buscando nombre no exite dentro de: " + plataforma.getNombre());
                    }
                    //FALTA
                }
                case BUSCAR_POR_GENERO -> {
                    Genero generoBuscado = ScannerUtils.capturarGenero("Genero del contenido a buscar");

                    List<Pelicula> contenidoPorGenero = plataforma.buscarPorGenero(generoBuscado);
                    System.out.println(contenidoPorGenero.size() + " encontrados para el genero " + generoBuscado);
                    contenidoPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
                }
                case VER_POPULARES ->{
                    int cantidad = ScannerUtils.capturarNumero("CANTIDAD DE RESULTADOS A MOSTRAR");

                    List<Pelicula> contenidosPopulares = plataforma.getPopulares(cantidad);
                    contenidosPopulares.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));

                }
                case REPRODUCIR ->{
                    String nombre  = ScannerUtils.capturarTexto("nombre de la pelicula a reproducir: ");
                    Pelicula contenido = plataforma.buscarPorTitulo(nombre);

                    if (contenido != null){
                        plataforma.reproducir(contenido);
                    }else {
                        System.out.println(nombre + "no existe dentro de la plataforma");
                    }

                }
                case ELIMINAR -> {
                    //FALTA
                    String peliculaEliminar = ScannerUtils.capturarTexto("nombre de la pelicula a eliminar");
                    Pelicula obtenerPelicula = plataforma.buscarPorTitulo(peliculaEliminar);
                    if(obtenerPelicula != null){
                        System.out.println(obtenerPelicula.getTitulo() + " Eliminada con exito.");
                        plataforma.eliminar(obtenerPelicula);
                    }else {
                        System.out.println(peliculaEliminar + " no existe dentro de " + plataforma.getNombre());
                    }
                }


                case SALIR -> {
                    System.exit(0);
                }
            }




//            if(opcionElegida == AGREGAR){
//                String nombre = ScannerUtils.capturarTexto("nombre del contenido");
//                String genero = ScannerUtils.capturarTexto("genero del contenido");
//                int duracion = ScannerUtils.capturarNumero("duracion del contenido");
//                double calificacion = ScannerUtils.capturarDecimal("calificacion del contenido");
//                Pelicula pelicula = new Pelicula(nombre,duracion, genero, calificacion);
//                plataforma.agregar(pelicula);
//            }
//            else if(opcionElegida == MOSTRAR_TODO){
//                plataforma.mostrarTitulos();
//            }
//            else if (opcionElegida == BUSCAR_POR_TITULOS) {
//                //FALTA
//            }
//            else if(opcionElegida == ELIMINAR){
//                //FALTA
//
//            }
//            else if (opcionElegida == SALIR){
//                System.exit(0);
//            }
//
//        }


//       String nombre = ScannerUtils.capturarTexto("nombre del contenido");
//        String genero = ScannerUtils.capturarTexto("genero del contenido");
//        int duracion = ScannerUtils.capturarNumero("duracion del contenido");
//        double calificacion = ScannerUtils.capturarDecimal("calificacion del contenido");


//
//        Pelicula pelicula = new Pelicula(nombre,duracion, genero, calificacion);
//        Pelicula pelicula2 = new Pelicula("ted",120, "terror", 4.7);
            //pelicula.titulo = nombre;
            //pelicula.fechaEstreno = LocalDate.of(2018,11,25);
            //pelicula.genero = genero;
//        pelicula.calificar(calificacion);
            //pelicula.duracion = duracion;

//        plataforma.agregar(pelicula);
//        plataforma.agregar(pelicula2);
//        System.out.println("numeros de elementos en la plataforma " + plataforma.getContenido().size());
//        plataforma.eliminar(pelicula2);
//        plataforma.mostrarTitulos();

//        long duracionPelicula = pelicula.duracion;
//        int calificacion = (int) pelicula.calificacion;
//        long numeroDePremios = Long.parseLong("2500000000000");
//        System.out.println("numero de premios" + numeroDePremios);
//        System.out.println("la calificacion long " + calificacion );
//        System.out.println("la pelicula duracion int " + duracionPelicula );

//        Usuario usuario = new Usuario("JUAN", "juan@gmail.com");
            //usuario.nombre = "Juan";
//        usuario.ver(pelicula);


            //System.out.println(pelicula.obtenerFichaTecnica());

        }

    }
    private static void cargarPeliculas(Plataforma plataforma) {
        plataforma.agregar(new Pelicula("Shrek", 90, Genero.ANIMADA));
        plataforma.agregar(new Pelicula("Inception", 148, Genero.CIENCIA_FICCION));
        plataforma.agregar(new Pelicula("Titanic", 195, Genero.DRAMA, 4.6));
        plataforma.agregar(new Pelicula("John Wick", 101, Genero.ACCION));
        plataforma.agregar(new Pelicula("El Conjuro", 112, Genero.TERROR, 3.0));
        plataforma.agregar(new Pelicula("Coco", 105, Genero.ANIMADA, 4.7));
        plataforma.agregar(new Pelicula("Interstellar", 169, Genero.CIENCIA_FICCION, 5));
        plataforma.agregar(new Pelicula("Joker", 122, Genero.DRAMA));
        plataforma.agregar(new Pelicula("Toy Story", 81, Genero.ANIMADA, 4.5));
        plataforma.agregar(new Pelicula("Avengers: Endgame", 181, Genero.ACCION, 3.9));

    }
}
