package main;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundUrl[] = new URL[30];

    public Sound(){
        soundUrl[0] = getClass().getResource("/sound/theme-song.wav");
        soundUrl[1] = getClass().getResource("/sound/cha-chin-register.wav");
        soundUrl[2] = getClass().getResource("/sound/door-unlock.wav");
        soundUrl[3] = getClass().getResource("/sound/power-up.wav");
        soundUrl[4] = getClass().getResource("/sound/fanfare.wav");

    }
    public void setFile(int index){
        try{
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundUrl[index]);
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void play(){
        clip.start();
    }
    public void stop(){
        clip.stop();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
