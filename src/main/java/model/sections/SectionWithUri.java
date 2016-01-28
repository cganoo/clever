package model.sections;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cganoo on 28/01/2016.
 */
public class SectionWithUri {

    @SerializedName("data")
    private Section section;

    @SerializedName("uri")
    private String uri;

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "SectionWithUri{" +
                "section=" + section +
                ", uri='" + uri + '\'' +
                '}';
    }
}
