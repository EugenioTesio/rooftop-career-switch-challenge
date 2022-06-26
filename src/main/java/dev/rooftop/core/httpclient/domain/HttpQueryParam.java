package dev.rooftop.core.httpclient.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class HttpQueryParam {

  private String name;
  private String value;
}
