package Adapter.convert_from;

public class Mp4Player implements Adapter.convert_from.AdvancedMediaPlayer {

    @Override
    public void playVlx(String fileName) {
        // MP4 Player does not support VLC format
        System.out.println("  ❌ MP4 Player: VLC format not supported.");
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("  🎬 MP4 Player: Playing MP4 file → " + fileName);
    }
    
}
