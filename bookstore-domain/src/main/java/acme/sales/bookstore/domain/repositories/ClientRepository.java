package acme.sales.bookstore.domain.repositories;

import acme.sales.bookstore.domain.entities.Client;
import org.springframework.data.repository.CrudRepository;

/**
 * @author vmuravlev
 */
public interface ClientRepository extends CrudRepository<Client, Integer> {
}
