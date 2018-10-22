package vistacontrol;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Ventana extends JFrame {

    public Ventana() {

        super("Almacenamiento de archivos");
        
        Dimension dimension; 
        Image icon;
        JMenuBar menuBar;
        JMenu menuArchivo, menuAcerca;
        JMenuItem itemSalir, itemAcerca;
        AccionSalir accionSalir;
        AccionAcerca accionAcerca;
        BorderLayout borderLayout;
        PanelInicio panelInicio;
        
        setSize(850, 850);
        setLocationRelativeTo(null);
        
        dimension = new Dimension(850, 850);
        setMinimumSize(dimension);
        
        icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagen/icon.png"));
        setIconImage(icon);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        menuArchivo = new JMenu("Archivo");
        itemSalir = new JMenuItem("Salir");
        accionSalir = new AccionSalir();
        itemSalir.addActionListener(accionSalir);
        menuArchivo.add(itemSalir); 
        menuBar.add(menuArchivo);
        
        menuAcerca = new JMenu("Acerca");
        itemAcerca = new JMenuItem("Acerca de...");
        accionAcerca = new AccionAcerca();
        itemAcerca.addActionListener(accionAcerca);
        menuAcerca.add(itemAcerca);
        menuBar.add(menuAcerca); 
        
        borderLayout = new BorderLayout();
        getContentPane().setLayout(borderLayout);
        
        panelInicio = new PanelInicio(getContentPane());
        getContentPane().add(panelInicio);
    }
}
