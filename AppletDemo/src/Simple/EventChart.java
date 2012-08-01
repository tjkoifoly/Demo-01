
package Simple;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class EventChart extends Applet {

    int initCount = 0;
    int startCount = 0;
    int stopCount = 0;
    int destroyCount = 0;
    
    @Override
    public void init() {
        initCount++;
        System.out.println("init");
        repaint();
    }//END method init
    
    @Override
    public void paint(Graphics g){
        //Xoa trang vung ve
        g.setColor(Color.white);
        g.fillRect(0, 0, getSize().width, getSize().height);
        
        //Ve lai cac truc cua do thi
        g.setColor(Color.red);
        g.drawLine(120, 20, 120, 220);
        g.drawLine(120, 220, 300, 220);
        
        //Ve lai cac nhan
        g.setColor(Color.blue);
        g.drawString( "Init Count", 5, 50);
        g.drawString( "Start Count ", 5, 100);
        g.drawString( "Stop Count ", 5, 150);
        g.drawString( "Destroy Count ", 5, 200);
        
        //Ve luoi do thi
        g.setColor(Color.lightGray);
        for(int x = (120+25); x< 300; x+= 25){
            g.drawLine(x, 20, x, 199);
        }
        
        //Ve lai cac thanh bieu thi do thi
        g.setColor(Color.black);
        g.fillRect(120, 30, initCount*25, 40);
        g.fillRect(120, 80 , startCount*25, 40);
        g.fillRect(120, 130, stopCount*25, 40);
        g.fillRect(120, 180, destroyCount*25, 40);
        
    } //END method paint
    
//    @Override
//    public void update(Graphics g){
//        paint(g);
//    }//END method update
//    
    @Override
    public void start(){
        startCount++;
        System.out.println("start");
        repaint();
    }//END method start
    
    @Override
    public void stop(){
        stopCount++;
        System.out.println("stop");
        repaint();
    }//END method stop
        
    @Override
    public void destroy(){
        destroyCount++;
        System.out.println("destroy");
        repaint();
    }//END method destroy
               
}
