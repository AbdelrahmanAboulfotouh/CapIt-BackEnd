package org.example.capitbackend.services;

import java.util.Optional;
import org.example.capitbackend.model.User;
import org.example.capitbackend.repositories.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    private final UsersRepository usersRepository;

    public SignupService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    public void saveAccount(User newUser)
    {
        usersRepository.save(newUser);
    }

}
