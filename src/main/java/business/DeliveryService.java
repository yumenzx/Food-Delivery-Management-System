package business;

import data.FileWriter;
import data.Serializator;

import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Clasa controller pentru aplicatia principala
 * implementeaza operatiile din IDeliveryServiceProcessing
 */
@SuppressWarnings({"unchecked", "deprecation", "ConstantConditions"})
public class DeliveryService extends Observable implements IDeliveryServiceProcessing {

    private static HashMap<String, MenuItem> products;
    private static final List<Order> orders;

    private static final DeliveryService thisInstance = new DeliveryService();

    static {
        products = (HashMap<String, MenuItem>) Serializator.deserialize(Serializator.PRODUCTS);
        orders = (List<Order>) Serializator.deserialize(Serializator.ORDERS);
    }

    public static DeliveryService getClassInstance() {
        return thisInstance;
    }


    @Override
    public void importProducts(HashMap<String, MenuItem> p) {
        assert p != null : "Produsele importate nu pot fi null";
        products = p;
        Serializator.serialize(products, Serializator.PRODUCTS);
        assert products.equals(p) : "Asignarea la importare a esuat";
    }

    @Override
    public void addProduct(MenuItem m) {
        assert m != null : "Produsul adaugat nu poate fi null";
        assert products.get(m.getTitle()) == null : "Produsul exista deja";
        products.put(m.getTitle(), m);
        Serializator.serialize(products, Serializator.PRODUCTS);
        assert products.containsKey(m.getTitle()) : "Adaugarea produsului a esuat";
    }

    @Override
    public void modifyProduct(String identifier, MenuItem m) {
        assert !identifier.equals("") : "Identificatorul produsului nu poate fi anomin";
        assert m != null : "Produsul dupa care se face actualizarea trebuie sa existe";
        MenuItem p = products.remove(identifier);
        if (p instanceof CompositeProduct) {
            CompositeProduct c = new CompositeProduct(m.getTitle(), m.getRating(), m.getCalories(), m.getProteins(), m.getFats(), m.getSodium(), m.computePrice());
            c.setList(((CompositeProduct) p).getList());
            products.put(m.getTitle(), c);
        } else
            products.put(m.getTitle(), m);
        Serializator.serialize(products, Serializator.PRODUCTS);
        assert products.containsValue(m) : "Produsul nu a fost actualizat";
    }

    @Override
    public void removeProduct(String title) {
        assert !title.equals("") : "Produsul nu poate fi anomin";
        MenuItem m = products.remove(title);
        assert !products.containsKey(m) : "Produsul nu a fost sters";
    }


    @Override
    public Object[][] getReportByCriteria1(int startHour, int endHour) {

        assert startHour <= endHour && startHour >= 0 && endHour <= 24 : "Intervalul orar " + startHour + "-" + endHour + " nu este valid";

        List<Order> results = orders.stream().filter(o -> o.getDate().getHours() >= startHour && o.getDate().getHours() < endHour).collect(Collectors.toList());

        Object[][] list = new Object[results.size() + 1][2];
        list[0] = new String[]{"Order ID", "Date"};
        final int[] i = {1};
        results.forEach(o -> {
            list[i[0]][0] = o.getOrderID();
            list[i[0]][1] = o.getDate();
            i[0]++;
        });

        assert list != null : "Lista in urma generarii raportului 1 este null";
        return list;
    }

    @Override
    public Object[][] getReportByCriteria2(int orderedMoreThan) {

        assert orderedMoreThan >= 0 : "Numarul " + orderedMoreThan + " nu este unul valid";

        List<MenuItem> produse = new ArrayList<>();
        orders.forEach(o -> produse.addAll(o.getList()));

        Map<MenuItem, Long> frequencyMap = produse.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Map.Entry<MenuItem, Long>> results = frequencyMap.entrySet().stream().filter(f -> f.getValue() > orderedMoreThan).collect(Collectors.toList());

        Object[][] list = new Object[results.size() + 1][2];
        list[0] = new String[]{"Product Name", "Nr Of Orders"};
        final int[] i = {1};
        results.forEach(o -> {
            list[i[0]][0] = o.getKey().getTitle();
            list[i[0]][1] = o.getValue();
            i[0]++;
        });

        assert list != null : "Lista in urma raportului 2 este null";
        return list;
    }

    @Override
    public Object[][] getReportByCriteria3(int orderedMoreThan, int valueBiggerThan) {

        assert orderedMoreThan >= 0 && valueBiggerThan > 0 : "Valoarile " + orderedMoreThan + " sau " + valueBiggerThan + " nu sunt valide";

        List<Order> comenzi = orders.stream().filter(o -> o.getPrice() > valueBiggerThan).collect(Collectors.toList());
        List<Integer> clients = comenzi.stream().map(Order::getClientID).collect(Collectors.toList());

        Map<Integer, Long> frequencyMap = clients.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Map.Entry<Integer, Long>> results = frequencyMap.entrySet().stream().filter(f -> f.getValue() > orderedMoreThan).collect(Collectors.toList());

        Object[][] list = new Object[results.size() + 1][3];
        list[0] = new String[]{"Client ID", "Username", "Nr Of Orders"};
        final int[] i = {1};
        results.forEach(c -> {
            list[i[0]][0] = c.getKey();
            list[i[0]][1] = UserService.findById(c.getKey()).getUsername();
            list[i[0]][2] = c.getValue();
            i[0]++;
        });

        assert list != null : "Lista in urma raportului 3 este null";
        return list;
    }

    @Override
    public Object[][] getReportByCriteria4(int specifiedDay, int nrTimes) {
        assert specifiedDay >= 0 && specifiedDay < 7 : "Ziua " + specifiedDay + " nu este una valida";
        assert nrTimes >= 0 : "Numarul " + nrTimes + " nu este valid";

        List<Order> comenzi = orders.stream().filter(o -> o.getDate().getDay() == specifiedDay).collect(Collectors.toList());
        List<MenuItem> produse = new ArrayList<>();
        comenzi.forEach(o -> produse.addAll(o.getList()));

        Map<MenuItem, Long> frequencyMap = produse.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Map.Entry<MenuItem, Long>> results = frequencyMap.entrySet().stream().filter(f -> f.getValue() == nrTimes).collect(Collectors.toList());

        Object[][] list = new Object[results.size() + 1][2];
        list[0] = new String[]{"Product Name", "Nr Of Orders"};
        final int[] i = {1};
        results.forEach(o -> {
            list[i[0]][0] = o.getKey().getTitle();
            list[i[0]][1] = o.getValue();
            i[0]++;
        });

        assert list != null : "Lista in urma raportului 4 este null";
        return list;
    }


    @Override
    public void placeOrder(Order o) {

        assert o != null : "Comanda trebuie sa existe, nu poate fi null";
        boolean succes = orders.add(o);
        assert succes : "Comanda nu fost adaugata";

        Serializator.serialize(orders, Serializator.ORDERS);
        FileWriter.createBill(o);
        setChanged();
        notifyObservers("O noua comanda cu ID=" + o.getOrderID() + " a fost plasata");
        clearChanged();
    }

    @Override
    public Object[][] getProductsByCriteria(String keyWord, int minRating, int minCalories, int maxCalories, int minProtein, int maxProtein, int minFat, int maxFat, int minSodium, int maxSodium, int minPrice, int maxPrice) {

        assert minCalories >= 0 && minCalories <= maxCalories : "Intervalul de calorii nu este valid";
        assert minProtein >= 0 && minProtein <= maxProtein : "Intervalul de proteine nu este valid";
        assert minFat >= 0 && minFat <= maxFat : "Intervalul de fat nu este valid";
        assert minSodium >= 0 && minSodium <= maxSodium : "Intervalul de sodium nu este valid";
        assert minPrice >= 0 && minPrice <= maxPrice : "Intervalul de pret nu este valid";

        List<MenuItem> resultSet = new ArrayList<>(products.values());

        if (!keyWord.equals(""))
            resultSet = resultSet.stream().filter(p -> p.getTitle().toLowerCase(Locale.ROOT).contains(keyWord.toLowerCase(Locale.ROOT))).collect(Collectors.toList());
        resultSet = resultSet.stream().filter(p -> p.getRating() >= minRating).collect(Collectors.toList());
        resultSet = resultSet.stream().filter(p -> p.getCalories() >= minCalories && p.getCalories() <= maxCalories).collect(Collectors.toList());
        resultSet = resultSet.stream().filter(p -> p.getProteins() >= minProtein && p.getProteins() <= maxProtein).collect(Collectors.toList());
        resultSet = resultSet.stream().filter(p -> p.getFats() >= minFat && p.getFats() <= maxFat).collect(Collectors.toList());
        resultSet = resultSet.stream().filter(p -> p.getSodium() >= minSodium && p.getSodium() <= maxSodium).collect(Collectors.toList());
        resultSet = resultSet.stream().filter(p -> p.computePrice() >= minPrice && p.computePrice() <= maxPrice).collect(Collectors.toList());

        assert resultSet != null : "Setul de rezultate este null";
        return getProducts(resultSet);
    }

    /**
     * returneaza produsul cautat dat prin identificator
     *
     * @param identifier identificatorul produsului
     * @return produsul propriu zis
     */
    public MenuItem getProduct(String identifier) {
        assert identifier != null || !identifier.equals("") : "Identificatorul produsului nu poate fi null";
        return products.get(identifier);
    }

    /**
     * returneaza produsele sub forma de matrice
     *
     * @param productsSet produsele dorite transformarii. Daca este null, transformarea se va face pentru produsele din catalog
     * @return lista de produse sub forma de matrice
     */
    public Object[][] getProducts(List<MenuItem> productsSet) {

        List<MenuItem> resultSet = productsSet;

        if (productsSet == null)
            resultSet = new ArrayList<>(products.values());

        Object[][] tableData = new Object[resultSet.size()][8];
        final int[] i = {0};
        resultSet.forEach(e -> {
            tableData[i[0]][0] = e.getTitle();
            tableData[i[0]][1] = e.getRating();
            tableData[i[0]][2] = e.getCalories();
            tableData[i[0]][3] = e.getProteins();
            tableData[i[0]][4] = e.getFats();
            tableData[i[0]][5] = e.getSodium();
            tableData[i[0]][6] = e.computePrice();
            if (e instanceof CompositeProduct)
                tableData[i[0]][7] = e.toString();
            i[0]++;
        });
        return tableData;
    }

    /**
     * returneaza lista de comenzi sub forma de matrice
     *
     * @return lista de comenzi
     */
    public Object[][] getOrders() {

        Object[][] tableData = new Object[orders.size()][3];
        final int[] i = {0};
        orders.forEach(o -> {
            tableData[i[0]][0] = o.getOrderID();
            tableData[i[0]][1] = o.getDate();
            StringBuilder s = new StringBuilder();
            o.getList().forEach(p -> s.append(p.getTitle()).append(", "));
            tableData[i[0]][2] = s.deleteCharAt(s.length() - 1).deleteCharAt(s.length() - 1);
            i[0]++;
        });

        return tableData;
    }

    /**
     * salveaza produsele prin serializare
     * metode se apeleaza cand se fac multe operatii pe tabela de produse deodate, de exemplu stergerea a mai multor produse
     */
    public static void save() {
        Serializator.serialize(products, Serializator.PRODUCTS);
    }
}
