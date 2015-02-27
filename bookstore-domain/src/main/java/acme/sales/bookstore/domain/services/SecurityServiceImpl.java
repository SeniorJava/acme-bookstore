package acme.sales.bookstore.domain.services;

import acme.sales.bookstore.domain.entities.Client;
import acme.sales.bookstore.domain.repositories.ClientRepository;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.inject.Inject;

/**
 * @author vmuravlev
 */
public class SecurityServiceImpl implements SecurityService {

    @Inject
    private ClientRepository clientRepository;

    @Override
    public Client getCurrentUserClient() {
        return clientRepository.findOneByFirstName(
                SecurityContextHolder.getContext().getAuthentication().getName());
    }
}