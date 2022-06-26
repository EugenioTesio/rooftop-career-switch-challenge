package dev.rooftop.core.httpclient.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.rooftop.core.config.AppConfig;
import dev.rooftop.core.httpclient.domain.HttpRequest;
import dev.rooftop.core.httpclient.exceptions.CustomHttpException;
import dev.rooftop.core.httpclient.interceptors.AuthInterceptor;
import dev.rooftop.core.httpclient.services.impl.HttpClientServiceImpl;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class HttpClientServiceImplTest {

    @Test
    public void givenNewDefaultInstance_whenGetOkHttpInterceptor_thenReturnOneInterceptorTypeOkHttpAuthInterceptor() {
        HttpClientServiceImpl service = HttpClientServiceImpl.getInstance();
        assertEquals(1, service.getOkHttpClient().interceptors().size());
        assertInstanceOf(AuthInterceptor.class, service.getOkHttpClient().interceptors().get(0));
    }

    @Test
    public void givenNewDefaultInstance_whenGetUrl_thenReturnAppConfigUrl() {
        HttpClientServiceImpl service = HttpClientServiceImpl.getInstance();
        AppConfig appConfig = AppConfig.getInstance();
        assertEquals(appConfig.getUrl(), service.getUrl());
    }

    @Test
    public void givenNewInstanceWithWrongUrl_whenGetOrPostInvoked_thenThrowIOException() {
        HttpClientService service = HttpClientServiceImpl.getInstance("http://wrong-url.com");
        assertThrows(CustomHttpException.class, () -> service.get(new HttpRequest(), String.class));
        assertThrows(CustomHttpException.class,
            () -> service.post(new HttpRequest(), String.class));
    }

    @Test
    public void givenNewDefaultInstance_whenGetBlocks_thenReturnValidResponse()
        throws IOException, CustomHttpException {
        HttpClientServiceImpl service = HttpClientServiceImpl.getInstance();
        Object response = service.get(HttpRequest.builder()
                .path("blocks")
                .build(),
            Object.class);
        assertNotNull(response);
    }

    @Test
    public void givenNewDefaultInstance_whenGetCheck_thenReturnCustomHttpException() {
        HttpClientServiceImpl service = HttpClientServiceImpl.getInstance();
        assertThrows(CustomHttpException.class, () -> service.get(HttpRequest.builder()
                .path("check")
                .build(),
            Object.class));
    }
}
