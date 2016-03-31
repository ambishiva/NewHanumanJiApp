package data;

import java.util.ArrayList;

/**
 * Created by DELL on 17-02-2016.
 */
public class ShowData {


    private String parentId;
    private String title;
    private String time;
    private ArrayList<Time> arrayListTime;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<Time> getArrayListTime() {
        return arrayListTime;
    }

    public void setArrayListTime(ArrayList<Time> arrayListTime) {
        this.arrayListTime = arrayListTime;
    }
}
