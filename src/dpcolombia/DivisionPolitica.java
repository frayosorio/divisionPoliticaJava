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
        //abrir el artchivo con los nombres de los mapas
        String ruta = System.getProperty("user.dir");
        String nombreArchivo = ruta + "/src/datos/Mapas.txt";
        
        
    }

}
