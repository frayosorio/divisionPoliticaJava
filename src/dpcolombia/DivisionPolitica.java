package dpcolombia;

import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class DivisionPolitica {

    public static void llenarArbol(JTree arbol) {
        //definir el modelo de datos para el arbol

        DefaultMutableTreeNode nRaiz = new DefaultMutableTreeNode("Colombia");
        DefaultTreeModel dtm = new DefaultTreeModel(nRaiz);
        arbol.setModel(dtm);

        //definir el nombre del archivo con la información de la division politica de Colombia
        String ruta = System.getProperty("user.dir");
        String nombreArchivo = ruta + "/src/datos/Colombia.txt";

        //abrir el archivo
        BufferedReader br = Archivo.abrirArchivo(nombreArchivo);
        if (br != null) {
            try {
                //recorrer el archivo
                String linea = br.readLine();
                String anteriorDepto = "";
                DefaultMutableTreeNode nDepto = null;
                while (linea != null) {
                    String[] textos = linea.split(",");
                    //Crear y agregar nodo de depto cada vez que sea necesario
                    if (!anteriorDepto.equals(textos[0])) {
                        nDepto = new DefaultMutableTreeNode(textos[0].trim());
                        nRaiz.add(nDepto);
                        anteriorDepto = textos[0];
                    }
                    //crear y agregar nodo de mpio al actual depto
                    DefaultMutableTreeNode nMpio = new DefaultMutableTreeNode(textos[1].trim());
                    nDepto.add(nMpio);
                    linea = br.readLine();
                }

            } catch (Exception ex) {

            }
        }
    }

    public static void mostrarMapa(JLabel lbl, JTree arbol) {
        //obtener nodo seleccionado
        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) arbol.getLastSelectedPathComponent();
        if (nodo != null) {

            if (nodo.getLevel() <= 1) {
                String nombreDepto = nodo.toString();

                //abrir el artchivo con los nombres de los mapas
                String ruta = System.getProperty("user.dir");
                String nombreArchivo = ruta + "/src/datos/Mapas.txt";

                BufferedReader br = Archivo.abrirArchivo(nombreArchivo);

                if (br != null) {
                    try {
                        String linea = br.readLine();
                        while (linea != null) {
                            String[] textos = linea.split(",");
                            if (textos.length >= 2) {
                                if (textos[0].equalsIgnoreCase(nombreDepto)) {
                                    String nombreArchivoMapa = ruta + "/src/mapas/" + textos[1];
                                    Archivo.cargarImagen(lbl, nombreArchivoMapa);
                                }
                            }
                            linea = br.readLine();
                        }

                    } catch (Exception ex) {

                    }
                }
            }
        }

    }

    public static void mostrarFoto(JLabel lbl, JTree arbol) {
        //obtener nodo seleccionado
        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) arbol.getLastSelectedPathComponent();
        if (nodo != null) {

            if (nodo.getLevel() == 2) {
                String nombreMpio = nodo.toString();

                //definir la ruta de la foto
                String ruta = System.getProperty("user.dir");
                String nombreArchivoFoto = ruta + "/src/fotos/" + quitarTildes(nombreMpio) + ".jpg";
                Archivo.cargarImagen(lbl, nombreArchivoFoto);
            }
        }
    }

    public static void mostrarDatos(JTextArea txt, JTree arbol) {
        String contenido = "";
        //obtener nodo seleccionado
        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) arbol.getLastSelectedPathComponent();
        if (nodo != null) {
            if (nodo.getLevel() == 2) {
                String nombreMpio = nodo.toString();
                String nombreDepto = nodo.getParent().toString();

                String ruta = System.getProperty("user.dir");
                String nombreArchivoDatos = ruta + "/src/datos/" + nombreDepto + ".txt";

                BufferedReader br = Archivo.abrirArchivo(nombreArchivoDatos);

                if (br != null) {
                    try {
                        String linea = br.readLine();
                        String[] encabezados = linea.split(";");
                        linea = br.readLine();
                        while (linea != null) {
                            String[] textos = linea.split(";");
                            if (textos.length >= encabezados.length) {
                                if (quitarTildes(textos[0]).contains(quitarTildes(nombreMpio))) {
                                    for (int i = 0; i < encabezados.length; i++) {
                                        contenido += encabezados[i] + ": " + textos[i] + "\n";
                                    }
                                }
                            }
                            linea = br.readLine();
                        }
                    } catch (Exception ex) {

                    }
                }

            }
        }
        txt.setText(contenido);
    }

    public static String quitarTildes(String texto) {
        return texto.toLowerCase().replace('á', 'a').
                replace('é', 'e').
                replace('í', 'i').
                replace('ó', 'o').
                replace('ú', 'u').
                replace('ü', 'u').
                replace('ñ', 'n');
    }

}
