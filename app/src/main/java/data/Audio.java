package data;

/**
 * Created by DELL on 16-02-2016.
 */
public class Audio {



    private String audioName;
    private String audioTitle;

    public Audio(String audioName, String audioTitle) {
        this.audioName = audioName;
        this.audioTitle = audioTitle;
    }

    public String getAudioTitle() {
        return audioTitle;
    }

    public void setAudioTitle(String audioTitle) {
        this.audioTitle = audioTitle;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }


}
