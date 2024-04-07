package jaworski.artur;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static jaworski.artur.JSONSaver.FILES_DICTIONARY;
import static jaworski.artur.JSONSaver._JSON;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JSONSaverTest {

    private JSONSaver cut;

    @BeforeEach
    public void setup() {
        cut = new JSONSaver();

    }

    @Test
    public void testSaveFiles() throws IOException {
        //given
        Post post = new Post(0L, 1L, "Title", "Test");
        String jsonToVerification =
                """
                        {
                        "id" : 0,
                        "userId" : 1,
                        "title" : "Title",
                        "body" : "Test"
                        }
                        """;
        //when
        cut.saveToFile(List.of(post));
        //then
        File file = new File(FILES_DICTIONARY + post.getId() + _JSON);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        String wholeFile = reader.lines().reduce("", (acc, line) -> acc + line.trim() + '\n');
        Assertions.assertThat(wholeFile).isEqualTo(jsonToVerification);
        reader.close();
        assertTrue(file.delete());
    }

    @Test
    public void testSaveFilesWithNullId() {
        //given
        Post post = new Post(null, 1L, "Title", "Test");
        //when
        cut.saveToFile(List.of(post));
        //then
        File file = new File(FILES_DICTIONARY + post.getId() + _JSON);
        assertFalse(file.canRead());
    }

}