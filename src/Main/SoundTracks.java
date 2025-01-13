package Main;

import javax.sound.sampled.*;

import java.io.IOException;
import java.net.URL;

public class SoundTracks {
    Clip musicClip;
    URL[] url = new URL[10];

    public SoundTracks() {
        url[0] = getClass().getResource("resources/BackgroundSound.wav");
        url[1] = getClass().getResource("resources/ClearLines.wav");
        url[2] = getClass().getResource("resources/Collision.wav");
        url[3] = getClass().getResource("resources/touchfloor.wav");
        url[4] = getClass().getResource("resources/GameOver.wav");


    }
    public void play(int i, boolean music) {
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url[i]);
            Clip clip = AudioSystem.getClip();

            if(music) {
                musicClip = clip;
            }
            clip.open(audioInputStream);
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if(event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                }
            });
            audioInputStream.close();
            clip.start();

        } catch (UnsupportedAudioFileException e) {
            System.err.println("Không thể đọc tệp âm thanh: " + url[i]);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Lỗi đọc tệp âm thanh: " + url[i]);
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.err.println("Không thể mở kênh âm thanh: " + url[i]);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Lỗi không xác định khi phát âm thanh: " + url[i]);
            e.printStackTrace();
        }
    }


    public void loop() {
        musicClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {

        musicClip.stop();
        musicClip.close();
    }
}
