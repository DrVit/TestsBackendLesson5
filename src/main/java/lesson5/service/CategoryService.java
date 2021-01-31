package lesson5.service;

import lesson5.dto.getCategoryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryService {

    @GET("categories/{id}")
    Call<getCategoryResponse> getCategory(@Path("id") String id);

}
