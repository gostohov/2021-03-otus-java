package ru.otus.services;

import org.eclipse.jetty.security.AbstractLoginService;
import org.eclipse.jetty.security.RolePrincipal;
import org.eclipse.jetty.security.UserPrincipal;
import org.eclipse.jetty.util.security.Password;
import ru.otus.crm.model.Client;
import ru.otus.crm.service.DBServiceClient;

import java.util.List;
import java.util.Optional;

public class HibernateLoginServiceImpl extends AbstractLoginService  {

    private final DBServiceClient dbServiceClient;

    public HibernateLoginServiceImpl(String name, DBServiceClient dbServiceClient) {
        setName(name);
        this.dbServiceClient = dbServiceClient;
    }

    @Override
    protected List<RolePrincipal> loadRoleInfo(UserPrincipal user) {
        return List.of(new RolePrincipal("client"));
    }


    @Override
    protected UserPrincipal loadUserInfo(String username) {
        System.out.println(String.format("HibernateLoginServiceImpl#loadClientInfo(%s)", username));
        Optional<Client> client = dbServiceClient.getClientByUsername(username);
        return client.map(c -> new UserPrincipal(c.getUsername(), new Password(c.getPassword()))).orElse(null);
    }
}
