package acme.sales.bookstore.domain.services;

import acme.sales.bookstore.domain.entities.Client;

/**
 * @author vmuravlev
 */
public interface AdministrationService {
    void createNewClient(Client client) throws AdministrationException;
    boolean hasDuplicate(Client client);
}
