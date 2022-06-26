package dev.rooftop.riddle.domain;

import lombok.Getter;

@Getter
public class BlocksCheck {

    private final String[] blocks;

    public BlocksCheck(String firstBlock, String secondBlock) {
        blocks = new String[]{firstBlock, secondBlock};
    }
}
