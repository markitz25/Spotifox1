package Model;

public class Sing {


    private final int duracion; // segundos sin comas ni nada
    private final String artista;
    private final String genero;
    private final String album;
    private final String titulo;

    public Sing(String titulo, String artista, int duracion, String album, String genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
        this.album = album;
        this.genero = genero;
    }

    public int obtenerDuracion() {
        return duracion;
    }//se simula pero no sirve de por si ya que no tenemos tal cual archivos propios de musica

    public String obtenerInformacion() {
        return "Título: " + titulo + ", Artista: " + artista + ", Duración: " + duracion + "s, Álbum: " + album + ", Género: " + genero;
    }//en ese mismo orden se deben de colocar los nombres en el archivo .csv

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public String getAlbum() {
        return album;
    }
}