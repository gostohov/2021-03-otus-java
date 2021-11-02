package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;

import java.util.*;

public class HistoryListener implements Listener, HistoryReader {

    private final List<List<Message>> history;
    private final Set<Message> storage;

    public HistoryListener() {
        history = new ArrayList<>();
        storage = new HashSet<>();
    }

    @Override
    public void onUpdated(Message msg) {
        if (msg != null) {
            List<Message> changes = new ArrayList<>();
            Optional<Message> oldMsg = findMessageById(msg.getId());
            oldMsg.ifPresentOrElse(
                oldMessage -> {
                    storage.remove(msg);
                    changes.add((Message) oldMessage.clone());
                    changes.add((Message) msg.clone());
                    history.add(changes);
                },
                () -> {

                }
            );

            storage.add((Message) msg.clone());
        }
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return storage.stream()
                      .filter(message -> message.getId() == id)
                      .findFirst();
    }

    public void printHistory() {
        history.forEach(changes -> {
            var logString = String.format("oldMsg:%s,\nnewMsg:%s\n", changes.get(0), changes.get(1));
            System.out.println(logString);
        });
    }
}
