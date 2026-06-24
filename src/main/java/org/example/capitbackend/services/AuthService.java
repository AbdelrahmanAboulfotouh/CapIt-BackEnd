package org.example.capitbackend.services;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.example.capitbackend.exception.InvalidCredentialsException;
import org.example.capitbackend.model.LoginRequest;
import org.example.capitbackend.model.SignupRequest;
import org.example.capitbackend.model.SignupResponse;
import org.example.capitbackend.model.User;
import org.example.capitbackend.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private static final String INVALID_CREDENTIALS_MESSAGE = "Invalid email or password";

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UsersRepository usersRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    @Transactional
    public SignupResponse signup(SignupRequest request) throws IllegalArgumentException
    {

        if (!request.getPassword().equals(request.getConfirmPassword()))
        {
            throw new IllegalArgumentException("Password and confirm password do not match");
        }
        User newUser = new User();
        newUser.setRole("USER");
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

        // issue a JWT for the newly created account
        String token = jwtService.generateToken(newUser.getId().toString());

        return new SignupResponse(newUser.getId(),newUser.getEmail(), token);

    }
    /* Authenticates a user by email + password and issues a JWT on success.
       Failures (unknown email or wrong password) are both reported back to the
       caller as the same generic message/status so the API never reveals
       which part of the credential pair was incorrect.
    */
    public SignupResponse login(LoginRequest request) {

        User user = usersRepository.findUserByEmail(request.getEmail()).orElse(null);

        if (user == null) {
            log.warn("Login failed - no account found for email {}", request.getEmail());
            throw new InvalidCredentialsException(INVALID_CREDENTIALS_MESSAGE);
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            log.warn("Login failed - invalid password for user {}", user.getId());
            throw new InvalidCredentialsException(INVALID_CREDENTIALS_MESSAGE);
        }

        // credentials are valid - issue a JWT using the same mechanism as signup
        String token = jwtService.generateToken(user.getId().toString());
        log.info("Login successful for user {}", user.getId());

        return new SignupResponse(user.getId(), user.getEmail(), token);
    }
    /* Logs out the currently authenticated user.
          JWTs issued by this service are stateless and are never persisted or tracked
          server-side, so there is no server-side token to revoke. This method only
          confirms that the caller is currently authenticated (i.e. presented a valid,
          unexpired token) and acknowledges the request; the client is responsible for
          discarding its token afterward.
       */
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            log.warn("Logout failed - no authenticated user in request");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication required");
        }

        log.info("Logout successful for user {}", authentication.getPrincipal());
    }
    @Transactional
    protected void saveAccount(User newUser)
    {
        newUser.setLastActiveAt(OffsetDateTime.now());
        usersRepository.save(newUser);
    }

}
