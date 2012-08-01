
package Simple;

import java.applet.Applet;
import java.applet.AudioClip;
/**
 *
 * @author foly
 */
public class AudioPlayer extends Applet{
    
    String file = "laser_trill.au";
    AudioClip au;
    
    @Override
    public void init(){
        if(au == null)
        au = getAudioClip(getCodeBase(), file);
        au.play();
        au.loop();
    }
    
    @Override
    public void start(){
       if(au != null){           
       }else{
           au = getAudioClip(getCodeBase(), file);
       }
       au.play();
    }
    
    @Override
    public void stop(){
        if(au != null){
            au.stop();
        }
    }
}
