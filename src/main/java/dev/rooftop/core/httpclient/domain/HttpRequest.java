package dev.rooftop.core.httpclient.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HttpRequest {

  private String path;
  private List<HttpQueryParam> httpQueryParam;
  private List<HttpHeader> headers;
  private Object body;
}
