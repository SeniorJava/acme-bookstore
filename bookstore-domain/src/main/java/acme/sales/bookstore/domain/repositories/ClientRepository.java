package acme.sales.bookstore.domain.repositories;

import acme.sales.bookstore.domain.entities.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author vmuravlev
 */
public interface ClientRepository extends CrudRepository<Client, Integer> {
    @Query("select c from Client c where UPPER(c.firstName) = UPPER(:firstName)")
    Client findOneByFirstName(@Param("firstName") String firstName);
}
