package acme.sales.bookstore.domain.services;

import acme.sales.bookstore.domain.entities.Client;

/**
 * @author vmuravlev
 */
public interface SecurityService {
    Client getCurrentUserClient();
}
