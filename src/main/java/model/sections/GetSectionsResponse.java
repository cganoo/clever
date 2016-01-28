package model.sections;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cganoo on 28/01/2016.
 */
public class GetSectionsResponse {
    @SerializedName("data")
    private List<SectionWithUri> data;

    public List<SectionWithUri> getData() {
        return data;
    }

    public void setData(List<SectionWithUri> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetSectionsResponse{" +
                "data=" + data +
                '}';
    }
}
