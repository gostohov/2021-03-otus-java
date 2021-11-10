package ru.otus.crm.service;

import ru.otus.crm.model.Client;
import ru.otus.crm.model.Clients;
import ru.otus.messagesystem.client.MessageCallback;

public interface FrontendService {
    void getClientById(MessageCallback<Client> dataConsumer);
    void getAllClients(MessageCallback<Clients> dataConsumer);
    void saveClient(Client client, MessageCallback<Clients> dataConsumer);

}
