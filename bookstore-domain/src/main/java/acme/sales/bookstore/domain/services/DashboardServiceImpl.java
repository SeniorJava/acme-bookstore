package acme.sales.bookstore.domain.services;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author vmuravlev
 */
public class DashboardServiceImpl implements DashboardService {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    @Transactional
    public void collectStats() {
        final Date today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
        final Integer orderQty = jdbcTemplate.queryForObject("SELECT count(1) FROM book_order WHERE order_date = ?",
                Integer.class, today);
        final Double totalSum = jdbcTemplate.queryForObject(
                "SELECT sum(price * qty) FROM book_order_line ol, book_order o WHERE o.id = ol.order_id AND o.order_date = ?",
                Double.class, today);

        Integer todayStatCount = jdbcTemplate.queryForObject("SELECT count(1) FROM dashboard_stats WHERE stat_date=?",
                Integer.class, today);

        PreparedStatementCallback<Object> setParamsCallback = new PreparedStatementCallback<Object>() {

            @Override
            public Object doInPreparedStatement(PreparedStatement pStatement) throws SQLException, DataAccessException {
                pStatement.setInt(1, orderQty);
                pStatement.setDouble(2, totalSum);
                pStatement.setDate(3, new java.sql.Date(today.getTime()));

                pStatement.executeUpdate();

                return null;
            }
        };

        boolean alreadyCollected = todayStatCount > 0;

        if (alreadyCollected) {
            jdbcTemplate.execute("UPDATE dashboard_stats SET orders_qty=?, total_sum=? WHERE stat_date=?",
                    setParamsCallback);
        } else {
            jdbcTemplate.execute("INSERT INTO dashboard_stats(orders_qty, total_sum, stat_date) VALUES (?,?,?)",
                    setParamsCallback);
        }
    }
}