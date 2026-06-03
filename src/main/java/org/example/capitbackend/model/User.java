            package org.example.capitbackend.model;

            import java.time.OffsetDateTime;
            import java.util.UUID;
            import org.springframework.data.annotation.Id;
            import org.springframework.data.annotation.ReadOnlyProperty;
            import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public class User {

    @Id
    private UUID id;

    private String firstName;

    private   String lastName;

    private String email;

    private String phone;

    private String passwordHash;

    private String role;
    @ReadOnlyProperty
    private OffsetDateTime createdAt;

    private OffsetDateTime lastActiveAt;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }



    public OffsetDateTime getLastActiveAt() {
        return lastActiveAt;
    }

    public void setLastActiveAt(OffsetDateTime lastActiveAt) {
        this.lastActiveAt = lastActiveAt;
    }
}