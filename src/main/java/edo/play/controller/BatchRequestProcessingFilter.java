package edo.play.controller;

import edo.play.controller.batch.BatchRequestProcessorUtils;
import edo.play.controller.batch.MockHttpServletRequest;
import edo.play.controller.batch.MockHttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(1)
public class BatchRequestProcessingFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        MockHttpServletRequest[] mockRequests = BatchRequestProcessorUtils.parseRequest(request);
        MockHttpServletResponse[] mockResponses = new MockHttpServletResponse[mockRequests.length];
        for(int i=0 ; i <= mockRequests.length ; i++  ) {
            chain.doFilter(mockRequests[i], mockResponses[i]);
        }
        BatchRequestProcessorUtils.populateResponseFromMockResponses(res, mockResponses);
    }

}
