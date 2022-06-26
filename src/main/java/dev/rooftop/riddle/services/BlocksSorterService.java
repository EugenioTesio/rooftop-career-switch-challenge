package dev.rooftop.riddle.services;

import dev.rooftop.core.httpclient.exceptions.CustomHttpException;
import java.io.IOException;

public interface BlocksSorterService {

  String[] sort() throws IOException, CustomHttpException;
}
