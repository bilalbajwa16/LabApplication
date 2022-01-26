package lab.pak.com.glacialappportal.task;

import com.google.gson.annotations.SerializedName;

public class Tasks {

    @SerializedName("id")
    private int id;
    @SerializedName("agent")
    private String agent;
    @SerializedName("userid")
    private String name;
    @SerializedName("email")
    private String species;
    @SerializedName("description")
    private String breed;
    @SerializedName("accomplished")
    private String designation;
    @SerializedName("state")
    private String type;


    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    @SerializedName("picture")
    private String picture;
    @SerializedName("love")
    private Boolean love;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    //
    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /*    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
*/

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getLove() {
        return love;
    }

    public void setLove(Boolean love) {
        this.love = love;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
