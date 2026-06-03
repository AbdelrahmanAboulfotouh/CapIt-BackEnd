package org.example.capitbackend.repositories;

import java.util.Optional;
import java.util.UUID;
import org.example.capitbackend.model.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, UUID> {
    @Query("select * from users where email = :email")
    Optional<User> findUserByEmail(String email);

    @Query("select * from users where phone = :phone")
    Optional<User> findUserByPhone(String phone);

    @Modifying
    @Query(" UPDATE users SET last_active_at = now() WHERE id = :userId ")
    void updateLastActive(UUID userId);


}
