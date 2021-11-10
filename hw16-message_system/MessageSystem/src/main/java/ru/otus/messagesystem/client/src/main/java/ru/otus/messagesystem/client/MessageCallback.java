package ru.otus.messagesystem.client.src.main.java.ru.otus.messagesystem.client;

import java.util.function.Consumer;

public interface MessageCallback<T extends ResultDataType> extends Consumer<T> {
}
