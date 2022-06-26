package dev.rooftop.riddle.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import dev.rooftop.riddle.repositories.impl.FakeBlocksRepository;
import dev.rooftop.riddle.services.impl.BlocksSorterServiceImpl;
import org.junit.jupiter.api.Test;


public class BlocksSorterServiceImplTest {

  @Test
  public void givenFakeBlocksRepository_whenSortIsCall_thenSortedBlocks() throws Exception {
    FakeBlocksRepository repository = new FakeBlocksRepository();
    BlocksSorterServiceImpl blocksSorterService = new BlocksSorterServiceImpl(repository);
    String[] soredArray = blocksSorterService.sort();
    String[] originalArray = repository.getTestArray();
    assertArrayEquals(originalArray, soredArray);
  }
}
