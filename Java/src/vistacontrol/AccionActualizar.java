package vistacontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import persistencia.Persistencia;

public class AccionActualizar implements ActionListener {

    private final JLabel labelNombre;
    private final ButtonGroup buttonGroup;

    public AccionActualizar(JLabel labelNombre, ButtonGroup buttonGroup) {

        this.labelNombre = labelNombre;
        this.buttonGroup = buttonGroup;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        Persistencia persistencia;
        String nombre;
        String estado;
        
        try {
            
            nombre = labelNombre.getText();
            estado = buttonGroup.getSelection().getActionCommand().toUpperCase();
            
            persistencia = new Persistencia();
            persistencia.actualizarArchivos(nombre, estado);
            
        } catch (ClassNotFoundException | SQLException ex) {
            
            ex.printStackTrace();
        }
    }
}
