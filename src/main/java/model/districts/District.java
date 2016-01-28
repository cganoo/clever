package model.districts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cganoo on 28/01/2016.
 */
public class District {

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "District{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
