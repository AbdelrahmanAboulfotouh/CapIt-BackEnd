package org.example.capitbackend.services;

import java.util.UUID;
import org.example.capitbackend.model.SignupRequest;
import org.example.capitbackend.model.User;
import org.example.capitbackend.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;


    public AuthService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public String signup(SignupRequest request) throws IllegalArgumentException
    {

        if (!request.getPassword().equals(request.getConfirmPassword()))
        {
            throw new IllegalArgumentException("Password and confirm password do not match");
        }
        User newUser = new User();
        newUser.setRole("customer");
        newUser.setId(UUID.randomUUID());

        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());

        // email uniqueness check
        if(usersRepository.findUserByEmail(request.getEmail()).isPresent())
            throw  new IllegalArgumentException("Email already exists");
        newUser.setEmail(request.getEmail());

        // phone uniqueness check
        if(usersRepository.findUserByPhone(request.getPhoneNumber()).isPresent())
            throw  new IllegalArgumentException("Phone Number  already exists");
        newUser.setPhone(request.getPhoneNumber());

        // hash password is required
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        newUser.setPasswordHash(hashedPassword);

        // save user
        this.saveAccount(newUser);

        return "User created successfully";

    }
    private void saveAccount(User newUser)
    {
        usersRepository.updateLastActive(newUser.getId());
        usersRepository.save(newUser);
    }

}
