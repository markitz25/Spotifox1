package UI;

import Model.Reprodux;
import Model.CancionesList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainWindow extends JFrame implements ActionListener {
    private static MainWindow instance; // Singleton instance
    private final Reprodux reproductor; // Hacerlo final
    private final CancionesList cancionesList; // Hacerlo final
    private final Informacion informacion; // Hacerlo final
    private final CargaInfo cargaInfo; // Hacerlo final

    // Constructor privado para restringir la creación de instancias
    private MainWindow() {
        setSize(1280, 720); // tamaño de la pantalla
        this.setTitle("Spotifox"); // título de la aplicación
        this.setLayout(new BorderLayout()); // administra el diseño

        reproductor = new Reprodux();
        cancionesList = reproductor.getListaReproduccion();
        informacion = new Informacion(reproductor, cancionesList);
        cargaInfo = new CargaInfo(reproductor, cancionesList);

        this.add(informacion, BorderLayout.CENTER);
        this.add(cargaInfo, BorderLayout.WEST);

        // Cargar canciones por defecto
        cargarCancionesDesdeCSV("src/main/resources/canciones.csv"); // carga las canciones del archivo de música .csv

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // cierra la aplicación cuando se le da X
    }

    // método para las instancias
    public static MainWindow getInstance() {
        if (instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }

    @Override // sobrescribe un método
    public void actionPerformed(ActionEvent e) { // maneja los clicks
        String command = e.getActionCommand(); // almacena el comando
        if ("Cargar Archivo".equals(command)) { // usa if en lugar de switch
            cargarArchivoCSV();
        }
    }

    public void cargarArchivoCSV() { // permite a los usuarios navegar por el sistema de archivos y seleccionar archivos.
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos CSV", "csv"));
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            cargarCancionesDesdeCSV(archivoSeleccionado.getAbsolutePath());
        }
    }

    public void cargarCancionesDesdeCSV(String rutaArchivo) {
        informacion.cargarCancionesDesdeCSV(rutaArchivo);
    }

    public static void main(String[] args) {
        // Llama al Singleton para mostrar la ventana principal
        MainWindow ventana = MainWindow.getInstance();
        ventana.setVisible(true);
    }
}