package dev.rooftop.riddle.services.impl;

import dev.rooftop.core.httpclient.exceptions.CustomHttpException;
import dev.rooftop.riddle.domain.Blocks;
import dev.rooftop.riddle.repositories.BlocksRepository;
import dev.rooftop.riddle.services.BlocksSorterService;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BlocksSorterServiceImpl implements BlocksSorterService {

  private final BlocksRepository blocksRepository;

  public BlocksSorterServiceImpl(BlocksRepository blocksRepository) {
    this.blocksRepository = blocksRepository;
  }

  @Override
  public String[] sort() throws IOException, CustomHttpException {
    Blocks blocks = blocksRepository.getBlocks();
    Deque<String> blocksDeque = new ArrayDeque<>(List.of(blocks.getData()));
    List<String> sortedBlocks = new ArrayList<>();
    sortedBlocks.add(blocksDeque.pollFirst());
    while (blocksDeque.size() > 0) {
      String block = blocksDeque.pollFirst();
      boolean isNext = blocksRepository.check(sortedBlocks.get(sortedBlocks.size() - 1), block);
      if (isNext) {
        sortedBlocks.add(block);
      } else {
        blocksDeque.addLast(block);
      }
    }
    return sortedBlocks.toArray(String[]::new);
  }
}
