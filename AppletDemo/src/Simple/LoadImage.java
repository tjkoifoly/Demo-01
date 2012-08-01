
package Simple;

import java.applet.Applet;
import java.awt.*;
import java.net.URL;

public class LoadImage extends Applet {

   Image img = null;
   URL url;
    @Override
    public void init() {
        //img = getImage(getDocumentBase(), "images/2.jpg");       
        img = getImage(getCodeBase(), "images/4.jpg");
        System.out.println(getCodeBase());
        }
    
    @Override
    public void paint(Graphics g){
        g.drawImage(img, 0, 0, this);
    }
}
