package acme.sales.bookstore.domain.test;

import acme.sales.bookstore.domain.entities.Book;

/**
 * @author vmuravlev
 */
public class BookBuilder {
    private Book book;

    public static BookBuilder aBook(String title) {
        return new BookBuilder(title);
    }

    private BookBuilder(String title) {
        book = new Book();
        book.setTitle(title);
    }

    public BookBuilder author(String author) {
        this.book.setAuthor(author);
        return this;
    }

    public BookBuilder genre(String genre) {
        this.book.setGenre(genre);
        return this;
    }

    public BookBuilder priced(double price) {
        this.book.setPrice(price);
        return this;
    }

    public Book build() {
        return book;
    }
}