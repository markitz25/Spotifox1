package UI;

import Model.Reprodux;
import Model.CancionesList;
import Model.Sing;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Informacion extends JPanel {
    private final Reprodux reproductor;
    private final CancionesList cancionesList;
    private final JButton btnReproducir;
    private final JButton btnPausar;
    private final JButton btnSiguiente;
    private final JButton btnAnterior;
    private final JLabel lblEstado;
    private final JTextArea txtAreaCanciones;

    public Informacion(Reprodux reproductor, CancionesList cancionesList) {
        this.cancionesList = cancionesList;
        this.reproductor = reproductor;


        this.setLayout(new BorderLayout());

        // Controles de la interfaz
        JPanel panelControles = new JPanel(new GridLayout(1, 4)); // Cambiado a 4 columnas
        btnReproducir = new JButton("Reproducir");
        btnPausar = new JButton("Pausar");
        btnSiguiente = new JButton("Siguiente");
        btnAnterior = new JButton("Anterior");

        btnReproducir.addActionListener(e -> reproducir());
        btnPausar.addActionListener(e -> pausar());
        btnSiguiente.addActionListener(e -> siguienteCancion());
        btnAnterior.addActionListener(e -> anteriorCancion());

        panelControles.add(btnSiguiente);
        panelControles.add(btnAnterior);
        panelControles.add(btnReproducir);
        panelControles.add(btnPausar);


        lblEstado = new JLabel("Estado: ¿En que te puedo servir hoy?", SwingConstants.CENTER);

        txtAreaCanciones = new JTextArea(30, 30);
        txtAreaCanciones.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaCanciones);

        this.add(panelControles, BorderLayout.NORTH);
        this.add(lblEstado, BorderLayout.CENTER);
        this.add(scrollPane, BorderLayout.EAST);
    }

    private void reproducir() {
        reproductor.reproducir();
        lblEstado.setText(reproductor.obtenerEstadoActual());
    }

    private void pausar() {
        reproductor.pausar();
        lblEstado.setText("Estado: Pausado");
    }

    private void siguienteCancion() {
        reproductor.siguienteCancion();
        lblEstado.setText(reproductor.obtenerEstadoActual());
    }

    private void anteriorCancion() {
        reproductor.anteriorCancion();
        lblEstado.setText(reproductor.obtenerEstadoActual());
    }

    public void cargarCancionesDesdeCSV(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            cancionesList.limpiarLista();
            txtAreaCanciones.setText("");

            String linea;
            br.readLine(); // Saltar la cabecera
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    String titulo = datos[0].replace("\"", "").trim();
                    String artista = datos[1].replace("\"", "").trim();
                    int duracion = Integer.parseInt(datos[2].trim());
                    String album = datos[3].replace("\"", "").trim();
                    String genero = datos[4].replace("\"", "").trim();

                    Sing sing = new Sing(titulo, artista, duracion, album, genero);
                    cancionesList.agregarCancion(sing);

                    txtAreaCanciones.append(sing.obtenerInformacion() + "\n");
                }
            }

            JOptionPane.showMessageDialog(this, "Canciones cargadas correctamente desde: " + rutaArchivo,
                    "Carga Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el archivo CSV.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}