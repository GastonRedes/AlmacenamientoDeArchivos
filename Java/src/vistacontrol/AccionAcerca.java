package vistacontrol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class AccionAcerca implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        
        JOptionPane.showMessageDialog(null, "Version 1.0");
    }
}
