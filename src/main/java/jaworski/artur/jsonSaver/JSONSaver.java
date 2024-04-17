package jaworski.artur.jsonSaver;

import com.fasterxml.jackson.databind.ObjectMapper;
import jaworski.artur.to.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Class for create files and save date to them.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class JSONSaver implements IJSONSaver {
    @Value("${files.directory}")
    private String filesDirectory;
    private static final String _JSON = ".json";
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
        File file = new File(filesDirectory);
        if (!file.isDirectory()) {
            try {
                return file.mkdirs();
            } catch (SecurityException e) {
                log.error("Can not create dir in: " + filesDirectory);
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
            File filePath = new File(filesDirectory + post.getId() + _JSON);
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

    protected void setFilesDirectory(String filesDirectory) {
        this.filesDirectory = filesDirectory;
    }
}
