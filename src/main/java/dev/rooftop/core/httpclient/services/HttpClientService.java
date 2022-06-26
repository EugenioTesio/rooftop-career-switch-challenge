package dev.rooftop.core.httpclient.services;

import dev.rooftop.core.httpclient.domain.HttpRequest;
import dev.rooftop.core.httpclient.exceptions.CustomHttpException;
import java.io.IOException;

public interface HttpClientService {

    <T> T get(HttpRequest httpRequest, Class<T> clazz) throws CustomHttpException, IOException;

    <T> T post(HttpRequest httpRequest, Class<T> clazz) throws CustomHttpException, IOException;

    <T> T put(HttpRequest httpRequest, Class<T> clazz) throws CustomHttpException, IOException;

    void delete(HttpRequest httpRequest) throws CustomHttpException, IOException;
}
