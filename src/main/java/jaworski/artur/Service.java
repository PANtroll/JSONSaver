package jaworski.artur;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * Service to do everything :)
 */
@org.springframework.stereotype.Service
@Log4j2
@RequiredArgsConstructor
public class Service implements IService {

    private final IGetPostsController getPostsController;
    private final IJSONSaver jsonSaver;

    public void perform() {

        List<Post> posts = getPostsController.getPostsFromAPI();
        if (posts.isEmpty()) {
            return;
        }
        if (jsonSaver.saveToFile(posts)) {
            log.info("All files saved successfully");
        }
    }
}
