package api;

import model.districts.GetDistrictsResponse;
import model.sections.GetSectionsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Representing Clever API as an interface via Retrofit
 * See: https://clever.com/developers/docs/explorer#api_data
 *
 * Created by cganoo on 28/01/2016.
 */
public interface CleverAPI {

    @Headers("Authorization: Bearer DEMO_TOKEN")
    @GET("districts")
    Call<GetDistrictsResponse> getDistricts();

    @Headers("Authorization: Bearer DEMO_TOKEN")
    @GET("districts/{id}/sections")
    Call<GetSectionsResponse> getSectionsForDistrict(@Path("id") String id);
}
