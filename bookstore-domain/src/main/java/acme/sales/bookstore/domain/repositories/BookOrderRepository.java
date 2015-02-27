package acme.sales.bookstore.domain.repositories;

import acme.sales.bookstore.domain.entities.BookOrder;
import acme.sales.bookstore.domain.entities.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * @author vmuravlev
 */
public interface BookOrderRepository extends CrudRepository<BookOrder, Integer> {
    Collection<BookOrder> findByClient(Client client);
}