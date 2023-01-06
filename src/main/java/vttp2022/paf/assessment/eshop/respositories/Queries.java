package vttp2022.paf.assessment.eshop.respositories;

public class Queries {

    public static final String SQL_FIND_CUSTOMER_BY_NAME = "select * from customers where name LIKE ?;";

    public static final String SQL_INSERT_LINE_ITEM = "insert into line_item(order_id, item, quantity) values (?, ?, ?)";

    public static final String SQL_INSERT_ORDER = "insert into customer_order(order_id, name, address, email, order_date) values (?, ?, ?, ?, ?)";
    
}
