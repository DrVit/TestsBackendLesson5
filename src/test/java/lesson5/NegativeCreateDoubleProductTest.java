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

public class NegativeCreateDoubleProductTest extends BaseTest{

    String id1;

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
// Проверка на создание дублирующих записей
            Response<Product> response = productService.createProduct(product)
                    .execute();
            id = response.body().getId();
            assertThat(response.isSuccessful(), CoreMatchers.is(true));

            Response<Product> responseRepeat = productService.createProduct(product)
                    .execute();
            id1 = responseRepeat.body().getId();
            assertThat(responseRepeat.isSuccessful(), CoreMatchers.is(false));

    }

    @SneakyThrows
    @AfterEach
    void tearDown() {

            Response<ResponseBody> response = productService.deleteProduct(id).execute();
            assertThat(response.isSuccessful(), CoreMatchers.is(true));

    }
    @SneakyThrows
    @AfterEach
        void tearDownRepeat() {
            Response<ResponseBody> response = productService.deleteProduct(id1).execute();
            assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
