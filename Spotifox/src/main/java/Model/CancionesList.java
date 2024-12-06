package Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CancionesList {
    private final List<Sing> canciones; // Campo 'canciones' como 'final'
    private ModoReproduccion modoReproduccion;

    public CancionesList() {
        this.canciones = new ArrayList<>();
        this.modoReproduccion = ModoReproduccion.NORMAL;  // Usar el enum en lugar de un String
    }

    public void agregarCancion(Sing c) {
        canciones.add(c);
    }

    public void eliminarCancion(Sing c) {
        canciones.remove(c);
    }

    public void ordenarPorArtista() {
        canciones.sort(Comparator.comparing(Sing::getArtista)); // Usar Comparator.comparing
    }

    public Sing buscarPorTitulo(String titulo) {
        for (Sing c : canciones) {
            if (c.getTitulo().equalsIgnoreCase(titulo)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Canción no encontrada: " + titulo); // Lanzar una excepción si no se encuentra
    }

    public List<Sing> getCanciones() {
        return canciones;
    }

    // Método adicional para obtener la información de la lista
    public String obtenerInformacionLista() {
        StringBuilder info = new StringBuilder();
        for (Sing c : canciones) {
            info.append(c.obtenerInformacion()).append("\n");
        }
        return info.toString();
    }

    // Enum para los modos de reproducción
    public enum ModoReproduccion {
        NORMAL,
        ALEATORIO,
        REPETIR
    }

    // Métodos para cambiar el modo de reproducción
    public ModoReproduccion getModoReproduccion() {
        return modoReproduccion;
    }

    public void setModoReproduccion(ModoReproduccion modoReproduccion) {
        this.modoReproduccion = modoReproduccion;
    }

    public void limpiarLista() {
        canciones.clear();
    }
}
