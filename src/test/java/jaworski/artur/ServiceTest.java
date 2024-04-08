package jaworski.artur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

    @InjectMocks
    private Service cut;
    private final List<Post> posts = new ArrayList<>();
    @Mock
    private GetPostsController postsController;
    @Mock
    private JSONSaver jsonSaver;

    @BeforeEach
    public void setup() {
        posts.clear();
    }

    @Test
    void testPerformHappyPath() {
        //given
        posts.add(new Post(0L, 1L, "Title", "test"));
        Mockito.when(postsController.getPostsFromAPI()).thenReturn(posts);
        Mockito.when(jsonSaver.saveToFile(posts)).thenReturn(true);
        cut = new Service(postsController, jsonSaver);
        //then
        cut.perform();
        //when
        Mockito.verify(postsController, Mockito.times(1)).getPostsFromAPI();
        Mockito.verify(jsonSaver, Mockito.times(1)).saveToFile(posts);
    }

    @Test
    void testPerformHappyWhenDontGetData() {
        //given
        Mockito.when(postsController.getPostsFromAPI()).thenReturn(posts);
        Mockito.lenient().when(jsonSaver.saveToFile(posts)).thenReturn(true);
        cut = new Service(postsController, jsonSaver);
        //then
        cut.perform();
        //when
        Mockito.verify(postsController, Mockito.times(1)).getPostsFromAPI();
        Mockito.verifyNoInteractions(jsonSaver);
    }
}