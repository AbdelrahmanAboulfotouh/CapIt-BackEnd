package org.example.capitbackend.services;

import java.util.Optional;
import java.util.UUID;
import org.example.capitbackend.model.SignupRequest;
import org.example.capitbackend.model.User;
import org.example.capitbackend.repositories.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    private final UsersRepository usersRepository;

    public SignupService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    public void signup(SignupRequest request) throws IllegalArgumentException
    {

        if (!request.getPassword().equals(request.getConfirmPassword()))
        {
            throw new IllegalArgumentException("Password and confirm password do not match");
        }
        User newUser = new User();
        newUser.setRole("customer");
        newUser.setId(UUID.randomUUID());

        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getFirstName());

        // email uniqueness check
        if(usersRepository.findUserByEmail(request.getEmail()).isPresent())
            throw  new IllegalArgumentException("Email already exists");
        else
            newUser.setEmail(request.getEmail());

        // phone uniqueness check
        if(usersRepository.findUserByPhone(request.getPhoneNumber()).isPresent())
            throw  new IllegalArgumentException("Phone Number  already exists");
        else
            newUser.setPhone(request.getPhoneNumber());
        // hash password is required

        // save user
        this.saveAccount(newUser);
    }
    public void saveAccount(User newUser)
    {

        usersRepository.save(newUser);
    }

}
