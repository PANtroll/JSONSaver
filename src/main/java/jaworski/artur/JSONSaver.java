package jaworski.artur;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Log4j2
@Component
public class JSONSaver implements IJSONSaver {
    protected static final String FILES_DICTIONARY = "src/main/resources/jaworski/artur/files/";
    protected static final String _JSON = ".json";
    private int savedFiles = 0;

    public boolean saveToFile(List<Post> postsList) {
        log.debug(() -> "start saving " + postsList.size() + " files");
        if (!ensureIsDirPresent()) {
            return false;
        }
        postsList.forEach(this::createAndSaveFile);
        if (savedFiles == postsList.size()) {
            log.debug(() -> "Saving all files successfully");
            return true;
        }
        log.warn("Not all files was saved");
        return false;
    }

    private boolean ensureIsDirPresent() {
        File file = new File(FILES_DICTIONARY);
        if (!file.isDirectory()) {
            try {
                return file.mkdirs();
            } catch (SecurityException e) {
                log.error("Can not create dir in: " + FILES_DICTIONARY);
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void createAndSaveFile(Post post) {
        if (post == null || post.getId() == null) {
            log.error("Provided null in post");
            return;
        }
        try {
            File filePath = new File(FILES_DICTIONARY + post.getId() + _JSON);
            if (!filePath.isFile()) {
                filePath.createNewFile();
            }
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(filePath, post);
            savedFiles++;

        } catch (IOException e) {
            log.error("IOException when saving post: " + post);
        } catch (Exception e) {
            log.error("Fail when saving post: " + post);
        }
    }
}
