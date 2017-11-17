package aj.demo.githubexplorer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ParserTest {

    @Test
    public void parseSubsequentPageUrl_nullInput() {
        String inputUrl = null;
        String result = Parser.parseSubsequentPageUrl(inputUrl);
        assertNull(result);
    }

    @Test
    public void parseSubsequentPageUrl_emptyInput() {
        String inputUrl = "";
        String result = Parser.parseSubsequentPageUrl(inputUrl);
        assertNull(result);
    }

    @Test
    public void parseSubsequentPageUrl_validInput() {
        String inputUrl = "<https://api.github.com/search/repositories?q=language%3AJava&page=2>; rel=\"next\", <https://api.github.com/search/repositories?q=language%3AJava&page=34>; rel=\"last\"";
        String result = Parser.parseSubsequentPageUrl(inputUrl);
        assertEquals("https://api.github.com/search/repositories?q=language%3AJava&page=2", result);
    }

    @Test
    public void extractDate_nullInput() {
        String inputDate = null;
        String result = Parser.parseIssuesUrl(inputDate);
        assertNull(result);
    }

    @Test
    public void extractDate_emptyInput() {
        String inputDate = "";
        String result = Parser.parseIssuesUrl(inputDate);
        assertNull(result);
    }

    @Test
    public void extractDate_validInput() throws Exception {
        String inputDate = "2017-11-15T07:34:27Z";
        String result = Parser.extractDate(inputDate);
        assertEquals("2017-11-15 07:34:27", result);
    }

    @Test
    public void parseIssuesUrl_nullInput() {
        String inputUrl = null;
        String result = Parser.parseIssuesUrl(inputUrl);
        assertNull(result);
    }

    @Test
    public void parseIssuesUrl_emptyInput() {
        String inputUrl = "";
        String result = Parser.parseIssuesUrl(inputUrl);
        assertNull(result);
    }

    @Test
    public void parseIssuesUrl_validInput() throws Exception {
        String inputUrl = "https://api.github.com/repos/ReactiveX/RxJava/issues{/number}";
        String result = Parser.parseIssuesUrl(inputUrl);
        assertEquals("https://api.github.com/repos/ReactiveX/RxJava/issues", result);
    }

}