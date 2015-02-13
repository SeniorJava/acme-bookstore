package acme.sales.bookstore.domain.services;

import acme.sales.bookstore.domain.entities.Book;
import acme.sales.bookstore.domain.entities.BookOrder;
import acme.sales.bookstore.domain.entities.Client;

/**
 * @author vmuravlev
 */
public interface BookOrderService {
    BookOrder prepareOrder(Client client);
    void addBook(BookOrder order, Book book, int qty);
    void confirmOrder(BookOrder bookOrder);
}
