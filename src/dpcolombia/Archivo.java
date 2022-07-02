package dpcolombia;

import java.io.*;
import javax.swing.*;

public class Archivo {

    public static BufferedReader abrirArchivo(String nombreArchivo) {
        //instancia de objeto archivo
        File f = new File(nombreArchivo);
        //existe el archivo
        if (f.exists()) {
            try {
                //instancia del objerto lector archivo
                FileReader fr = new FileReader(f);
                //abrir el archivo y retornar su contenido
                return new BufferedReader(fr);

            } catch (Exception ex) {

            }

        }
        return null;
    }

    public static void cargarImagen(JLabel lbl, String nombreArchivo) {
        //instancia de objeto archivo
        File f = new File(nombreArchivo);
        //existe el archivo
        if (f.exists()) {
            ImageIcon img = new ImageIcon(nombreArchivo);
            lbl.setIcon(img);
        } else {
            lbl.setIcon(null);
        }
    }

}
