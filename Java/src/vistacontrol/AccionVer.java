package vistacontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import persistencia.Archivo;
import persistencia.Persistencia;

public class AccionVer implements ActionListener {

    private final DefaultTableModel defaultTableModel;
    private final JLabel labelNombre;

    public AccionVer(DefaultTableModel defaultTableModel, JLabel labelNombre) {

        this.defaultTableModel = defaultTableModel;
        this.labelNombre = labelNombre;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        Persistencia persistencia;
        ArrayList<Archivo> archivos;
        String[] fila;

        try {
            
            persistencia = new Persistencia();
            archivos = persistencia.seleccionarArchivos(labelNombre.getText());

            fila = new String[3];

            defaultTableModel.setRowCount(0);
            
            for (Archivo archivo : archivos) {
                
                fila[0] = archivo.getEstado().toString();
                fila[1] = archivo.getTipo().toString();
                fila[2] = archivo.getNombre();
                
                archivo.getInputStream().close();
                
                defaultTableModel.addRow(fila);
            }
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            
            ex.printStackTrace();
        }
    }
}
