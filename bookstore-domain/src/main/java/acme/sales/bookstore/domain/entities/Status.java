package acme.sales.bookstore.domain.entities;

/**
 * @author vmuravlev
 */
public enum Status {
    NEW("Новый"), ACCEPTED("Подтвержден"), COMPLETE("Выполнен");

    private String title;

    Status(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}