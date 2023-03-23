package business;

import java.util.ArrayList;
import java.util.List;

/**
 * clasa principala pentru un produs compus din mai multe produse de tipul BaseProduct
 */
public class CompositeProduct extends MenuItem {

    private List<MenuItem> list;

    public CompositeProduct(String title, double rating, int calories, int proteins, int fats, int sodium, int price) {
        super(title, rating, calories, proteins, fats, sodium, price);
        list = new ArrayList<>();
    }


    public void addProduct(MenuItem p) {
        list.add(p);
    }

    public List<MenuItem> getList() {
        return list;
    }

    public void setList(List<MenuItem> list) {
        this.list = list;
    }

    @Override
    public int computePrice() {
        //return list.stream().mapToInt(MenuItem::computePrice).sum();
        return super.computePrice();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        list.forEach(m -> s.append(m.getTitle()).append(", "));
        s.deleteCharAt(s.length() - 1).deleteCharAt(s.length() - 1);
        return s.toString();
    }
}
