package presentation.administratorGUI;

import business.BaseProduct;
import business.CompositeProduct;
import business.DeliveryService;
import business.MenuItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * controllerul interfetei grafice al administratorului
 */
public class AdministratorPanelController {

    private final AdministratorPanelView view;
    private ProductEditorView editorView;

    public AdministratorPanelController() {
        view = new AdministratorPanelView();
        view.refreshTable(DeliveryService.getClassInstance().getProducts(null));
        view.addListeners(new AddButtonListener(), new ImportButtonListener(), new DeleteButtonListener(), new ModifyButtonListener(), new ComposeButtonListener(), new GenerateReportListener());
    }

    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    /**
     * metoda pt initializarea variabilelor in text fieldurile introduse
     *
     * @return true -> datele introduse sunt corecte; false -> datele introduse au un format invalid
     */
    private boolean getFields() {
        try {
            title = editorView.getTextFieldTitle();
            rating = Double.parseDouble(editorView.getTextFieldRating());
            calories = Integer.parseInt(editorView.getTextFieldCalories());
            protein = Integer.parseInt(editorView.getTextFieldProteins());
            fat = Integer.parseInt(editorView.getTextFieldFats());
            sodium = Integer.parseInt(editorView.getTextFieldSodium());
            price = Integer.parseInt(editorView.getTextFieldPrice());
            return true;
        } catch (Exception e) {
            view.showMessage("Format invalid");
            return false;
        }
    }

    /**
     * clasa ascultatoare a butonului de Add din gui ul principal
     */
    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            editorView = new ProductEditorView(false);
            editorView.addListeners(new AddButtonListener2(true));
        }
    }

    /**
     * clasa ascultatoare a butonului Add din editor panel
     */
    private class AddButtonListener2 implements ActionListener {
        boolean flag;

        public AddButtonListener2(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (getFields()) {
                try {
                    if (flag)
                        DeliveryService.getClassInstance().addProduct(new BaseProduct(title, rating, calories, protein, fat, sodium, price));
                    else {
                        int[] rows = view.getSelectedRows();

                        CompositeProduct p = new CompositeProduct(title, rating, calories, protein, fat, sodium, price);
                        Arrays.stream(rows).forEach(i -> p.addProduct(DeliveryService.getClassInstance().getProduct(view.tableValueAt(i, 0))));
                        DeliveryService.getClassInstance().addProduct(p);

                    }
                    view.refreshTable(DeliveryService.getClassInstance().getProducts(null));
                    editorView.dispose();
                } catch (AssertionError assertionError) {
                    view.showMessage(assertionError.getMessage());
                }
            }
        }
    }


    /**
     * clasa ascultatoare a butonului Modify din gui principal
     */
    private class ModifyButtonListener implements ActionListener {
        private String identifier;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getSelectedRows().length != 1)
                view.showMessage("Selectati un produs");
            else {
                int row = view.getSelectedRows()[0];
                identifier = view.tableValueAt(row, 0);

                editorView = new ProductEditorView(true);
                editorView.addListeners(new ModifyButtonListener2());
                editorView.setTextFieldTitle(identifier);
                editorView.setTextFieldRating(view.tableValueAt(row, 1));
                editorView.setTextFieldCalories(view.tableValueAt(row, 2));
                editorView.setTextFieldProteins(view.tableValueAt(row, 3));
                editorView.setTextFieldFats(view.tableValueAt(row, 4));
                editorView.setTextFieldSodium(view.tableValueAt(row, 5));
                editorView.setTextFieldPrice(view.tableValueAt(row, 6));
            }
        }

        /**
         * clasa ascultatoare a butonului Modify din Editor Panel
         */
        private class ModifyButtonListener2 implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getFields()) {
                    DeliveryService.getClassInstance().modifyProduct(identifier, new BaseProduct(title, rating, calories, protein, fat, sodium, price));
                    view.refreshTable(DeliveryService.getClassInstance().getProducts(null));
                    editorView.dispose();
                }
            }
        }

    }

    /**
     * clasa ascultatoare a butonului de Remove
     */
    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] rows = view.getSelectedRows();
            Arrays.stream(rows).forEach(i -> DeliveryService.getClassInstance().removeProduct(view.tableValueAt(i, 0)));
            view.refreshTable(DeliveryService.getClassInstance().getProducts(null));
            DeliveryService.save();
        }
    }

    /**
     * clasa ascultatoare a butonului Compose
     */
    private class ComposeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            editorView = new ProductEditorView(false);
            editorView.addListeners(new AddButtonListener2(false));
        }
    }

    /**
     * clasa ascultatoare a butonului Import
     */
    private class ImportButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            JFileChooser chooser = new JFileChooser();

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                String chosenFile = chooser.getSelectedFile().getAbsolutePath();

                Path path = Paths.get(chosenFile);
                if (Files.exists(path)) {
                    try {
                        HashMap<String, MenuItem> map = new HashMap<>();
                        Stream<String> stream = Files.lines(path).skip(1);
                        stream.forEach(str -> {
                            List<String> s = Arrays.asList(str.split(","));
                            map.put(s.get(0), new BaseProduct(s.get(0), Double.parseDouble(s.get(1)), Integer.parseInt(s.get(2)), Integer.parseInt(s.get(3)), Integer.parseInt(s.get(4)), Integer.parseInt(s.get(5)), Integer.parseInt(s.get(6))));
                        });
                        DeliveryService.getClassInstance().importProducts(map);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
                view.refreshTable(DeliveryService.getClassInstance().getProducts(null));
            }
        }
    }

    /**
     * clasa ascultatoare a butonului Generate Report
     */
    private class GenerateReportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                switch (view.getSelectedOption()) {
                    case 0 -> view.showMessage("Va rugam sa selectati o optiune");
                    case 1 -> {
                        int minHour = Integer.parseInt(view.getTextFieldStartHour());
                        int maxHour = Integer.parseInt(view.getTextFieldEndHour());
                        view.refreshReportTable(DeliveryService.getClassInstance().getReportByCriteria1(minHour, maxHour));
                    }
                    case 2 -> {
                        int orderedMoreThan = Integer.parseInt(view.getTextFieldNrProductsOrdered());
                        view.refreshReportTable(DeliveryService.getClassInstance().getReportByCriteria2(orderedMoreThan));
                    }
                    case 3 -> {
                        int orderedMoreThan = Integer.parseInt(view.getTextFieldNrOrders());
                        int valueBiggerThan = Integer.parseInt(view.getTextFieldPrice());
                        view.refreshReportTable(DeliveryService.getClassInstance().getReportByCriteria3(orderedMoreThan, valueBiggerThan));
                    }
                    case 4 -> {
                        int specifiedDay = view.getComboBox();
                        int nrTimes = Integer.parseInt(view.getTextFieldNrOfOrders());
                        view.refreshReportTable(DeliveryService.getClassInstance().getReportByCriteria4(specifiedDay, nrTimes));
                    }
                }
            } catch (NumberFormatException exception) {
                view.showMessage("Format invalid");
            } catch (AssertionError assertionError) {
                view.showMessage(assertionError.getMessage());
            }
        }
    }

}
