package jaworski.artur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Model of JSON data
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Post {

    private Long id;
    private Long userId;
    private String title;
    private String body;

}
