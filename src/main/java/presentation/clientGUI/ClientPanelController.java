package presentation.clientGUI;

import business.DeliveryService;
import business.MenuItem;
import business.Order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * clasa controller pentru interfata grafica a clientilor
 */
public class ClientPanelController {

    private final ClientPanelView view;

    private final int clientId;

    public ClientPanelController(int clientId) {
        this.clientId = clientId;
        view = new ClientPanelView();
        view.addBtnListeners(new ApplyFilterBtnListener(), new CreateOrderBtnListener());
    }

    /**
     * clasa ascultatoare pentru butonul de Apply Filter
     */
    private class ApplyFilterBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int minCalories, maxCalories, minProtein, maxProteins, minFat, maxFat, minSodium, maxSodium, minPrice, maxPrice;
            try {
                minCalories = Integer.parseInt(view.getCaloriesMin().equals("") ? "0" : view.getCaloriesMin());
                maxCalories = Integer.parseInt(view.getCaloriesMax().equals("") ? "2147483647" : view.getCaloriesMax());
                minProtein = Integer.parseInt(view.getProteinsMin().equals("") ? "0" : view.getProteinsMin());
                maxProteins = Integer.parseInt(view.getProteinsMax().equals("") ? "2147483647" : view.getProteinsMax());
                minFat = Integer.parseInt(view.getFatsMin().equals("") ? "0" : view.getFatsMin());
                maxFat = Integer.parseInt(view.getFatsMax().equals("") ? "2147483647" : view.getFatsMax());
                minSodium = Integer.parseInt(view.getSodiumMin().equals("") ? "0" : view.getSodiumMin());
                maxSodium = Integer.parseInt(view.getSodiumMax().equals("") ? "2147483647" : view.getSodiumMax());
                minPrice = Integer.parseInt(view.getPriceMin().equals("") ? "0" : view.getPriceMin());
                maxPrice = Integer.parseInt(view.getPriceMax().equals("") ? "2147483647" : view.getPriceMax());

                view.refreshTable(DeliveryService.getClassInstance().getProductsByCriteria(view.getTextFieldKeyword(), view.getRatingMin(), minCalories, maxCalories, minProtein, maxProteins, minFat, maxFat, minSodium, maxSodium, minPrice, maxPrice));
            } catch (NumberFormatException exception) {
                view.showMessage("Format invalid: " + exception.getMessage());
            } catch (AssertionError assertionError) {
                view.showMessage(assertionError.getMessage());
            }
        }
    }

    /**
     * clasa ascultatoare pentru butonul Create Order
     */
    private class CreateOrderBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] rows = view.getSelectedRows();
            if (rows.length > 0) {
                List<MenuItem> list = new ArrayList<>();
                Arrays.stream(rows).forEach(i -> list.add(DeliveryService.getClassInstance().getProduct(view.tableValueAt(i))));
                try {
                    DeliveryService.getClassInstance().placeOrder(new Order(clientId, list));
                }catch (AssertionError assertionError){
                    view.showMessage(assertionError.getMessage());
                }
                view.showMessage("Comanda a fost plasata");
            } else
                view.showMessage("Selectati cel putin un produs din lista de produse");
        }
    }
}
