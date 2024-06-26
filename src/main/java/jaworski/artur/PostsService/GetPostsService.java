package jaworski.artur.PostsService;

import jaworski.artur.to.Post;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class to get data from REST API
 */
@Log4j2
@Service
public class GetPostsService implements IGetPostsService {

    @Value("${URI.posts}")
    private String URI;
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

    protected void setURI(String URI) {
        this.URI = URI;
    }
}
