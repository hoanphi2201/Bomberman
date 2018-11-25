/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

    public String fileName;
    public  Clip clipTheme;
    public  Clip clipStart;
    public  Clip clipKeyDown;
    public  Clip clipGetItem;
    public  Clip clipExplosion;
    public  Clip clipStep1;
    public  Clip clipStep2;
    public  Clip clipDie;
    public  Clip clipScreen;
    public  Clip clipStageComplete;

    public Sound() {
    }

    public Sound(String file) {
        fileName = file;
    }
    

    public void setFile(String soundFileName, int clip) {
        try {
            File file = new File(soundFileName);
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            switch(clip){
                case 1: {
                    clipTheme = AudioSystem.getClip();
                    clipTheme.open(sound);
                }
                case 2: {
                    clipStart = AudioSystem.getClip();
                    clipStart.open(sound);
                }
                case 3: {
                    clipGetItem = AudioSystem.getClip();
                    clipGetItem.open(sound);
                }
                case 4: {
                    clipExplosion = AudioSystem.getClip();
                    clipExplosion.open(sound);
                }
                case 5: {
                    clipStep1 = AudioSystem.getClip();
                    clipStep1.open(sound);
                }
                case 6: {
                    clipDie = AudioSystem.getClip();
                    clipDie.open(sound);
                }
                case 7: {
                    clipScreen = AudioSystem.getClip();
                    clipScreen.open(sound);
                }
                 case 8: {
                    clipStep2 = AudioSystem.getClip();
                    clipStep2.open(sound);
                }
                 case 9: {
                    clipStageComplete = AudioSystem.getClip();
                    clipStageComplete.open(sound);
                }
            }
           
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
    public void play(int clip){
         switch(clip){
            case 1: {
                clipTheme.start();
            }
            case 2: {
                clipStart.start();
            }
            case 3: {
                clipGetItem.start();
            }
            case 4: {
                clipExplosion.start();
            }
            case 5: {
                clipStep1.start();
            }
            case 6: {
                clipDie.start();
            }
            case 7: {
                clipScreen.start();
            }
            case 8: {
                clipStep2.start();
            }
            case 9: {
                clipStageComplete.start();
            }
         }
    }
    public void close(int clip){
         switch(clip){
            case 1: {
                clipTheme.close();
            }
            case 7: {
                clipScreen.close();
            }
           
         
        }
    }
    public void pause(int clip){
        switch(clip){
            case 1: {
                clipTheme.stop();
            }
        }
        
    }
}
