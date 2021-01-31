package lesson5.service;

import lesson5.dto.Product;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ProductService {

    @POST("products")
    Call<Product> createProduct(@Body Product createProductRequest);

    @PUT("products")
    Call<Product> modifyProduct(@Body Product modifyProductRequest);

    @GET("products/{id}")
    Call<ResponseBody> getProduct(@Path("id") String id);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") String id);

}
