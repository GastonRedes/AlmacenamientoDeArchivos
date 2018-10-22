package vistacontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import modelo.NombreInvalidoException;
import modelo.PasswordInvalidoException;
import modelo.Usuario;
import persistencia.Persistencia;

public class AccionRegistrar implements ActionListener {

    private final JTextField textFieldNombre;
    private final JPasswordField passwordField;

    public AccionRegistrar(JTextField textFieldNombre, JPasswordField passwordField) {

        this.textFieldNombre = textFieldNombre;
        this.passwordField = passwordField;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        Persistencia persistencia;
        Usuario usuario;

        try {

            persistencia = new Persistencia();
            usuario = persistencia.seleccionarUsuario(textFieldNombre.getText());

            if (usuario == null) {
                
                usuario = new Usuario(textFieldNombre.getText(), new String(passwordField.getPassword()));
                persistencia.insertarUsuario(usuario);
                
            } else {
                throw new NombreInvalidoException();
            }

        } catch (ClassNotFoundException | SQLException ex) {
            
            ex.printStackTrace();
            
        } catch (NombreInvalidoException | PasswordInvalidoException ex) {
            
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
