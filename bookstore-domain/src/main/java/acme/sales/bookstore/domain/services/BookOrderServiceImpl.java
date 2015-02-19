package acme.sales.bookstore.domain.services;

import acme.sales.bookstore.domain.entities.*;
import acme.sales.bookstore.domain.repositories.BookOrderRepository;
import acme.sales.bookstore.domain.repositories.BookRepository;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * @author vmuravlev
 */
public class BookOrderServiceImpl implements BookOrderService {

    @Inject
    private BookOrderRepository orderRepository;

    @Inject
    private BookRepository bookRepository;

    @Override
    public BookOrder prepareOrder(Client client) {
        BookOrder newOrder = new BookOrder();
        newOrder.setClient(client);
        newOrder.setStatus(Status.NEW);
        newOrder.setLines(new ArrayList<BookOrderLine>());

        orderRepository.save(newOrder);
        return newOrder;
    }

    @Override
    public void addBook(BookOrder order, Book book, int qty) {
        BookOrderLine newLine = new BookOrderLine();
        newLine.setOrder(order);
        newLine.setBook(book);
        newLine.setQty(qty);

        order.getLines().add(newLine);

        orderRepository.save(order);
    }

    @Override
    public void confirmOrder(BookOrder bookOrder) {
        bookOrder.setStatus(Status.ACCEPTED);
        orderRepository.save(bookOrder);
    }
}