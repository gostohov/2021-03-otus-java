package ru.otus.crm.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.crm.model.Client;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Optional<Client> findByUsername(@Param("username") String name);

    @Query("select * from client where upper(username) = upper(:username)")
    Optional<Client> findByUsernameIgnoreCase(@Param("username") String username);

    @Modifying
    @Query("update client set name = :newName where id = :id")
    void updateName(@Param("id") long id, @Param("newName") String newName);
}
