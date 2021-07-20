package ru.gostohov.processor;

import ru.gostohov.Message;

public interface Processor {

    Message process(Message message);

}
