package bll;

import dao.OrderDao;
import model.Orders;

/**
 * Aceasta este o clasa care apeleaza metodele din clasa DAO corespunzatoare
 * @author Tincu Diana
 *
 */
public class OrderBll {
    private OrderDao orderDao;
    public OrderBll()
    {
        orderDao=new OrderDao();
    }
    public int insertOrder( Orders order ) throws IllegalArgumentException, IllegalAccessException{
        int result = orderDao.insert(order);
        return result;
    }
}
