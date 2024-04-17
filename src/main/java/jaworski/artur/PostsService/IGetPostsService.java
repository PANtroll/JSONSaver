package jaworski.artur.PostsService;

import jaworski.artur.to.Post;

import java.util.List;

public interface IGetPostsService {

    List<Post> getPostsFromAPI();
}
