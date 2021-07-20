package ru.gostohov.handler;

import ru.gostohov.Message;
import ru.gostohov.listener.Listener;

public interface Handler {
    Message handle(Message msg);

    void addListener(Listener listener);
    void removeListener(Listener listener);
}
