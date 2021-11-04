package ru.otus.crm.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.repository.DataTemplate;
import ru.otus.crm.model.Client;
import ru.otus.core.sessionmanager.TransactionManager;

import java.util.List;
import java.util.Optional;

public class DbServiceClientImpl implements DBServiceClient {
    private static final Logger log = LoggerFactory.getLogger(DbServiceClientImpl.class);

    private final DataTemplate<Client> clientDataTemplate;
    private final TransactionManager transactionManager;
    private final SessionFactory sessionFactory;

    public DbServiceClientImpl(TransactionManager transactionManager, DataTemplate<Client> clientDataTemplate, SessionFactory sessionFactory) {
        this.transactionManager = transactionManager;
        this.clientDataTemplate = clientDataTemplate;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Client saveClient(Client client) {
        return transactionManager.doInTransaction(session -> {
            if (client.getId() == null) {
                clientDataTemplate.insert(session, client);
                log.info("created client: {}", client);

                return client;
            }
            clientDataTemplate.update(session, client);
            log.info("updated client: {}", client);
            return client;
        });
    }

    @Override
    public Optional<Client> getClient(long id) {
        try (var session = sessionFactory.openSession()) {
            try {
                return Optional.ofNullable(session.find(Client.class, id));
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Client> findAll() {
        return transactionManager.doInTransaction(session -> {
            var clientList = clientDataTemplate.findAll(session);
            log.info("clientList:{}", clientList);
            return clientList;
       });
    }
}
