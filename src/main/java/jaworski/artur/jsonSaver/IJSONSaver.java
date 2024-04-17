package jaworski.artur.jsonSaver;

import jaworski.artur.to.Post;

import java.util.List;

public interface IJSONSaver {

    boolean saveToFile(List<Post> postsList);
}
