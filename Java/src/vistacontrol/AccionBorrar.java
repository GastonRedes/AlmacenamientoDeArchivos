package vistacontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JLabel;
import persistencia.Persistencia;

public class AccionBorrar implements ActionListener {

    private final JLabel labelNombre;

    public AccionBorrar(JLabel labelNombre) {

        this.labelNombre = labelNombre;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        Persistencia persistencia;
        
        try {
            
            persistencia = new Persistencia();
            persistencia.borrarArchivos(labelNombre.getText());
            
        } catch (ClassNotFoundException | SQLException ex) {
            
            ex.printStackTrace();
        }
    }
}
