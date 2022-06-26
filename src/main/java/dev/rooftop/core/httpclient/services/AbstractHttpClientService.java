package dev.rooftop.core.httpclient.services;

import dev.rooftop.core.httpclient.domain.HttpRequest;
import dev.rooftop.core.httpclient.exceptions.CustomHttpException;
import dev.rooftop.core.httpclient.exceptions.NotImplementedException;
import java.io.IOException;

public abstract class AbstractHttpClientService implements HttpClientService {

  @Override
  public <T> T get(HttpRequest httpRequest, Class<T> clazz)
      throws CustomHttpException, IOException {
    throw new NotImplementedException("need to implement get method");
  }

  @Override
  public <T> T post(HttpRequest httpRequest, Class<T> clazz)
      throws CustomHttpException, IOException {
    throw new NotImplementedException("need to implement post method");
  }

  @Override
  public <T> T put(HttpRequest httpRequest, Class<T> clazz)
      throws CustomHttpException, IOException {
    throw new NotImplementedException("need to implement put method");
  }

  @Override
  public void delete(HttpRequest httpRequest) throws CustomHttpException, IOException {
    throw new NotImplementedException("need to implement delete method");
  }
}
