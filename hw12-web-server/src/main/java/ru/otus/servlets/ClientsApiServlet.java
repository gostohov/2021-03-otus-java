package ru.otus.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.otus.crm.model.Client;
import ru.otus.crm.service.DBServiceClient;
import ru.otus.crm.service.DbServiceException;

import java.io.IOException;
import java.util.List;

public class ClientsApiServlet extends HttpServlet {
    public static final String PARAM_NAME = "name";
    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_IS_ADMIN = "isAdmin";

    private static final int ID_PATH_PARAM_POSITION = 1;

    private final DBServiceClient dbServiceClient;
    private final Gson gson;

    public ClientsApiServlet(DBServiceClient dbServiceClient, Gson gson) {
        this.dbServiceClient = dbServiceClient;
        this.gson = gson;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = extractIdFromRequest(request);
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        if (id > 0 ) {
            Client client = dbServiceClient.getClient(id).orElse(null);
            out.print(gson.toJson(client));
        }
        else if (id == -1 ) {
            List<Client> client = dbServiceClient.findAll();
            out.print(gson.toJson(client));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        JsonMessage msg;
        try{
            Client client = gson.fromJson(request.getReader(), Client.class);
            dbServiceClient.saveClient(client);
            msg = new JsonMessage("success", "id: " + client.getId());
        }
        catch (JsonParseException e){
            response.setStatus(200);
            msg = new JsonMessage("Error", "Ошибка парсинга данных пользователя.");
        } catch (DbServiceException e) {
            response.setStatus(200);
            msg = new JsonMessage("Error", "Пользователь не создан - " + e.getMessage());
        }
        out.print(gson.toJson(msg));
    }

    private long extractIdFromRequest(HttpServletRequest request) {
        String[] path = request.getPathInfo().split("/");
        String id = (path.length > 1)? path[ID_PATH_PARAM_POSITION]: String.valueOf(- 1);
        return Long.parseLong(id);
    }

    private class JsonMessage {
        private final String status;
        private final String message;

        public JsonMessage(String status, String message) {
            this.status = status;
            this.message = message;
        }
    }
}
