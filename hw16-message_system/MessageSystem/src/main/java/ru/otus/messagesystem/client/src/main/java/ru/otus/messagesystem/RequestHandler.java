package ru.otus.messagesystem.client.src.main.java.ru.otus.messagesystem;


import ru.otus.messagesystem.client.src.main.java.ru.otus.messagesystem.client.ResultDataType;
import ru.otus.messagesystem.client.src.main.java.ru.otus.messagesystem.message.Message;

import java.util.Optional;


public interface RequestHandler<T extends ResultDataType> {
    Optional<Message> handle(Message msg);
}
