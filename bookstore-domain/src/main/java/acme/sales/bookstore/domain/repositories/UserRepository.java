package acme.sales.bookstore.domain.repositories;

import acme.sales.bookstore.domain.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author vmuravlev
 */
public interface UserRepository extends CrudRepository<User, String> {
}
