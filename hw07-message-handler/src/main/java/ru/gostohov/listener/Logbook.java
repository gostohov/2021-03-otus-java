package ru.gostohov.listener;

import ru.gostohov.Message;

import java.util.List;

public interface Logbook extends Listener {

    void addRecord(Message oldMassage, Message newMessage);

    List<Record> getRecords();
}
