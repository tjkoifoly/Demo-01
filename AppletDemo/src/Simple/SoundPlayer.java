package Simple;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class SoundPlayer extends Applet implements ActionListener {

    SoundList soundList;
    String onceFile = "GuiNhungNguoiPhuNuToiYeu.mp3";
    String loopFile = "train.au";
    AudioClip onceClip;
    AudioClip loopClip;
    Button playOnce;
    Button startLoop;
    Button stopLoop;
    Button reload;
    boolean looping = false;

    @Override
    public void init() {
        playOnce = new Button("Bark!");
        playOnce.addActionListener(this);
        add(playOnce);

        startLoop = new Button("Start sound loop");
        stopLoop = new Button("Stop sound loop");
        stopLoop.setEnabled(false);
        startLoop.addActionListener(this);
        add(startLoop);
        stopLoop.addActionListener(this);
        add(stopLoop);

        reload = new Button("Reload sounds");
        reload.addActionListener(this);
        add(reload);

        startLoadingSounds();
    }

    void startLoadingSounds() {
        //Start asynchronous sound loading.
        soundList = new SoundList(this, getCodeBase());
        soundList.startLoading(loopFile);
        soundList.startLoading(onceFile);
    }

    @Override
    public void stop() {
        onceClip.stop(); //Cut short the one-time sound.
        if (looping) {
            loopClip.stop(); //Stop the sound loop.
        }
    }

    @Override
    public void start() {
        if (looping) {
            loopClip.loop(); //Restart the sound loop.
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        //PLAY BUTTON
        Object source = event.getSource();
        if (source == playOnce) {
            if (onceClip == null) {
                //Try to get the AudioClip.
                onceClip = soundList.getClip(onceFile);
            }

            if (onceClip != null) { //If the sound is loaded:
                onceClip.play(); //Play it once.
                showStatus("Playing sound " + onceFile + ".");
            } else {
                showStatus("Sound " + onceFile + " not loaded yet.");
            }
            return;
        }

        //START LOOP BUTTON
        if (source == startLoop) {
            if (loopClip == null) {
                //Try to get the AudioClip.
                loopClip = soundList.getClip(loopFile);
            }

            if (loopClip != null) { //If the sound is loaded:
                looping = true;
                loopClip.loop(); //Start the sound loop.
                stopLoop.setEnabled(true); //Enable stop button.
                startLoop.setEnabled(false); //Disable start button.
                showStatus("Playing sound " + loopFile + " continuously.");
            } else {
                showStatus("Sound " + loopFile + " not loaded yet.");
            }
            return;
        }

        //STOP LOOP BUTTON
        if (source == stopLoop) {
            if (looping) {
                looping = false;
                loopClip.stop(); //Stop the sound loop.
                startLoop.setEnabled(true); //Enable start button.
                stopLoop.setEnabled(false); //Disable stop button.
            }
            showStatus("Stopped playing sound " + loopFile + ".");
            return;
        }

        //RELOAD BUTTON
        if (source == reload) {
            if (looping) { //Stop the sound loop.
                looping = false;
                loopClip.stop();
                startLoop.setEnabled(true); //Enable start button.
                stopLoop.setEnabled(false); //Disable stop button.
            }
            loopClip = null; //Reset AudioClip to null.
            onceClip = null; //Reset AudioClip to null.
            startLoadingSounds();
            showStatus("Reloading all sounds.");
            return;
        }
    }
}
/*
 * Code is the same in both 1.0 and 1.1.
 */

//Loads and holds a bunch of audio files whose locations are specified
//relative to a fixed base URL.
class SoundList extends java.util.Hashtable {

    Applet applet;
    URL baseURL;

    public SoundList(Applet applet, URL baseURL) {
        super(5); //Initialize Hashtable with capacity of 5 entries.
        this.applet = applet;
        this.baseURL = baseURL;
    }

    public void startLoading(String relativeURL) {
        SoundLoader soundLoader = new SoundLoader(applet, this, baseURL, relativeURL);
    }

    public AudioClip getClip(String relativeURL) {
        return (AudioClip) get(relativeURL);
    }

    public void putClip(AudioClip clip, String relativeURL) {
        put(relativeURL, clip);
    }
} //END SoundList Class.
/*
 * Code is the same in both 1.0 and 1.1.
 */

class SoundLoader extends Thread {

    Applet applet;
    SoundList soundList;
    URL baseURL;
    String relativeURL;

    public SoundLoader(Applet applet, SoundList soundList, URL baseURL,
            String relativeURL) {
        this.applet = applet;
        this.soundList = soundList;
        this.baseURL = baseURL;
        this.relativeURL = relativeURL;
        setPriority(MIN_PRIORITY);
        start();
    }

    @Override
    public void run() {
        AudioClip audioClip = applet.getAudioClip(baseURL, relativeURL);

        //AudioClips load too fast for me!
        //Simulate slow loading by adding a delay of up to 10 seconds.
        try {
            sleep((int) (Math.random() * 10000));
        } catch (InterruptedException e) {
        }

        soundList.putClip(audioClip, relativeURL);
    }
}
