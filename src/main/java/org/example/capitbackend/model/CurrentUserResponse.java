package org.example.capitbackend.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public class CurrentUserResponse {

        private UUID userId;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String role;
        private OffsetDateTime createdAt;
        private OffsetDateTime lastActiveAt;

        public CurrentUserResponse() {}

        public CurrentUserResponse(UUID userId, String firstName, String lastName, String email,
                                   String phone, String role, OffsetDateTime createdAt,
                                   OffsetDateTime lastActiveAt) {
            this.userId = userId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phone = phone;
            this.role = role;
            this.createdAt = createdAt;
            this.lastActiveAt = lastActiveAt;
        }

        public UUID getUserId() {
            return userId;
        }

        public void setUserId(UUID userId) {
            this.userId = userId;
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

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public OffsetDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(OffsetDateTime createdAt) {
            this.createdAt = createdAt;
        }

        public OffsetDateTime getLastActiveAt() {
            return lastActiveAt;
        }

        public void setLastActiveAt(OffsetDateTime lastActiveAt) {
            this.lastActiveAt = lastActiveAt;
        }


}
