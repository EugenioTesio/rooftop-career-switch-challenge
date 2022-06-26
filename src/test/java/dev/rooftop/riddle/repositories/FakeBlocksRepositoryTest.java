package dev.rooftop.riddle.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.rooftop.riddle.repositories.impl.FakeBlocksRepository;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class FakeBlocksRepositoryTest {

  @Test
  public void givenFakeBlocksRepository_whenGetBlocks_thenReturnUnsortedBlocks() {
    FakeBlocksRepository fakeBlocksRepository = new FakeBlocksRepository();
    String[] unsortedData = fakeBlocksRepository.getBlocks().getData();
    String[] originalArray = fakeBlocksRepository.getTestArray();
    assertEquals(originalArray[0], unsortedData[0]);
    assertFalse(Arrays.equals(originalArray, unsortedData));
  }

  @Test
  public void givenContiguousValues_whenCheck_thenReturnTrue() {
    FakeBlocksRepository fakeBlocksRepository = new FakeBlocksRepository();
    String[] originalArray = fakeBlocksRepository.getTestArray();
    String block1 = originalArray[0];
    String block2 = originalArray[1];
    assertTrue(fakeBlocksRepository.check(block1, block2));
  }

  @Test
  public void givenNotContiguousValues_whenCheck_thenReturnFalse() {
    FakeBlocksRepository fakeBlocksRepository = new FakeBlocksRepository();
    String[] originalArray = fakeBlocksRepository.getTestArray();
    String block1 = originalArray[0];
    String block2 = originalArray[2];
    assertFalse(fakeBlocksRepository.check(block1, block2));
  }

  @Test
  public void givenValuesOutOfTheArray_whenCheck_thenReturnFalse() {
    FakeBlocksRepository fakeBlocksRepository = new FakeBlocksRepository();
    String[] originalArray = fakeBlocksRepository.getTestArray();
    String block1 = originalArray[0];
    String block2 = "anyValue";
    assertFalse(fakeBlocksRepository.check(block1, block2));
  }
}
