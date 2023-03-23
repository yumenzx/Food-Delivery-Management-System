package presentation.clientGUI;

import business.DeliveryService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * interfata grafica pentru panoul clientilor
 */
public class ClientPanelView extends JFrame {

    private final JTable table;

    private final JTextField textFieldKeyword;
    private final JTextField caloriesMin;
    private final JTextField caloriesMax;
    private final JTextField proteinsMin;
    private final JTextField proteinsMax;
    private final JTextField fatsMin;
    private final JTextField fatsMax;
    private final JTextField sodiumMin;
    private final JTextField sodiumMax;
    private final JTextField priceMin;
    private final JTextField priceMax;

    private final JSlider slider;

    private final JButton btnApplyFilter;
    private final JButton btnCreateOrder;

    private final String[] tableHeader = new String[]{
            "Title", "Rating", "Calories", "Proteins", "Fats", "Sodium", "Price", "Description"
    };

    public ClientPanelView() {

        this.setTitle("Product Catalog");
        this.setBounds(100, 100, 1157, 481);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(226, 24, 900, 404);
        this.getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(null, tableHeader));
        table.setAutoCreateRowSorter(true);
        scrollPane.setViewportView(table);

        JLabel lblKeyword = new JLabel("Keyword");
        lblKeyword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblKeyword.setBounds(10, 50, 62, 19);
        this.getContentPane().add(lblKeyword);

        textFieldKeyword = new JTextField();
        textFieldKeyword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldKeyword.setBounds(88, 49, 96, 20);
        this.getContentPane().add(textFieldKeyword);
        textFieldKeyword.setColumns(10);

        JLabel lblRating = new JLabel("Rating");
        lblRating.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblRating.setBounds(10, 79, 53, 19);
        this.getContentPane().add(lblRating);

        JLabel lblRatingValue = new JLabel("0");
        lblRatingValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblRatingValue.setBounds(58, 78, 21, 20);
        this.getContentPane().add(lblRatingValue);

        slider = new JSlider(0, 5, 0);
        slider.setBounds(83, 79, 102, 31);
        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(1);
        slider.setPaintLabels(true);
        slider.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.getContentPane().add(slider);
        slider.addChangeListener(e -> lblRatingValue.setText(String.valueOf(slider.getValue())));

        JLabel lblCalories = new JLabel("Calories");
        lblCalories.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCalories.setBounds(10, 119, 62, 19);
        this.getContentPane().add(lblCalories);

        JLabel lblProteins = new JLabel("Proteins");
        lblProteins.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProteins.setBounds(10, 145, 62, 19);
        this.getContentPane().add(lblProteins);

        JLabel lblFats = new JLabel("Fats");
        lblFats.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFats.setBounds(10, 174, 62, 19);
        this.getContentPane().add(lblFats);

        JLabel lblSodium = new JLabel("Sodium");
        lblSodium.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSodium.setBounds(10, 203, 62, 19);
        this.getContentPane().add(lblSodium);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPrice.setBounds(10, 232, 62, 19);
        this.getContentPane().add(lblPrice);

        caloriesMin = new JTextField();
        caloriesMin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        caloriesMin.setColumns(10);
        caloriesMin.setBounds(88, 118, 42, 20);
        this.getContentPane().add(caloriesMin);

        caloriesMax = new JTextField();
        caloriesMax.setFont(new Font("Tahoma", Font.PLAIN, 14));
        caloriesMax.setColumns(10);
        caloriesMax.setBounds(142, 118, 42, 20);
        this.getContentPane().add(caloriesMax);

        proteinsMin = new JTextField();
        proteinsMin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        proteinsMin.setColumns(10);
        proteinsMin.setBounds(88, 145, 42, 20);
        this.getContentPane().add(proteinsMin);

        proteinsMax = new JTextField();
        proteinsMax.setFont(new Font("Tahoma", Font.PLAIN, 14));
        proteinsMax.setColumns(10);
        proteinsMax.setBounds(142, 145, 42, 20);
        this.getContentPane().add(proteinsMax);

        fatsMin = new JTextField();
        fatsMin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        fatsMin.setColumns(10);
        fatsMin.setBounds(88, 174, 42, 20);
        this.getContentPane().add(fatsMin);

        fatsMax = new JTextField();
        fatsMax.setFont(new Font("Tahoma", Font.PLAIN, 14));
        fatsMax.setColumns(10);
        fatsMax.setBounds(142, 174, 42, 20);
        this.getContentPane().add(fatsMax);

        sodiumMin = new JTextField();
        sodiumMin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sodiumMin.setColumns(10);
        sodiumMin.setBounds(88, 203, 42, 20);
        this.getContentPane().add(sodiumMin);

        sodiumMax = new JTextField();
        sodiumMax.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sodiumMax.setColumns(10);
        sodiumMax.setBounds(142, 203, 42, 20);
        this.getContentPane().add(sodiumMax);

        priceMin = new JTextField();
        priceMin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        priceMin.setColumns(10);
        priceMin.setBounds(88, 232, 42, 20);
        this.getContentPane().add(priceMin);

        priceMax = new JTextField();
        priceMax.setFont(new Font("Tahoma", Font.PLAIN, 14));
        priceMax.setColumns(10);
        priceMax.setBounds(142, 232, 42, 20);
        this.getContentPane().add(priceMax);

        JLabel lblFilter = new JLabel("Filter");
        lblFilter.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblFilter.setBounds(68, 16, 62, 29);
        this.getContentPane().add(lblFilter);

        btnApplyFilter = new JButton("Apply Filter");
        btnApplyFilter.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnApplyFilter.setBounds(42, 273, 123, 24);
        this.getContentPane().add(btnApplyFilter);

        btnCreateOrder = new JButton("Create Order");
        btnCreateOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCreateOrder.setBounds(42, 380, 142, 29);
        this.getContentPane().add(btnCreateOrder);

        refreshTable(DeliveryService.getClassInstance().getProducts(null));
        this.setVisible(true);
    }

    public void addBtnListeners(ActionListener applyFilterListener, ActionListener placeOrderListener) {
        btnApplyFilter.addActionListener(applyFilterListener);
        btnCreateOrder.addActionListener(placeOrderListener);
    }

    public String getTextFieldKeyword() {
        return textFieldKeyword.getText();
    }

    public String getCaloriesMin() {
        return caloriesMin.getText();
    }

    public String getCaloriesMax() {
        return caloriesMax.getText();
    }

    public String getProteinsMin() {
        return proteinsMin.getText();
    }

    public String getProteinsMax() {
        return proteinsMax.getText();
    }

    public String getFatsMin() {
        return fatsMin.getText();
    }

    public String getFatsMax() {
        return fatsMax.getText();
    }

    public String getSodiumMin() {
        return sodiumMin.getText();
    }

    public String getSodiumMax() {
        return sodiumMax.getText();
    }

    public String getPriceMin() {
        return priceMin.getText();
    }

    public String getPriceMax() {
        return priceMax.getText();
    }

    public int getRatingMin() {
        return slider.getValue();
    }

    /**
     * actualizeaza tabelul cu datele existente dat ca parametru
     *
     * @param dataProducts lista de produse
     */
    public void refreshTable(Object[][] dataProducts) {
        table.setModel(new DefaultTableModel(dataProducts, tableHeader));
    }

    /**
     * @return vector de elmente cu indicele liniilor selectate
     */
    public int[] getSelectedRows() {
        return table.getSelectedRows();
    }

    /**
     * @param row linia
     * @return valoarea din tabela de pe linia data ca parametru si prima coloana
     */
    public String tableValueAt(int row) {
        return String.valueOf(table.getValueAt(row, 0));
    }

    /**
     * se afiseaza mesajul dat ca parametru intro fereastra pop up
     *
     * @param message mesajul supus afisarii
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
