package vistacontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import persistencia.Archivo;
import persistencia.Persistencia;

public class AccionDescargar implements ActionListener {

    private final JLabel labelNombre;

    public AccionDescargar(JLabel labelNombre) {

        this.labelNombre = labelNombre;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        JFileChooser fileChooser;
        int seleccion;
        File file;
        FileOutputStream fileOutputStream;
        Persistencia persistencia;
        ArrayList<Archivo> archivos;
        byte[] buffer;

        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        seleccion = fileChooser.showSaveDialog(labelNombre);

        if (seleccion == JFileChooser.APPROVE_OPTION) {

            file = fileChooser.getSelectedFile();

            try {

                persistencia = new Persistencia();
                archivos = persistencia.seleccionarArchivos(labelNombre.getText());
                
                for (Archivo archivo : archivos) {
                    
                    fileOutputStream = new FileOutputStream(file + "/" + archivo.getNombre());
                    
                    buffer = new byte[1024];

                    while (archivo.getInputStream().read(buffer) > 0) {
                        fileOutputStream.write(buffer);
                    }
                    
                    archivo.getInputStream().close();
                    fileOutputStream.close();
                }

            } catch (ClassNotFoundException | SQLException | IOException ex) {
                
                ex.printStackTrace();
            }  
        }
    }
}
