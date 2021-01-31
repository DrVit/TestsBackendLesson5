package lesson5;

import lesson5.base.enums.Category;
import lesson5.dto.Product;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;

public class PositiveCreateProductTest extends BaseTest{


    @BeforeEach
    void setUp() {

        product = new Product()
//                .withId((int) (Math.random()*10000))
                .withTitle(faker.food().ingredient())
        .withCategoryTitle(Category.FOOD.title)
        .withPrice((int) (Math.random()*10000));

    }

    @SneakyThrows
    @Test
    void createInProductFoodCategoryTest() {
// Create product
        Response<Product> response = productService.createProduct(product)
                .execute();

        id = response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

// Modify product
        putProduct = new Product()
                .withId(id)
                .withTitle(faker.food().ingredient())
                .withCategoryTitle(Category.FOOD.title)
                .withPrice((int) (Math.random() * 10000));

        Response<Product> modifyResponse = productService.modifyProduct(putProduct)
                .execute();
        assertThat(modifyResponse.isSuccessful(), CoreMatchers.is(true));

// Get product by id
        Response<ResponseBody> getResponse = productService.getProduct(id).execute();
        assertThat(getResponse.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

    }
}
