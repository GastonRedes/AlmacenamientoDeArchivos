package vistacontrol;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccionCerrar implements ActionListener{
    
    private final Container container;
    
    public AccionCerrar(Container container){
        
        this.container = container;
    }
    
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        
        PanelInicio panelInicio;
        
        panelInicio = new PanelInicio(container);
        container.removeAll();
        container.add(panelInicio);
        container.validate();
        container.repaint();
    }
}
