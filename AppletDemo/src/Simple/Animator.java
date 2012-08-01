package Simple;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Animator extends Applet implements Runnable {

    int frameNumber = -1;
    int delay;
    Thread animatorThread;
    boolean frozen = false;

    @Override
    public void init() {
        String str = "4";
        int delayTime = 10;

        //str = getParameter("DELAYTIME");
        try {
            if (str != null) {
                delayTime = Integer.parseInt(str);
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        delay = (delayTime > 0) ? (1000 / delayTime) : 100;
        
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (frozen) {
                    frozen = false;
                    start();
                } else {
                    frozen = true;
                    stop();
                }
            } //END mousePressed Method
        });
    } //END init Method

    @Override
    public void start() {
        if (frozen) {
            //Do nothing to stop
        } else {
            if(animatorThread == null){
                animatorThread = new Thread(this);
            }
            animatorThread.start();
        }
    } //END start Method

    @Override
    public void stop() {
        animatorThread = null;        
    } //END stop Method
    
    @Override
    public void paint(Graphics g){        
        g.drawString("Frame" + frameNumber, 0, 30);
    }//END paint Method

    @Override
    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        long startTime = System.currentTimeMillis();
        Thread currentThread = Thread.currentThread();
        while(currentThread == animatorThread){
            frameNumber++;
            repaint();
            try{
                startTime += delay;
                Thread.sleep(Math.max(0, startTime - System.currentTimeMillis()));
            }catch(InterruptedException e){
                System.err.println(e);
            } //END try-catch
        } //END while loop
    } //END run Method
}

