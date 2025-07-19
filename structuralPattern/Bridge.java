/*

Problem:
    - class explosion due to multiple dimensions of variability.

interface VideoPlayer{
    void play(String title);
}

class WebHDPlayer implements VideoPlayer{

    @Override
    public void play(String title) {
        System.out.println("Playing video in web with HD quality");
    }

}

class Web4kPlayer implements VideoPlayer{

    @Override
    public void play(String title) {
        System.out.println("Playing video in web with 4k quality");
    }

}

// When 8k quality need to be added, we have to add Web8k, mobile8k, smarttv8k,...
// leading to class explosion.

class MobileHDPlayer implements VideoPlayer{

    @Override
    public void play(String title) {
        System.out.println("Playing video in mobile with HD quality");
    }

}

class Mobile4kPlayer implements VideoPlayer{

    @Override
    public void play(String title) {
        System.out.println("Playing video in mobile with 4k quality");
    }

}

class Main{
    public static void main(String[] args) {
        VideoPlayer videoPlayer = new Mobile4kPlayer();
        
        videoPlayer.play("video-1");
    }
}

*/


// Implementor interface
interface VideoQuality{
    void load(String title);
}

// concrete implementations
class HDQuality implements VideoQuality{

    @Override
    public void load(String title) {
        System.out.println("HD quality loaded");
    }

}

// concrete implementations
class UltraHDQuality implements VideoQuality{

    @Override
    public void load(String title) {
        System.out.println("HD quality loaded");
    }

}

// Abstractions
abstract class VideoPlayer{
    protected VideoQuality videoQuality;

    public VideoPlayer(VideoQuality videoQuality){
        this.videoQuality = videoQuality;
    }

    abstract void play(String title);
}

// Refined Abstractions
class WebPlayer extends VideoPlayer{
    public WebPlayer(VideoQuality videoQuality){
        super(videoQuality);
    }

    @Override
    void play(String title) {
        System.out.println("Web Platform");

        this.videoQuality.load(title);
    }
    
}

class MobilePlayer extends VideoPlayer{
    public MobilePlayer(VideoQuality videoQuality){
        super(videoQuality);
    }

    @Override
    void play(String title) {
        System.out.println("Mobile Player");

        this.videoQuality.load(title);
    }

}

public class Bridge {
    public static void main(String[] args) {
        VideoPlayer player1 = new MobilePlayer(new HDQuality());
        
        player1.play("Video-1");

        VideoPlayer player2 = new MobilePlayer(new UltraHDQuality());
        
        player2.play("Video-1");
    }
}
