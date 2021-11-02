package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;

import java.time.LocalDateTime;

public class EvenSecondProcessor implements Processor {
    @Override
    public Message process(Message message) {
        LocalDateTime now = LocalDateTime.now();

        int second = now.getSecond();

        if (second % 2 == 0) {
            throw new IllegalStateException("Method run at even second: " + second);
        }

        return message;
    }
}
