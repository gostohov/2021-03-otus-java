package ru.otus.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import ru.otus.crm.model.Client;
import ru.otus.crm.service.FrontendService;

public class ClientWSController {
    private static final Logger logger = LoggerFactory.getLogger(ClientWSController.class);

    private final FrontendService frontendService;
    private final SimpMessagingTemplate template;

    public ClientWSController(FrontendService frontendService, SimpMessagingTemplate template) {
        this.frontendService = frontendService;
        this.template = template;
    }

    @MessageMapping("/client/list")
    public void getClients() {
        frontendService.getAllClients(clients -> template.convertAndSend("/topic/client/list", clients));
    }

    @MessageMapping("/client/new")
    public void addClient(Client client) {
        frontendService.saveClient(client, clients -> template.convertAndSend("/topic/client/list", clients));
    }
}
