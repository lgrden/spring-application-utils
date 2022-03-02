package io.wegetit.sau.core.errorhandler;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
        super("Entity not found.");
    }
}
