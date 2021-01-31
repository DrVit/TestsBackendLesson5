package lesson5;

import lesson5.dto.getCategoryResponse;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static lesson5.base.enums.Category.FOOD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetPositiveCategoryTest extends BaseTest{

    @SneakyThrows
    @Test
    void getCategoryByIdPositiveTest() {
        Response<getCategoryResponse> response = categoryService.getCategory(FOOD.id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @Test
    void getCategoryWithResponseAssertionPositiveTest() {
        Response<getCategoryResponse> response = categoryService.getCategory(FOOD.id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getId(), equalTo("1"));
        assertThat(response.body().getTitle(), equalTo(FOOD.title));
        response.body().getProducts().forEach(product ->
                assertThat(product.getCategoryTitle(), equalTo(FOOD.title)));

    }
}
