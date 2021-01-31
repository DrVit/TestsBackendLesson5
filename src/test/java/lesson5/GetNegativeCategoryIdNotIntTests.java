package lesson5;

import lesson5.dto.getCategoryResponse;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetNegativeCategoryIdNotIntTests extends BaseTest{

    @SneakyThrows
    @Test
    void getCategoryByIdNegativeTest() {
//Тест на не int значение id - Должен выдавать код 400, error "Bad request"
        for (int i = 0; i < idStringNoInt.length; i++) {
            Response<getCategoryResponse> response = categoryService.getCategory(idStringNoInt[i]).execute();
            assertThat(response.isSuccessful(), CoreMatchers.is(false));
            assertThat(response.code(), equalTo(400));
        }
    }
}
