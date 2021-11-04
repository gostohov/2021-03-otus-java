package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;

import java.util.*;

public class HistoryListener implements Listener, HistoryReader {

    private final Map<Long, Message> history = new HashMap<>();

    @Override
    public void onUpdated(Message msg) {
        var copy = msg.clone();
        history.put(msg.getId(), copy);
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return Optional.of(history.get(id));
    }

    public void printHistory() {
        history.forEach((k, v) -> {
            System.out.println(v);
        });
    }

}
