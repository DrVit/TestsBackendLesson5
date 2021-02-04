package ru.geekbrains.java4.lesson6.db.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.geekbrains.java4.lesson6.db.dto.getCategoryResponse;

;

public interface CategoryService {

    @GET("categories/{id}")
    Call<getCategoryResponse> getCategory(@Path("id") int id);

}
