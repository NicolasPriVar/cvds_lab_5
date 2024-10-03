package com.hazinlab.gestortareasbackend;

import static org.mockito.Mockito.*;

import com.hazinlab.gestortareasbackend.configuration.AddHeadersFilter;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;

class AddHeadersFilterTest {

  private final AddHeadersFilter addHeadersFilter = new AddHeadersFilter();

  @Test
  void doFilter_ShouldAddHeaders_WhenFilterIsCalled()
    throws IOException, ServletException {
    // Arrange
    HttpServletResponse response = mock(HttpServletResponse.class);
    ServletRequest request = mock(ServletRequest.class);
    FilterChain chain = mock(FilterChain.class);

    // Act
    addHeadersFilter.doFilter(request, response, chain);

    // Assert
    // Verify that the correct headers were set
    verify(response).setHeader("Access-Control-Allow-Origin", "*");
    verify(response)
      .setHeader(
        "Access-Control-Allow-Headers",
        "Access-Control-Allow-Origin, Access-Control-Allow-Methods, Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"
      );

    // Verify that the filter chain is continued
    verify(chain).doFilter(request, response);
  }
}
