package vttp2022.paf.assessment.eshop.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;

import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

import java.util.List;

@Repository
public class OrderRepository {
	// TODO: Task 3

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void addLineItems(Order customerOrder) {
		addLineItems(customerOrder.getLineItems(), customerOrder.getOrderId());
	}

	public void addLineItems(List<LineItem> lineItems, String orderId) {

        List<Object[]> data = lineItems.stream()
            .map(li -> {
                Object[] l = new Object[3];
                l[0] = li.getItem();
                l[1] = li.getQuantity();
                l[2] = orderId;
                return l;
            })
            .toList();

        // Batch update
        jdbcTemplate.batchUpdate(SQL_INSERT_LINE_ITEM, data);
    }

	public boolean insertOrder(Order co) {
		return jdbcTemplate.update(SQL_INSERT_ORDER, 
			co.getOrderId(), co.getName(), co.getAddress(), co.getEmail(), co.getOrderDate()) > 0;
	}
}
