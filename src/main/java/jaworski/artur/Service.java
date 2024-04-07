package jaworski.artur;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Service to do everything :)
 */
@org.springframework.stereotype.Service
@Log4j2
public class Service implements IService {

    private IGetPostsController getPostsController;
    private IJSONSaver jsonSaver;

    public void perform() {

        List<Post> posts = getPostsController.getPostsFromAPI();
        if (posts.isEmpty()) {
            return;
        }
        if (jsonSaver.saveToFile(posts)) {
            log.info("All files saved successfully");
        }
    }

    @Autowired
    protected void setGetPostsController(IGetPostsController getPostsController) {
        this.getPostsController = getPostsController;
    }

    @Autowired
    protected void setJsonSaver(IJSONSaver jsonSaver) {
        this.jsonSaver = jsonSaver;
    }
}
