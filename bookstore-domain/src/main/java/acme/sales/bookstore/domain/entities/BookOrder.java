package acme.sales.bookstore.domain.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * @author vmuravlev
 */
@Entity
@Table(name = "book_order", schema = "acme_bookstore")
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="order_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "order_date")
    private Date orderDate;

    @Basic
    private String comments;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Collection<BookOrderLine> lines;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Collection<BookOrderLine> getLines() {
        return lines;
    }

    public void setLines(Collection<BookOrderLine> lines) {
        this.lines = lines;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}