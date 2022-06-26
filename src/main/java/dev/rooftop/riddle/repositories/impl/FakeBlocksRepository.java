package dev.rooftop.riddle.repositories.impl;

import dev.rooftop.riddle.domain.Blocks;
import dev.rooftop.riddle.repositories.BlocksRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FakeBlocksRepository implements BlocksRepository {

  List<String> data = List.of("qwer", "asdf", "zcvf", "erty", "jhgf", "polk", "gthu", "uhgt");

  @Override
  public Blocks getBlocks() {
    List<String> unsortedList = new ArrayList<>(data);
    unsortedList.remove(0);
    Collections.shuffle(unsortedList);
    unsortedList.add(0, data.get(0));

    return Blocks.builder()
        .chunkSize(4)
        .length(32)
        .data(unsortedList.toArray(String[]::new))
        .build();
  }

  @Override
  public boolean check(String block1, String block2) {
    Integer indexBlock1 = data.indexOf(block1);
    Integer indexBlock2 = data.indexOf(block2);
    if (indexBlock1 == -1 || indexBlock2 == -1) {
      return false;
    }
    return indexBlock2 - indexBlock1 == 1;
  }

  public String[] getTestArray() {
    return data.toArray(String[]::new);
  }
}
