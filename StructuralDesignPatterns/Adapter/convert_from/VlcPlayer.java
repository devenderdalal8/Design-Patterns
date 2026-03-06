package Adapter.convert_from;

public class VlcPlayer implements AdvancedMediaPlayer {

    @Override
    public void playVlx(String fileName) {
        System.out.println("  🎬 VLC Player: Playing VLC file → " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // VLC Player does not support MP4 format
        System.out.println("  ❌ VLC Player: MP4 format not supported.");
    }

}