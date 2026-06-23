package org.example.capitbackend.exception;

public class InvalidCredentialsException extends RuntimeException
{
    /*  Thrown when login credentials are missing, unknown, or do not match.
        The message is intentionally generic so the API response never reveals
        whether the email exists or which part of the credential pair was wrong.
    */
        public InvalidCredentialsException(String message) {
            super(message);
        }
}
