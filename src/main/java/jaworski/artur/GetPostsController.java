package jaworski.artur;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class to get data from REST API
 */
@Log4j2
@Controller
public class GetPostsController {

    protected static final String URI = "https://jsonplaceholder.typicode.com/posts";
    private RestTemplate restTemplate = new RestTemplate();

    public List<Post> getPostsFromAPI() {

        List<Post> result = new ArrayList<>();
        try {
            ResponseEntity<Post[]> fetchedObject = restTemplate.getForEntity(URI, Post[].class);
            if (!HttpStatus.OK.equals(fetchedObject.getStatusCode())) {
                log.warn("Status code is not 200 for getting data from: " + URI);
                return result;
            }
            Post[] body = fetchedObject.getBody();
            if (body != null && body.length > 0) {
                result = Arrays.asList(body);
            }
        } catch (Exception e) {
            log.error("Error when fetching data from API: " + URI);
        }

        return result;
    }

    protected void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
