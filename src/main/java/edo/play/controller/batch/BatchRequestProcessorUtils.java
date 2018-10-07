package edo.play.controller.batch;

import org.springframework.http.codec.HttpMessageDecoder;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

public class BatchRequestProcessorUtils {
    private static final Pattern BOUNDARY_REGEX = Pattern.compile(".*boundary=(.*)");

    public static MockHttpServletRequest[] parseRequest(HttpServletRequest request) throws IOException {
        String req = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String contentType = request.getHeader(CONTENT_TYPE);
        Matcher matcher = BOUNDARY_REGEX.matcher(contentType);
        String delimiter = "batch";
        if(matcher.matches()) {
            delimiter = matcher.group(1);
        }
        System.err.println("request/contentType/delimiter: " + req + "/" + contentType + "/" +  delimiter);
        String[] reqs = req.split(delimiter);
        MockHttpServletRequest[] mockHttpServletRequests = new MockHttpServletRequest[reqs.length];
        for (int i = 0; i < reqs.length; i++) {
            mockHttpServletRequests[i] = extractRequestMessage(reqs[i]);
        }
        return mockHttpServletRequests;
    }

    private static MockHttpServletRequest extractRequestMessage(String req) {

    }

    public static void populateResponseFromMockResponses(ServletResponse res, MockHttpServletResponse[] mockResponses) {
        System.err.println("res: " + res);
        mockResponses = new MockHttpServletResponse[1];
    }
}
