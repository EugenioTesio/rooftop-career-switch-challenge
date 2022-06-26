package dev.rooftop;

import dev.rooftop.core.httpclient.exceptions.CustomHttpException;
import dev.rooftop.riddle.repositories.BlocksRepository;
import dev.rooftop.riddle.repositories.impl.HttpBlocksRepository;
import dev.rooftop.riddle.services.BlocksSorterService;
import dev.rooftop.riddle.services.impl.BlocksSorterServiceImpl;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public class Application {

    private static final Logger logger = Logger.getLogger("Application");

    public static void main(String[] args) throws IOException, CustomHttpException {
        BlocksRepository repository = new HttpBlocksRepository();
        BlocksSorterService blocksSorterService = new BlocksSorterServiceImpl(repository);
        String[] soredArray = blocksSorterService.sort();
        logger.info(String.format("Sorted array: %s", Arrays.toString(soredArray)));
    }
}
