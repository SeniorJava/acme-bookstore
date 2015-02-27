package acme.sales.bookstore.domain.services;

import java.util.Date;

/**
 * @author vmuravlev
 */
public class DashboardStats {
    private int ordersQty;
    private double totalSum;
    private Date statsDate;

    public int getOrdersQty() {
        return ordersQty;
    }

    public void setOrdersQty(int ordersQty) {
        this.ordersQty = ordersQty;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    public Date getStatsDate() {
        return statsDate;
    }

    public void setStatsDate(Date statsDate) {
        this.statsDate = statsDate;
    }
}