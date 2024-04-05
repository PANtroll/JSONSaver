package jaworski.artur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model of JSON data
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private Long id;
    private Long userId;
    private String title;
    private String body;

}
