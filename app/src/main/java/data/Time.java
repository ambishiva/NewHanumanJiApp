package data;

/**
 * Created by DELL on 10-02-2016.
 */
public class Time {

    private String day;
    private String hours;
    private String minutes;
    private String alarmTitle;
    private String weekDay;
    private String time;
    private int id;
    private String parentId;

    public Time(String day, String hours, String minutes,String alarmTitle,int id,String parentId) {
        this.day = day;
        this.hours = hours;
        this.minutes = minutes;
        this.alarmTitle = alarmTitle;
        this.id = id;
        this.parentId = parentId;
    }

    public Time(String day, String hours, String minutes,String alarmTitle,String weekDay,int id,String parentId) {
        this.day = day;
        this.hours = hours;
        this.minutes = minutes;
        this.alarmTitle = alarmTitle;
        this.weekDay = weekDay;
        this.id = id;
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time(String parentId,String time,String alarmTitle){

        this.parentId = parentId;
        this.time = time;
        this.alarmTitle = alarmTitle;

    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }


    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getAlarmTitle() {
        return alarmTitle;
    }

    public void setAlarmTitle(String alarmTitle) {
        this.alarmTitle = alarmTitle;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

}
