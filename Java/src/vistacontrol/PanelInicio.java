package vistacontrol;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PanelInicio extends JPanel {

    public PanelInicio(Container container) {

        super();

        Font font;
        JLabel labelIniciarSesion, labelNombre, labelPassword;
        JTextField textFieldNombre;
        JPasswordField passwordField;
        JButton buttonIngresar, buttonRegistrar;
        AccionIngresar accionIngresar;
        AccionRegistrar accionRegistrar;
        GridBagLayout gridBagLayout;
        GridBagConstraints gridBagConstraints;
        FlowLayout flowLayout;
        JPanel panelBotones;

        labelIniciarSesion = new JLabel("Iniciar Sesión");
        font = new Font("Times New Roman", Font.PLAIN, 25);
        labelIniciarSesion.setFont(font);

        labelNombre = new JLabel("Nombre");

        textFieldNombre = new JTextField(20);

        labelPassword = new JLabel("Contraseña");

        passwordField = new JPasswordField(20);

        buttonIngresar = new JButton("Ingresar");
        accionIngresar = new AccionIngresar(container, textFieldNombre, passwordField);
        buttonIngresar.addActionListener(accionIngresar);

        buttonRegistrar = new JButton("Registrar");
        accionRegistrar = new AccionRegistrar(textFieldNombre, passwordField);
        buttonRegistrar.addActionListener(accionRegistrar);

        gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.insets = new Insets(15, 0, 0, 15);
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        add(labelIniciarSesion, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.gridwidth = 1;
        add(labelNombre, gridBagConstraints);
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        add(textFieldNombre, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        add(labelPassword, gridBagConstraints);
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        add(passwordField, gridBagConstraints);

        panelBotones = new JPanel();
        flowLayout = new FlowLayout();
        panelBotones.setLayout(flowLayout);
        panelBotones.add(buttonIngresar);
        panelBotones.add(buttonRegistrar);
        add(panelBotones, gridBagConstraints);
    }
}
