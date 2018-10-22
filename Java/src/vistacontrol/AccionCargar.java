package vistacontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import persistencia.Archivo;
import modelo.Estado;
import modelo.Tipo;
import persistencia.Persistencia;

public class AccionCargar implements ActionListener {

    private final JLabel labelNombre;
    private final JComboBox comboBox;
    private final ButtonGroup buttonGroup;

    public AccionCargar(JLabel labelNombre, JComboBox comboBox, ButtonGroup buttonGroup) {

        this.labelNombre = labelNombre;
        this.comboBox = comboBox;
        this.buttonGroup = buttonGroup;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        JFileChooser fileChooser;
        File file;
        FileInputStream fileInputStream;
        int seleccion;
        Persistencia persistencia;
        Archivo archivo;

        fileChooser = new JFileChooser();
        seleccion = fileChooser.showOpenDialog(labelNombre);

        if (seleccion == JFileChooser.APPROVE_OPTION) {

            file = fileChooser.getSelectedFile();
            
            try {
                
                fileInputStream = new FileInputStream(file);
                
                archivo = new Archivo(file.getName(), fileInputStream);
                archivo.setEstado(Estado.valueOf(buttonGroup.getSelection().getActionCommand().toUpperCase()));
                archivo.setTipo(Tipo.valueOf(comboBox.getSelectedItem().toString().toUpperCase()));
                
                persistencia = new Persistencia();
                persistencia.insertarArchivo(labelNombre.getText(), archivo);
                
                fileInputStream.close();
                
            } catch (ClassNotFoundException | SQLException | IOException ex) {
                
                ex.printStackTrace();
            }
        }
    }
}
