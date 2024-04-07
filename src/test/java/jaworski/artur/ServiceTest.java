package jaworski.artur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class ServiceTest {

    private Service cut;
    private final List<Post> posts = new ArrayList<>();

    @BeforeEach
    public void setup() {
        cut = new Service();
    }

    @Test
    void testPerformHappyPath() {
        //given
        posts.add(new Post(0L, 1L, "Title", "test"));
        GetPostsController postsController = Mockito.mock();
        Mockito.when(postsController.getPostsFromAPI()).thenReturn(posts);
        cut.setGetPostsController(postsController);
        JSONSaver jsonSaver = Mockito.mock();
        Mockito.when(jsonSaver.saveToFile(posts)).thenReturn(true);
        cut.setJsonSaver(jsonSaver);
        //then
        cut.perform();
        //when
        Mockito.verify(postsController, Mockito.times(1)).getPostsFromAPI();
        Mockito.verify(jsonSaver, Mockito.times(1)).saveToFile(posts);
    }

    @Test
    void testPerformHappyWhenDontGetData() {
        //given
        posts.clear();
        GetPostsController postsController = Mockito.mock();
        Mockito.when(postsController.getPostsFromAPI()).thenReturn(posts);
        cut.setGetPostsController(postsController);
        JSONSaver jsonSaver = Mockito.mock();
        Mockito.when(jsonSaver.saveToFile(posts)).thenReturn(true);
        cut.setJsonSaver(jsonSaver);
        //then
        cut.perform();
        //when
        Mockito.verify(postsController, Mockito.times(1)).getPostsFromAPI();
        Mockito.verify(jsonSaver, Mockito.times(0)).saveToFile(posts);
    }
}