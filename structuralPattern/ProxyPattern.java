
/*
    Proxy pattern:
        - acts as a layer that can be used for privacy, security, filtering,.... still following the SRP.

    Client -> Proxy(layer that filters,...) -> Service/Object
    Client -> Proxy1 -> Proxy2 -> .... -> Service/Object

Problem:

class VideoDownloader{
    public void downloadVideo(String videoURL){
        // adding caching logic violates the SRP and makes it tightly coupled.

        // in future, more requirements like filtering, privacy checks,....

        System.out.println("Downloading the video using:"+videoURL);
    }
}

class Client{
    public static void main(String[] args) {
        VideoDownloader videoDownloader = new VideoDownloader();
        videoDownloader.downloadVideo("video-1");

        VideoDownloader videoDownloader2 = new VideoDownloader();

        // downloading same video(costlier - lets introduce caching in video downloader)
        videoDownloader2.downloadVideo("video-1");
    }
}

 */

import java.util.*;

interface VideoDownloader{
    String downloadVideo(String videoURL);
}

class MainVideoDownloader implements VideoDownloader{

    @Override
    public String downloadVideo(String videoURL) {
       System.out.println("Downloading the video using:"+videoURL);

       return "video content for "+videoURL;
    }
    
}

// proxy with caching
class CachedVideoDownload implements VideoDownloader{
    private VideoDownloader videoDownloader;

    private Map<String, String> cache;

    public CachedVideoDownload(){
        this.videoDownloader = new MainVideoDownloader();

        this.cache = new HashMap<>();
    }

    @Override
    public String downloadVideo(String videoURL) {
        if(cache.containsKey(videoURL)){
            System.out.println("Returning the content of "+videoURL+" from cache");

            return "Cached content for"+videoURL;
        }

        String content = this.videoDownloader.downloadVideo(videoURL);

        cache.put(videoURL, content);

        return content;
    }

}

class FilteredVideoDownloader implements VideoDownloader{
    private VideoDownloader videoDownloader;
    
    private Set<String> blockedWords;

    public FilteredVideoDownloader(VideoDownloader videoDownloader){
        this.videoDownloader = videoDownloader;

        this.blockedWords = new HashSet<>(Set.of("pattern", "design"));
    }

    private boolean doesContainsBlockedWords(String videoUrl){
        for(String word: blockedWords){
            if(videoUrl.toLowerCase().contains(word)){
                return true;
            }
        }

        return false;
    }

    @Override
    public String downloadVideo(String videoURL) {
        if(doesContainsBlockedWords(videoURL)){
            System.out.println("Video download blocked... for "+videoURL);

            return "Video download blocked... for "+videoURL;
        }

        return this.videoDownloader.downloadVideo(videoURL);
    }

}

public class ProxyPattern {
    public static void main(String[] args) {
        // VideoDownloader downloader1 = new CachedVideoDownload();

        // downloader1.downloadVideo("Video-1");

        // downloader1.downloadVideo("Video-1");

        VideoDownloader filteredDownloader = new FilteredVideoDownloader(new CachedVideoDownload());

        filteredDownloader.downloadVideo("video-1");

        filteredDownloader.downloadVideo("video-1");
        
        filteredDownloader.downloadVideo("design video");

        filteredDownloader.downloadVideo("proxy video");
    }
}
