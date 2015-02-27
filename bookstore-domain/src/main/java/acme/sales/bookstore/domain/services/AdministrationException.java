package acme.sales.bookstore.domain.services;

/**
 * @author vmuravlev
 */
public class AdministrationException extends Exception {
    public AdministrationException(String message) {
        super(message);
    }
}