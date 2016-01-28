package model.districts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cganoo on 28/01/2016.
 */
public class DistrictWithUri {

    @SerializedName("data")
    private District district;

    @SerializedName("uri")
    private String uri;

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "DistrictWithUri{" +
                "district=" + district +
                '}';
    }
}
