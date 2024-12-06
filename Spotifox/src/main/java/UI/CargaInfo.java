package UI;

import Model.Sing;
import Model.Reprodux;
import Model.CancionesList;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class CargaInfo extends JPanel {
    private Reprodux reproductor;
    private CancionesList ListaCanci;
    private JButton BtnSubirArchi, BtnCreateList, BTNcrearrMusic, BtnparaArtis, btnAgregarCancionALista;
    private JTextArea TXTcanciones;

    public CargaInfo(Reprodux reproductor, CancionesList ListaCanci) {
        this.reproductor = reproductor;
        this.ListaCanci = ListaCanci;

        this.setLayout(new GridLayout(6, 4));

        // Inicializar los componentes de la interfaz
        BtnSubirArchi = new JButton("Cargar Archivo");
        BtnCreateList = new JButton("Crear Lista");
        BTNcrearrMusic = new JButton("Crear Canción");
        BtnparaArtis = new JButton("Crear Artista");
        btnAgregarCancionALista = new JButton("Agregar a Lista");

        TXTcanciones = new JTextArea();
        TXTcanciones.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(TXTcanciones);

        // Agregar los botones y el área de texto al panel

        this.add(scrollPane);//barra inferior
        this.add(BtnparaArtis);
        this.add(BtnCreateList);
        this.add(BTNcrearrMusic);
        this.add(BtnSubirArchi);
        this.add(BtnCreateList);
        this.add(BTNcrearrMusic);
        this.add(btnAgregarCancionALista);


        // Asignar los event listeners a los botones
        BtnSubirArchi.addActionListener(e -> cargarArchivoCSV());
        BtnCreateList.addActionListener(e -> mostrarDialogoLista());
        BTNcrearrMusic.addActionListener(e -> mostrarDialogoCancion());
        BtnparaArtis.addActionListener(e -> mostrarDialogoArtista());
        btnAgregarCancionALista.addActionListener(e -> mostrarDialogoSeleccionarLista());
    }

    private void cargarArchivoCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos CSV", "csv"));
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            MainWindow.getInstance().cargarCancionesDesdeCSV(archivoSeleccionado.getAbsolutePath());
        }
    }

    private void mostrarDialogoLista() {
        JTextField nombreLista = new JTextField();
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JLabel("Nombre de la lista:"));
        panel.add(nombreLista);

        int result = JOptionPane.showConfirmDialog(this, panel, "Crear Lista", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String nombreListaTexto = nombreLista.getText().trim();
            if (!nombreListaTexto.isEmpty()) {
                String rutaArchivoListas = "src/main/resources/data/" + nombreListaTexto + ".csv";
                File archivo = new File(rutaArchivoListas);
                if (archivo.exists()) {
                    JOptionPane.showMessageDialog(this, "Ya existe una lista con ese nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    guardarEnCSV(rutaArchivoListas, nombreListaTexto);
                    TXTcanciones.append("Lista creada: " + nombreListaTexto + "\n");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre válido para la lista.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void guardarEnCSV(String rutaArchivo, String contenido) {
        try (FileWriter writer = new FileWriter(rutaArchivo, true)) {
            writer.write(contenido + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void mostrarDialogoCancion() {
        JTextField tituloCancion = new JTextField();
        JTextField artistaCancion = new JTextField();
        JTextField duracionCancion = new JTextField();
        JTextField albumCancion = new JTextField();
        JTextField generoCancion = new JTextField();

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Título:"));
        panel.add(tituloCancion);
        panel.add(new JLabel("Artista:"));
        panel.add(artistaCancion);
        panel.add(new JLabel("Duración (segundos):"));
        panel.add(duracionCancion);
        panel.add(new JLabel("Álbum:"));
        panel.add(albumCancion);
        panel.add(new JLabel("Género:"));
        panel.add(generoCancion);

        int result = JOptionPane.showConfirmDialog(this, panel, "Crear Canción", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String titulo = tituloCancion.getText().trim();
            String artista = artistaCancion.getText().trim();
            String duracionStr = duracionCancion.getText().trim();
            String album = albumCancion.getText().trim();
            String genero = generoCancion.getText().trim();

            if (!titulo.isEmpty() && !artista.isEmpty() && !duracionStr.isEmpty() && !album.isEmpty() && !genero.isEmpty()) {
                try {
                    int duracion = Integer.parseInt(duracionStr);
                    Sing sing = new Sing(titulo, artista, duracion, album, genero);

                    String rutaArchivoCanciones = "src/main/resources/data/canciones.csv";
                    String contenidoCancion = titulo + "," + artista + "," + duracion + "," + album + "," + genero;
                    guardarEnCSV(rutaArchivoCanciones, contenidoCancion);

                    ListaCanci.agregarCancion(sing);
                    TXTcanciones.append("Canción Añadida: " + sing.obtenerInformacion() + "\n");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "La duración debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarDialogoArtista() {
        JTextField nombreArtista = new JTextField();
        JTextField generoArtista = new JTextField();
        JTextField paisArtista = new JTextField();
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreArtista);
        panel.add(new JLabel("Género:"));
        panel.add(generoArtista);
        panel.add(new JLabel("País:"));
        panel.add(paisArtista);

        int result = JOptionPane.showConfirmDialog(this, panel, "Crear Artista", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            guardarEnCSV("src/main/resources/Artistas.csv", nombreArtista.getText() + "," + generoArtista.getText() + "," + paisArtista.getText());
        }
    }

    private void mostrarDialogoSeleccionarLista() {
        JTextField nombreLista = new JTextField();
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Nombre de la lista:"));
        panel.add(nombreLista);

        int result = JOptionPane.showConfirmDialog(this, panel, "Seleccionar Lista", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String nombreListaTexto = nombreLista.getText().trim();
            if (!nombreListaTexto.isEmpty()) {
                agregarCancionALista(nombreListaTexto);
            } else {
                JOptionPane.showMessageDialog(this, "Debe ingresar un nombre válido para la lista.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void agregarCancionALista(String nombreLista) {
        String rutaArchivoCanciones = "src/main/resources/data/canciones.csv";
        String rutaArchivoLista = "src/main/resources/data/" + nombreLista + ".csv";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivoCanciones))) {
            String linea;
            StringBuilder cancionesDisponibles = new StringBuilder();
            while ((linea = br.readLine()) != null) {
                cancionesDisponibles.append(linea).append("\n");
            }

            JTextArea textAreaCanciones = new JTextArea(cancionesDisponibles.toString());
            JScrollPane scrollPane = new JScrollPane(textAreaCanciones);
            textAreaCanciones.setEditable(false);

            String cancionSeleccionada = JOptionPane.showInputDialog(this, scrollPane,
                    "Seleccione una canción escribiendo el título exacto", JOptionPane.PLAIN_MESSAGE);

            if (cancionSeleccionada != null && !cancionSeleccionada.trim().isEmpty()) {
                try (BufferedReader brBusqueda = new BufferedReader(new FileReader(rutaArchivoCanciones))) {
                    String busqueda;
                    boolean encontrada = false;
                    while ((busqueda = brBusqueda.readLine()) != null) {
                        if (busqueda.contains(cancionSeleccionada.trim())) {
                            try (FileWriter writer = new FileWriter(rutaArchivoLista, true)) {
                                writer.write(busqueda + "\n");
                                TXTcanciones.append("Canción añadida a la lista: " + cancionSeleccionada + "\n");
                                encontrada = true;
                                break;
                            }
                        }
                    }
                    if (!encontrada) {
                        JOptionPane.showMessageDialog(this, "Canción no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se seleccionó una canción válida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
