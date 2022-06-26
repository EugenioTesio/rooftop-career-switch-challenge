package dev.rooftop.riddle.repositories.impl;

import dev.rooftop.core.httpclient.domain.HttpRequest;
import dev.rooftop.core.httpclient.exceptions.CustomHttpException;
import dev.rooftop.core.httpclient.services.HttpClientService;
import dev.rooftop.core.httpclient.services.impl.HttpClientServiceImpl;
import dev.rooftop.riddle.domain.Blocks;
import dev.rooftop.riddle.domain.BlocksCheck;
import dev.rooftop.riddle.domain.CheckResponse;
import dev.rooftop.riddle.repositories.BlocksRepository;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public class HttpBlocksRepository implements BlocksRepository {

  Logger logger = Logger.getLogger(getClass().getName());

  @Override
  public Blocks getBlocks() throws IOException, CustomHttpException {
    logger.info("Getting Blocks");
    HttpClientService service = HttpClientServiceImpl.getInstance();
    HttpRequest httpRequest = HttpRequest.builder()
        .path("blocks")
        .build();
    Blocks blocks = service.get(httpRequest, Blocks.class);
    logger.info(String.format("Fetched Blocks %s", Arrays.toString(blocks.getData())));
    return blocks;
  }

  @Override
  public boolean check(String block1, String block2) throws IOException, CustomHttpException {
    logger.info(String.format("Check if %s is followed by %s", block1, block2));
    HttpClientService service = HttpClientServiceImpl.getInstance();
    HttpRequest httpRequest = HttpRequest.builder()
        .path("check")
        .body(new BlocksCheck(block1, block2))
        .build();
    CheckResponse checkResponse = service.post(httpRequest, CheckResponse.class);
    logger.info(String.format("Result %b", checkResponse.getMessage()));
    return checkResponse.getMessage();
  }
}
