package dev.rooftop.core.httpclient.interceptors;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

public class AuthInterceptorTest {

  @Test
  public void givenNewInstance_whenGetToken_thenReturnNull() {
    AuthInterceptor authInterceptor = new AuthInterceptor();
    assertNull(authInterceptor.getToken());
  }

  @Test
  public void givenTokenRequest_whenFinish_thenReturnNonEmptyString() throws IOException {
    AuthInterceptor authInterceptor = new AuthInterceptor();
    assertNotEquals(StringUtils.EMPTY, authInterceptor.requestToken());
  }
}
