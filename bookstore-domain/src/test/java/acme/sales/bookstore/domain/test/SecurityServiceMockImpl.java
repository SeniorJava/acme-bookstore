package acme.sales.bookstore.domain.test;

import acme.sales.bookstore.domain.entities.Client;
import acme.sales.bookstore.domain.repositories.ClientRepository;
import acme.sales.bookstore.domain.services.SecurityService;

import javax.inject.Inject;

/**
 * @author vmuravlev
 */
public class SecurityServiceMockImpl implements SecurityService {

    @Inject
    private ClientRepository clientRepository;

    @Override
    public Client getCurrentUserClient() {
        return clientRepository.findAll().iterator().next();
    }
}