
package Simple;

import java.awt.BorderLayout;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.UIManager;

public class LayoutExample extends JApplet{
    
    JButton btnNorth;
    JButton btnWest;
    JButton btnEast;
    JButton btnSouth;
    JButton btnCenter;
    
    @Override
    public void init(){
        
        try{           
            UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
        }catch(Exception e){
            System.err.println(e);
        }
        
        setLayout(new BorderLayout());
        
        btnNorth = new JButton("North");
        btnWest = new JButton("West");
        btnEast = new JButton("East");
        btnSouth = new JButton("South");
        btnCenter = new JButton("Center");
        
        add(btnNorth, BorderLayout.NORTH);
        add(btnWest, BorderLayout.WEST);
        add(btnEast, BorderLayout.EAST);
        add(btnSouth, BorderLayout.SOUTH);
        add(btnCenter, BorderLayout.CENTER);
        
    } //END init() Method
    
}
