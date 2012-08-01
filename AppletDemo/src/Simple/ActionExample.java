
package Simple;

import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.TextField;
import javax.swing.UIManager;

public class ActionExample extends JApplet implements ActionListener{

    Button btnChange;
    Button btnSet;
    TextField txtDescription;
    CheckboxGroup radioGroup;
    Checkbox rdoRed;
    Checkbox rdoBlue;
    Checkbox rdoGreen;
    
    @Override
    public void init(){
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch(Exception e){
            System.err.println(e);
        }
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
        btnChange = new Button("Change");
        btnSet = new Button("Reset");
        txtDescription = new TextField("Type here something", 20);
        radioGroup = new CheckboxGroup();
        rdoRed = new Checkbox("Red", radioGroup, false);
        rdoBlue = new Checkbox("Blue", radioGroup, true);
        rdoGreen = new Checkbox("Green", radioGroup, false);
        
        add(btnChange);
        add(btnSet);
        add(txtDescription);
        add(rdoRed);
        add(rdoBlue);
        add(rdoGreen);
        
        btnChange.addActionListener(this);
        btnSet.addActionListener(this);
    }
    
    @Override
    public void paint(Graphics g){
        if(rdoRed.getState()){
            g.setColor(Color.red);
        }else if(rdoBlue.getState()){
            g.setColor(Color.blue);
        }else{
            g.setColor(Color.green);
        }
        String content = txtDescription.getText();
        g.drawString(content, 20, 100);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object objEvent = e.getSource();
        if(objEvent == btnChange){
            repaint();
        }else if(objEvent == btnSet){
            btnSet.setLabel("Reset");
            txtDescription.setText("Hello Applet");
            repaint();
        }
    }
    
}
