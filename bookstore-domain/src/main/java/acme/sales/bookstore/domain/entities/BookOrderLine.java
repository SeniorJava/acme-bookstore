package acme.sales.bookstore.domain.entities;

import javax.persistence.*;

/**
 * @author vmuravlev
 */
@Entity
@Table(name = "book_order_line", schema = "acme_bookstore")
public class BookOrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private BookOrder order;

    @Basic
    private Double price;

    @Basic
    private int qty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BookOrder getOrder() {
        return order;
    }

    public void setOrder(BookOrder order) {
        this.order = order;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}