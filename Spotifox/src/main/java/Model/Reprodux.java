package Model;

public class Reprodux {
    private final CancionesList listaReproduccion;  // Hacer el campo final
    private int indiceCancionActual = -1;  // Almacenar el índice de la canción actual

    public Reprodux() {
        this.listaReproduccion = new CancionesList();  // Inicializar la lista de canciones
    }

    public CancionesList getListaReproduccion() {
        return listaReproduccion;
    }

    // obtener la cancion actual
    public Sing getCancionActual() {
        if (indiceCancionActual >= 0 && indiceCancionActual < listaReproduccion.getCanciones().size()) {
            return listaReproduccion.getCanciones().get(indiceCancionActual);
        }
        return null;  // Si no hay ninguna canción en el momento
    }

    // Métodos de reproducción (simulados)
    public void reproducir() {
        if (indiceCancionActual == -1 && !listaReproduccion.getCanciones().isEmpty()) {
            indiceCancionActual = 0; // Empieza con la primera canción si no hay ninguna seleccionada
        }

        Sing singActual = getCancionActual();
        if (singActual != null) {
            System.out.println("Reproduciendo: " + singActual.obtenerInformacion());
        } else {
            System.out.println("No hay canciones para reproducir.");
        }
    }

    public void pausar() {
        // Lógica de pausa, si es necesario
    }



    public void siguienteCancion() {
        if (indiceCancionActual + 1 < listaReproduccion.getCanciones().size()) {
            indiceCancionActual++;
        } else {
            indiceCancionActual = 0; // Vuelve al inicio si llega al final
        }
        reproducir(); // Muestra la nueva canción
    }

    public void anteriorCancion() {
        if (indiceCancionActual - 1 >= 0) {
            indiceCancionActual--;
        } else {
            indiceCancionActual = listaReproduccion.getCanciones().size() - 1; // Va al final si está al principio
        }
        reproducir(); // Muestra la nueva canción
    }


    public String obtenerEstadoActual() {
        Sing singActual = getCancionActual();
        return (singActual != null) ? "Reproduciendo: " + singActual.obtenerInformacion() : "No hay canciones en reproducción.";
    }
}