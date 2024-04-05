package jaworski.artur;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service to do everything :)
 */
@org.springframework.stereotype.Service
@Log4j2
public class Service {

    GetPostsController getPostsController;


    public void perform() {

        log.info(getPostsController.getPostsFromAPI());
    }

    @Autowired
    public void setJsonGetController(GetPostsController getPostsController) {
        this.getPostsController = getPostsController;
    }
}
