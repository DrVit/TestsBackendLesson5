package lesson5;

import com.github.javafaker.Faker;
import lesson5.dto.Product;
import lesson5.service.CategoryService;
import lesson5.service.ProductService;
import lesson5.util.RetrofitUtils;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {
    static ProductService productService;
    static CategoryService categoryService;
    static String[] idStringNoInt = {"notIntId",  ",",  "33,24", "33.24", "1'","", "9999999999", "-9999999999",
            "9999999999999999999999999999999999999999999999999999999999999999999999999"};
    static String[] idStringInt = {"0", "-1", "-2147483648","2147483647", "4294967295"};
    static String[] intPositiveId = {"2147483647", "4294967295"};
    static String[] intNegativeId = {"0", "-1", "-2147483648"};
    Product product;
    Product putProduct;
    String id;
    Faker faker = new Faker();


    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);
    }
}
