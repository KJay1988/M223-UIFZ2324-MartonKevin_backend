package backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Benutzerdefinierte Ausnahme, die ausgelöst wird, wenn eine angeforderte Ressource nicht gefunden wird.
 * Diese Ausnahme wird mit einem HTTP-Statuscode 404 (Not Found) zurückgegeben.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Konstruktor für die {@link ResourceNotFoundException}.
     * 
     * @param message Die Fehlermeldung, die mit dieser Ausnahme übergeben wird
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
