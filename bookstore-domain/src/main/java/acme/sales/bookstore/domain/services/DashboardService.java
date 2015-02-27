package acme.sales.bookstore.domain.services;

import java.util.Date;

/**
 * @author vmuravlev
 */
public interface DashboardService {
    void collectStats();
    DashboardStats getStats(Date date);
}
