import java.util.*;

/* 

Problem:    
    - Iterator of elements in the components in client revealing the internal implementation details


class Video{
    private String title;

    public Video(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }
}

class YoutubePlaylist{
    List<Video> videos;

    public YoutubePlaylist(){
        this.videos = new ArrayList<>();
    }

    public void addVideo(Video video){
        this.videos.add(video);
    }

    public List<Video> getVideos(){
        return videos;
    }
}

class Main{
    public static void main(String[] args) {
        Video video1 = new Video("video-1");

        Video video2 = new Video("video-2");

        YoutubePlaylist youtubePlaylist = new YoutubePlaylist();
        youtubePlaylist.addVideo(video1);
        youtubePlaylist.addVideo(video2);

        // client logic knows the entire datastructure used in playlist class
            // iterator pattern just provides only iterator that client 
                //can only iterator without knowing the internal implementation details.
        for(Video videoItem: youtubePlaylist.getVideos()){
            System.out.println(videoItem.getTitle());
        }
    }
}

*/

class Video{
    private String title;

    public Video(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }
}

interface PlaylistIterator{
    Video next();

    boolean hasNext();
}

class YoutubePlaylistIterator implements PlaylistIterator{
    private List<Video> videos;

    private int index;

    public YoutubePlaylistIterator(List<Video> videos){
        this.videos = videos;

        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < this.videos.size();
    }

    @Override
    public Video next() {
        return hasNext() ? this.videos.get(index++):null;
    }

}

class YoutubePlaylistTrendingIterator implements PlaylistIterator{
    private List<Video> videos;

    private int index;

    private List<Video> filterTrending(List<Video> videos){
        // trending videos filter logic
        return videos;
    }

    public YoutubePlaylistTrendingIterator(List<Video> videos){
        this.videos = filterTrending(videos);

        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < this.videos.size();
    }

    @Override
    public Video next() {
        return hasNext() ? this.videos.get(index++):null;
    }

}


abstract class Playlist{
    protected List<Video> videos;

    public Playlist(){
        this.videos = new ArrayList<>();
    }

    void addVideo(Video video){
        this.videos.add(video);
    }

    abstract PlaylistIterator createIterator();
}

class YoutubePlaylist extends Playlist{
    @Override
    public PlaylistIterator createIterator() {
       return new YoutubePlaylistTrendingIterator(this.videos);
    //    return new YoutubePlaylistIterator(this.videos);
    }
}


public class IteratorPattern {
    public static void main(String[] args) {
        Video video1 = new Video("video-1");

        Video video2 = new Video("video-2");

        YoutubePlaylist youtubePlaylist = new YoutubePlaylist();
        youtubePlaylist.addVideo(video1);
        youtubePlaylist.addVideo(video2);

        // client code doesn't have any info about the datastructure or logic implemented in the service
        PlaylistIterator iterator = youtubePlaylist.createIterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next().getTitle());
        }


        List<Integer> arr = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        arr.add(6);
        
        Iterator<Integer> it = arr.iterator();
        
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}