package dev.rooftop.core.httpclient.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.rooftop.core.httpclient.domain.HttpRequest;
import dev.rooftop.core.httpclient.exceptions.NotImplementedException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AbstractHttpClientServiceTest {

    @Test
    public void givenAbstractHttpClientService_whenGetOrPostMethodsAreCalled_thenThrowNotImplementedException() {
        AbstractHttpClientService abstractHttpClientService = Mockito.mock(
            AbstractHttpClientService.class,
            Mockito.CALLS_REAL_METHODS);
        assertThrows(NotImplementedException.class,
            () -> abstractHttpClientService.get(new HttpRequest(), Object.class),
            "need to implement get method");
        assertThrows(NotImplementedException.class,
            () -> abstractHttpClientService.post(new HttpRequest(), Object.class),
            "need to implement post method");
        assertThrows(NotImplementedException.class,
            () -> abstractHttpClientService.put(new HttpRequest(), Object.class),
            "need to implement put method");
        assertThrows(NotImplementedException.class,
            () -> abstractHttpClientService.delete(new HttpRequest()),
            "need to implement delete method");
    }
}
