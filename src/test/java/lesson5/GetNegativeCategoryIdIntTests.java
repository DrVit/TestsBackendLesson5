package lesson5;

import lesson5.dto.getCategoryResponse;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetNegativeCategoryIdIntTests extends BaseTest {

    @SneakyThrows
    @Test
    void getCategoryByPosIdNegativeTest() {
// Тест на int значение id отсутствующие в базе - Должен выдавать код 400, "message" : "Unable to find category with id:" (значение 4294967295 - max INT UNSIGNED)
        for (int i = 0; i < intPositiveId.length; i++) {
        Response<getCategoryResponse> response = categoryService.getCategory(intPositiveId[i]).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
        assertThat(response.code(), equalTo(404));}
    }

    @SneakyThrows
    @Test
    void getCategoryByNegIdNegativeTest() {
// Тест на int значение id <= 0 - Должен выдавать код 404, "message" : "Bad request" (полагаю, что при обращении к базе id не может быть меньше 1)
        for (int i = 0; i < intNegativeId.length; i++) {
            Response<getCategoryResponse> response = categoryService.getCategory(intNegativeId[i]).execute();
            assertThat(response.isSuccessful(), CoreMatchers.is(false));
            assertThat(response.code(), equalTo(400));}
    }
}
