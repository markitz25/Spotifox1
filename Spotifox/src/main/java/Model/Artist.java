package Model;

public class Artist {
    // Atributos

    private String genero;
    private String sexo;
    private String nombre;
    private String pais;

    // Constructor
    public Artist(String nombre, String genero, String pais) {
        this.nombre = nombre;
        this.genero = genero;
        this.pais = pais;
        this.sexo = sexo;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public String getPais() {
        return pais;
    }

    public String getSexo() {
        return sexo;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    // sirve para probar si sirve
    public static void main(String[] args) {
        // Crear un objeto de la clase Artista
        Artist artist = new Artist("Valles t", "Rap", "Colombia");

        // Usar los métodos getters
        System.out.println("Nombre: " + artist.getNombre());
        System.out.println("Género: " + artist.getGenero());
        System.out.println("País: " + artist.getPais());
        System.out.println("Sexo: " + artist.getSexo());

        // Usar los métodos setters
        artist.setNombre("Valentin");
        artist.setGenero("Rock, pop, electronica, romantica, etc");
        artist.setPais("Argentina wacho");
        artist.setSexo("M");

        // Mostrar los nuevos valores
        System.out.println("Nuevo Nombre: " + artist.getNombre());
        System.out.println("Nuevo Género: " + artist.getGenero());
        System.out.println("Nuevo País: " + artist.getPais());
        System.out.println("Nuevo Sexo: " + artist.getSexo());
    }

}