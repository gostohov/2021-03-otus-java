package ru.gostohov.listener;

import ru.gostohov.Message;

public interface Listener {

    void onUpdated(Message oldMsg, Message newMsg);

}
