package game;

import city.cs.engine.SoundClip;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Simple class that represents the music in the game.
 *
 *
 * @author GIUSEPPE
 */
public class Music {

    private SoundClip sound;

    /**
     *
     * @param filePath the filepath for the sound to be played.
     */
    public Music(String filePath) {
        try {
            sound = new SoundClip(filePath);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Music.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Music.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Music.class.getName()).log(Level.SEVERE, null, ex);
        }
        sound.setVolume(0.25);
    }

    /**
     *
     * @return the soundclip
     */
    public SoundClip getMusic() {
        return sound;
    }

}
