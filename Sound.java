import java.applet.*;

import javax.swing.*;

import java.io.*;

import java.net.*;



public class Sound {// Holds one audio file



    public AudioClip song; // Sound player

    public URL songPath; // Sound path

    Sound(String filename)

    {
    URL url = Location.class.getProtectionDomain().getCodeSource().getLocation();


  try

  {

     songPath = new URL(url,"theme.mp3"); // Get the Sound URL

     song = Applet.newAudioClip(songPath); // Load the Sound
  }

  catch(Exception e){
  } // Satisfy the catch

    }

    public void playSound()

    {

  song.loop(); // Play

    }
    public void stopSound()

    {

  song.stop(); // Stop

    }

    public void playSoundOnce()

    {

  song.play(); // Play only once

    }
}

