package lab.pak.com.glacialappportal.Notifications;

import com.google.gson.annotations.SerializedName;

public class Tasks {

    @SerializedName("id")
    private int id;

    @SerializedName("message")
private String taskmessage;

    @SerializedName("dateattribute")
    private String dateattribute;

    @SerializedName("taskid")
    private String tid;

    @SerializedName("value")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTaskmessage() {
        return taskmessage;
    }

    public void setTaskmessage(String taskmessage) {
        this.taskmessage = taskmessage;
    }



    @SerializedName("info")
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getDateattribute() {
        return dateattribute;
    }

    public void setDateattribute(String dateattribute) {
        this.dateattribute = dateattribute;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


