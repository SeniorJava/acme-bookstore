package acme.sales.bookstore.domain.services;

import acme.sales.bookstore.domain.entities.*;
import acme.sales.bookstore.domain.repositories.BookOrderRepository;
import acme.sales.bookstore.domain.repositories.ClientRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @author vmuravlev
 */
public class BookOrderServiceImpl implements BookOrderService {

    @Inject
    private BookOrderRepository orderRepository;

    @Inject
    private ClientRepository clientRepository;

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
    @Transactional
    public void confirmOrder(BookOrder bookOrder) {
        bookOrder.setStatus(Status.ACCEPTED);
        orderRepository.save(bookOrder);
    }

    @Override
    @Transactional
    public void createOrder(Collection<BookOrderLine> orderLines) {
        BookOrder bookOrder = new BookOrder();
        bookOrder.setLines(orderLines);
        for (BookOrderLine orderLine : orderLines) {
            orderLine.setOrder(bookOrder);
            orderLine.setPrice(orderLine.getBook().getPrice());
        }

        Client client = getCurrentUserClient();
        bookOrder.setClient(client);
        bookOrder.setOrderDate(new Date());
        bookOrder.setStatus(Status.NEW);

        orderRepository.save(bookOrder);
    }

    private Client getCurrentUserClient() {
        return clientRepository.findOneByFirstName(
                    SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public Collection<BookOrder> getCurrentUserOrders() {
        return orderRepository.findByClient(getCurrentUserClient());
    }
}