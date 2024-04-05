package jaworski.artur;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static jaworski.artur.GetPostsController.URI;


class GetPostsControllerTest {

    GetPostsController cut;

    @BeforeEach
    public void setup() {
        cut = new GetPostsController();
    }

    @Test
    public void fetchingPostsWhenStatusIsOkThenReturnPostsList() {
        //given
        Long id = 1L;
        Long userId = 2L;
        String title = "Title";
        String body = "Test";
        RestTemplate mockedRestTemplate = Mockito.mock();
        ResponseEntity<Post[]> response = new ResponseEntity<>(new Post[]{new Post(id, userId, title, body)}, HttpStatus.OK);
        Mockito.when(mockedRestTemplate.getForEntity(URI, Post[].class)).thenReturn(response);
        cut.setRestTemplate(mockedRestTemplate);
        //when
        List<Post> postFromAPI = cut.getPostsFromAPI();
        //then
        Assertions.assertThat(postFromAPI).isNotNull().hasSize(1);
        Post post = postFromAPI.get(0);
        Assertions.assertThat(post.getId()).isEqualTo(id);
        Assertions.assertThat(post.getUserId()).isEqualTo(userId);
        Assertions.assertThat(post.getTitle()).isEqualTo(title);
        Assertions.assertThat(post.getBody()).isEqualTo(body);
    }

    @Test
    public void fetchingPostsWhenStatusIsNotFoundThenReturnEmptyList() {
        //given
        Long id = 1L;
        Long userId = 2L;
        String title = "Title";
        String body = "Test";
        RestTemplate mockedRestTemplate = Mockito.mock();
        ResponseEntity<Post[]> response = new ResponseEntity<>(new Post[]{new Post(id, userId, title, body)}, HttpStatus.NOT_FOUND);
        Mockito.when(mockedRestTemplate.getForEntity(URI, Post[].class)).thenReturn(response);
        cut.setRestTemplate(mockedRestTemplate);
        //when
        List<Post> postFromAPI = cut.getPostsFromAPI();
        //then
        Assertions.assertThat(postFromAPI).isNotNull().hasSize(0);
    }

}