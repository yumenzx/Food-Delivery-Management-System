package business;

import java.io.Serializable;
import java.util.*;

/**
 * clasa principala pentru comenzi
 */
public class Order implements Serializable {

    private final int orderID;
    private final int clientID;
    private final Date date;
    private final int price;

    private final List<MenuItem> list;

    public Order(int clientID, List<MenuItem> productsList) {
        assert productsList != null && productsList.size() > 0 : "O comanda ar trebui sa contina cel putin un produs";
        this.clientID = clientID;
        list = productsList;
        date = Calendar.getInstance().getTime();
        orderID = (toString() + clientID + date).hashCode();
        //list.forEach(m -> price += m.computePrice());
        price = list.stream().mapToInt(MenuItem::computePrice).sum();
    }

    public int getOrderID() {
        return orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public Date getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }

    public List<MenuItem> getList() {
        return list;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        list.forEach(m -> s.append(m.getTitle()).append(".......... ").append(m.computePrice()).append("\n"));
        return s.toString();
    }
}
