package model.sections;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cganoo on 28/01/2016.
 */
public class Section {

    @SerializedName("id")
    private String id;

    @SerializedName("students")
    private List<String> studentIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<String> studentIds) {
        this.studentIds = studentIds;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id='" + id + '\'' +
                ", studentIds=" + studentIds +
                '}';
    }
}
