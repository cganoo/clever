package model.districts;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cganoo on 28/01/2016.
 */
public class GetDistrictsResponse {

    @SerializedName("data")
    private List<DistrictWithUri> data;

    public List<DistrictWithUri> getData() {
        return data;
    }

    public void setData(List<DistrictWithUri> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetDistrictsResponse{" +
                "data=" + data +
                '}';
    }
}
