package lesson5;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteNegative400Test extends BaseTest{

    @SneakyThrows
    @Test
    void tearDown() {
       for( int i = 0; i < idStringNoInt.length; i++) {
           id = idStringNoInt[i];
           Response<ResponseBody> response = productService.deleteProduct(id).execute();
           assertThat(response.isSuccessful(), CoreMatchers.is(false));
           assertThat(response.code(), equalTo(400));
       }
    }
}
