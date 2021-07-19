package ru.gostohov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gostohov.gc.GcObserver;
import ru.gostohov.gc.Observer;
import ru.gostohov.gc.OutOfMemory;

public class GcTester {

    private static final Logger logger = LoggerFactory.getLogger(GcTester.class);

    public static void main(String[] args) {
        final Observer observer = new GcObserver();
        try {
            observer.observe(new OutOfMemory(100_000));
        } finally {
            logger.info("{}", observer.getResults());
        }
    }
}
