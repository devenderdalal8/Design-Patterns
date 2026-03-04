package Adapter;

import Adapter.convert_to.AudioAdapter;

public class Main {
    public static void main(String[] args) {

        AudioAdapter player = new AudioAdapter();

        System.out.println("======= Playing Media Files =======\n");

        player.play("mp3", "song.mp3");
        player.play("mp4", "movie.mp4");
        player.play("vlc", "documentary.vlc");
        player.play("avi", "clip.avi");         // unsupported format
    }
}


//  Client
//     │
//     │ uses
//     ▼
//  «interface»          «class»
//   MediaPlayer  ◄───  MediaAdapter ────► AdvancedMediaPlayer
//   play()              play()              playVlc()
//                       (translates)        playMp4()
//                            │
//                     ┌──────┴──────┐
//                     ▼             ▼
//                VlcPlayer      Mp4Player