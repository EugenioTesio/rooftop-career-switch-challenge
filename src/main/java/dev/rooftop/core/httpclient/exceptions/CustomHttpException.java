package dev.rooftop.core.httpclient.exceptions;

public class CustomHttpException extends Exception {

  public CustomHttpException(String message, Integer statusCode) {
    super(String.format("CustomHttpException(statusCode: %d, message: %s)", statusCode, message));
  }
}
