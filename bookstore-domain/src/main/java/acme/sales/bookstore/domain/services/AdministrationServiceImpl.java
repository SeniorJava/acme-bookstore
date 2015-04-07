package acme.sales.bookstore.domain.services;

import acme.sales.bookstore.domain.entities.Client;
import acme.sales.bookstore.domain.entities.User;
import acme.sales.bookstore.domain.repositories.ClientRepository;
import acme.sales.bookstore.domain.repositories.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * @author vmuravlev
 */
@Component("administrationService")
public class AdministrationServiceImpl implements AdministrationService {

    @Inject
    private ClientRepository clientRepository;

    @Inject
    private UserRepository userRepository;

    @Override
    @Transactional
    public void createNewClient(Client client) throws AdministrationException {

        if (hasDuplicate(client)) {
            throw new AdministrationException(String.format("Duplicate user %s", client.getFirstName()));
        }

        clientRepository.save(client);
        User newUser = new User(client);
        newUser.getAuthorities().add("ROLE_CUSTOMER");
        userRepository.save(newUser);
    }

    @Override
    public boolean hasDuplicate(Client client) {
        return clientRepository.findOneByFirstName(client.getFirstName()) != null;
    }
}