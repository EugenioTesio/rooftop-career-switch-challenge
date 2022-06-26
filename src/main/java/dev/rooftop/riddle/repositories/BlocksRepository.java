package dev.rooftop.riddle.repositories;

import dev.rooftop.core.httpclient.exceptions.CustomHttpException;
import dev.rooftop.riddle.domain.Blocks;
import java.io.IOException;

public interface BlocksRepository {

    Blocks getBlocks() throws IOException, CustomHttpException;

    boolean check(String block1, String block2) throws IOException, CustomHttpException;
}
