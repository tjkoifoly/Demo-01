
package Simple;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author foly
 */
public class SumCalculator extends Applet implements ActionListener
{
    //Declarate the fields of Applet:
    TextField txtElement1;
    TextField txtElement2;
    
    @Override
    public void init(){
        txtElement1 = new TextField(10);
        txtElement2 = new TextField(10);
        
        add(txtElement1);
        add(txtElement2);
        
        txtElement1.setText("0");
        txtElement2.setText("0");
        
        txtElement1.addActionListener(this);
        txtElement2.addActionListener(this);
    } //END init() Method.
    
    @Override
    public void paint(Graphics g){
        int value1;
        int value2;
        int sum;
        
        g.drawString("Type a number in each box.", 40, 50);
        g.drawString("The a sum of the value is : ", 40, 75);
        
        String s = txtElement1.getText();
        value1 = Integer.parseInt(s);
        s = txtElement2.getText();
        value2 = Integer.parseInt(s);
        
        sum = value1 + value2;
        
        s = String.valueOf(sum);
        g.drawString(s, 80, 100);
    } //END pait() Method.

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    } //END actionPerformed() Method.
    
}
