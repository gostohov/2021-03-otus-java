package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;

public class EvenSecondProcessor implements Processor {

    private final TimeProvider timeProvider;

    public EvenSecondProcessor(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    @Override
    public Message process(Message message) {
        int second = timeProvider.getTime().getSecond();

        if (second % 2 == 0) {
            throw new IllegalStateException("Method run at even second: " + second);
        }

        return message;
    }
}
