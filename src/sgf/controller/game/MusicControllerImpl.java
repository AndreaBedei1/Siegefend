package sgf.controller.game;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Controller for the music that manages the tracks random choice.
 * We thank Alex-Productions for the creation and free distribution of "Extreme Trap Racing Music" (our sound3.wav).
 */
public class MusicControllerImpl implements MusicController {
    private static final int SONGS_NUMBER = 3;
    private static final int  DURATION = 3000;
    private boolean musicOn;
    private Clip c;

    /**
     * Constructor that makes the music beginning with first track. 
     */
    public MusicControllerImpl() {
        this.musicOn = true;
        this.play("sound1");
        this.thread();
    }

    @Override
    public void musicOn() {
        this.c.start();
        this.musicOn = true;
    }

    @Override
    public void musicOff() {
        this.musicOn = false;
        this.c.stop();
    }

    @Override
    public boolean isMusicPlaying() {
        return this.musicOn;
    }

    // This method opens an audio stream and start the music file.
    private void play(final String fileName) {
        final String musicFile = "musics/" + fileName + ".wav";
        try (InputStream stream = ClassLoader.getSystemResourceAsStream(musicFile)) {
                try (AudioInputStream a = AudioSystem.getAudioInputStream(new BufferedInputStream(stream))) {
                    c = AudioSystem.getClip();
                    c.open(a);
                    c.start();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    e.printStackTrace();
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Thread that plays and changes track.
    private void thread() {
        final Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (musicOn) {
                    if (!c.isRunning()) {
                        changeMusic();
                    }
                    try {
                        Thread.sleep(DURATION);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    private void changeMusic() {
        final Random r = new Random();
        this.play("sound" + (r.nextInt(SONGS_NUMBER) + 1));
    }
}
