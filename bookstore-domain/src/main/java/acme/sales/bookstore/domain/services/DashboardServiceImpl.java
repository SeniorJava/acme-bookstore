package acme.sales.bookstore.domain.services;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author vmuravlev
 */
public class DashboardServiceImpl implements DashboardService {

    private static final DashboardStatsRowMapper DASHBOARD_STATS_ROW_MAPPER = new DashboardStatsRowMapper();
    private JdbcTemplate jdbcTemplate;

    @Inject
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private Logger logger = LoggerFactory.getLogger("DashboardService");

    @Override
    @Transactional
    public void collectStats() {
        logger.info("Collecting dashboard statistic...");

        final Date today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);

        final Integer orderQty = jdbcTemplate.queryForObject("SELECT count(1) FROM book_order WHERE order_date = ?",
                Integer.class, today);
        final Double totalSum = jdbcTemplate.queryForObject(
                "SELECT sum(price * qty) FROM book_order_line ol, book_order o WHERE o.id = ol.order_id AND o.order_date = ?",
                Double.class, today);

        Integer todayStatCount = jdbcTemplate.queryForObject("SELECT count(1) FROM dashboard_stats WHERE stat_date=?",
                Integer.class, today);
        boolean alreadyCollected = todayStatCount > 0;

        PreparedStatementCallback<Object> updateCallback = new UpdateStatsCallback(orderQty, totalSum, today);

        if (alreadyCollected) {
            jdbcTemplate.execute("UPDATE dashboard_stats SET orders_qty=?, total_sum=? WHERE stat_date=?",
                    updateCallback);
        } else {
            jdbcTemplate.execute("INSERT INTO dashboard_stats(orders_qty, total_sum, stat_date) VALUES (?,?,?)",
                    updateCallback);
        }

        logger.info("Dashboard statistic collected");
    }

    @Override
    public DashboardStats getStats(Date date) {
        List<DashboardStats> statsList = jdbcTemplate.query(
                "SELECT * FROM dashboard_stats WHERE stat_date=?",
                DASHBOARD_STATS_ROW_MAPPER, date);

        return statsList.isEmpty() ? null : statsList.get(0);
    }

    private static class DashboardStatsRowMapper implements RowMapper<DashboardStats> {
        @Override
        public DashboardStats mapRow(ResultSet rs, int rowNum) throws SQLException {
            DashboardStats stats = new DashboardStats();
            stats.setOrdersQty(rs.getInt("orders_qty"));
            stats.setTotalSum(rs.getDouble("total_sum"));
            stats.setStatsDate(rs.getDate("stat_date"));

            return stats;
        }
    }

    private static class UpdateStatsCallback implements PreparedStatementCallback<Object> {

        private final Integer orderQty;
        private final Double totalSum;
        private final Date today;

        public UpdateStatsCallback(Integer orderQty, Double totalSum, Date today) {
            this.orderQty = orderQty;
            this.totalSum = totalSum;
            this.today = today;
        }

        @Override
        public Object doInPreparedStatement(PreparedStatement pStatement) throws SQLException, DataAccessException {
            pStatement.setInt(1, orderQty != null ? orderQty : 0);
            pStatement.setDouble(2, totalSum != null ? totalSum : 0.0);
            pStatement.setDate(3, new java.sql.Date(today.getTime()));

            pStatement.executeUpdate();

            return null;
        }
    }
}