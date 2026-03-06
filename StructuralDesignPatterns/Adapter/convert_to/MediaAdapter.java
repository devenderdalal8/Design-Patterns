package Adapter.convert_to;

import Adapter.convert_from.AdvancedMediaPlayer;

public class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer = new Adapter.convert_from.VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer = new Adapter.convert_from.Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        switch (audioType.toLowerCase()) {
            case "vlc":
                advancedMediaPlayer.playVlx(fileName);
                break;
            case "mp4":
                advancedMediaPlayer.playMp4(fileName);
                break;
            default:
                System.out.println("No Extension Supports");
                break;
        }
    }

}
