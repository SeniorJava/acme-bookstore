package acme.sales.bookstore.domain.services;

import acme.sales.bookstore.domain.entities.Client;
import acme.sales.bookstore.domain.entities.User;
import acme.sales.bookstore.domain.repositories.ClientRepository;
import acme.sales.bookstore.domain.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * @author vmuravlev
 */
public class AdministrationServiceImpl implements AdministrationService {

    @Inject
    private ClientRepository clientRepository;

    @Inject
    private UserRepository userRepository;

    @Override
    @Transactional
    public void createNewClient(Client client) {
        clientRepository.save(client);
        User newUser = new User(client);
        newUser.getAuthorities().add("ROLE_CUSTOMER");
        userRepository.save(newUser);
    }
}