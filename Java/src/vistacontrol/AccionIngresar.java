package vistacontrol;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import modelo.NombreInvalidoException;
import modelo.PasswordInvalidoException;
import modelo.Usuario;
import modelo.Encriptacion;
import persistencia.Persistencia;

public class AccionIngresar implements ActionListener {

    private final Container container;
    private final JTextField textFieldNombre;
    private final JPasswordField passwordField;

    public AccionIngresar(Container container, JTextField textFieldNombre, JPasswordField passwordField) {

        this.container = container;
        this.textFieldNombre = textFieldNombre;
        this.passwordField = passwordField;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        Persistencia persistencia;
        Usuario usuario;
        Encriptacion encriptacion;
        String nombre, password, hash;
        PanelArchivos panelArchivos;

        try {

            persistencia = new Persistencia();
            nombre = textFieldNombre.getText();
            usuario = persistencia.seleccionarUsuario(nombre);

            if (usuario == null) {
                throw new NombreInvalidoException();
            }

            encriptacion = new Encriptacion();
            password = new String(passwordField.getPassword());
            hash = encriptacion.getHash(password);

            if (usuario.getPassword().equals(hash)) {
                
                panelArchivos = new PanelArchivos(container, nombre);      
                container.removeAll();
                container.add(panelArchivos);
                container.validate();
                container.repaint();
                
            } else {
                throw new PasswordInvalidoException();
            }

        } catch (ClassNotFoundException | SQLException ex) {
            
            ex.printStackTrace();
            
        } catch (NombreInvalidoException | PasswordInvalidoException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
