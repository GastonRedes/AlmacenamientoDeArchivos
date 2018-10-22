package vistacontrol;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelArchivos extends JPanel {

    @SuppressWarnings("unchecked")
    public PanelArchivos(Container container, String nombre) {

        super();

        Font font;
        JLabel labelNombre, labelEstado, labelTipo, labelMaximo;
        JSeparator separator;
        ButtonGroup buttonGroup;
        JRadioButton radioButtonPrivado, radioButtonPublico;
        JComboBox comboBox;
        String[][] datos;
        String[] columnas;
        DefaultTableModel defaultTableModel;
        JTable table;
        JScrollPane scrollPane;
        JPanel panelBotones;
        JButton buttonVer, buttonActualizar, buttonCargar, buttonDescargar, buttonBorrar, buttonCerrar;
        AccionVer accionVer;
        AccionActualizar accionActualizar;
        AccionCargar accionCargar;
        AccionDescargar accionDescargar;
        AccionBorrar accionBorrar;
        AccionCerrar accionCerrar;
        GridBagLayout gridBagLayout;
        GridBagConstraints gridBagConstraints;
        FlowLayout flowLayout;

        labelNombre = new JLabel(nombre);
        font = new Font("Times New Roman", Font.PLAIN, 25);
        labelNombre.setFont(font);

        separator = new JSeparator();
        
        labelEstado = new JLabel("Estado");
        
        buttonGroup = new ButtonGroup();

        radioButtonPrivado = new JRadioButton("Privado");
        radioButtonPrivado.setActionCommand("Privado");
        radioButtonPrivado.setSelected(true);
        buttonGroup.add(radioButtonPrivado);

        radioButtonPublico = new JRadioButton("Publico");
        radioButtonPublico.setActionCommand("Publico");
        buttonGroup.add(radioButtonPublico);
        
        labelTipo = new JLabel("Tipo");
        
        comboBox = new JComboBox();
        comboBox.addItem("Otro");
        comboBox.addItem("Texto");
        comboBox.addItem("Imagen");

        datos = new String[][]{};
        columnas = new String[]{"ESTADO", "TIPO", "NOMBRE"};
        defaultTableModel = new DefaultTableModel(datos, columnas);
        table = new JTable(defaultTableModel);
        scrollPane = new JScrollPane(table);
        
        labelMaximo = new JLabel("Maximo 64 KB");
        
        buttonActualizar = new JButton("Actualizar");
        accionActualizar = new AccionActualizar(labelNombre, buttonGroup);
        buttonActualizar.addActionListener(accionActualizar);

        buttonCargar = new JButton("Cargar...");
        accionCargar = new AccionCargar(labelNombre, comboBox, buttonGroup);
        buttonCargar.addActionListener(accionCargar);
        
        buttonVer = new JButton("Ver");
        accionVer = new AccionVer(defaultTableModel, labelNombre);
        buttonVer.addActionListener(accionVer);

        buttonDescargar = new JButton("Descargar...");
        accionDescargar = new AccionDescargar(labelNombre);
        buttonDescargar.addActionListener(accionDescargar);
        
        buttonBorrar = new JButton("Borrar");
        accionBorrar = new AccionBorrar(labelNombre);
        buttonBorrar.addActionListener(accionBorrar);

        buttonCerrar = new JButton("Cerrar");
        accionCerrar = new AccionCerrar(container);
        buttonCerrar.addActionListener(accionCerrar);

        gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.insets = new Insets(15, 0, 0, 15);
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        add(labelNombre, gridBagConstraints);
        
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(separator, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.gridwidth = 1;
        add(labelEstado, gridBagConstraints);
        
        add(radioButtonPrivado, gridBagConstraints);

        add(radioButtonPublico, gridBagConstraints);
        
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        add(buttonActualizar, gridBagConstraints);
        
        gridBagConstraints.gridwidth = 1;
        add (labelTipo, gridBagConstraints);

        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        add(comboBox, gridBagConstraints);
        
        gridBagConstraints.gridwidth = 1;
        add(buttonCargar, gridBagConstraints);
        
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        add(labelMaximo, gridBagConstraints);
        
        separator = new JSeparator();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(separator, gridBagConstraints);
        
        panelBotones = new JPanel();
        flowLayout = new FlowLayout();
        panelBotones.setLayout(flowLayout);
        panelBotones.add(buttonVer);
        panelBotones.add(buttonDescargar);
        panelBotones.add(buttonBorrar);
        gridBagConstraints.fill = GridBagConstraints.NONE;
        add(panelBotones, gridBagConstraints);
        
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        add(scrollPane, gridBagConstraints);
        
        separator = new JSeparator();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        add(separator, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.NONE;
        add(buttonCerrar, gridBagConstraints);
    }
}
