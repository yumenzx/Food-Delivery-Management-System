package presentation.administratorGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * interfata grafica pentru administrator
 */
public class AdministratorPanelView extends JFrame {

    private final JTable tableProducts;
    private final JTable tableReports;

    private final JTextField textFieldStartHour;
    private final JTextField textFieldEndHour;
    private final JTextField textFieldNrProductsOrdered;
    private final JTextField textFieldNrOrders;
    private final JTextField textFieldPrice;
    private final JTextField textFieldNrOfOrders;

    private final JComboBox<String> comboBox;

    private final JButton btnImport;
    private final JButton btnAdd;
    private final JButton btnModify;
    private final JButton btnDelete;
    private final JButton btnCompose;
    private final JButton btnGenerateReport;

    private final JRadioButton[] radioButton = new JRadioButton[4];

    private final String[] tableProductsHeader = new String[]{
            "Title", "Rating", "Calories", "Proteins", "Fats", "Sodium", "Price", "Description"
    };

    public AdministratorPanelView() {
        this.setTitle("Administrator panel");
        this.setBounds(100, 100, 1164, 552);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 1150, 515);
        this.getContentPane().add(tabbedPane);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        tabbedPane.addTab("Manage Products", null, panel, null);
        panel.setLayout(null);

        btnImport = new JButton("Import Products");
        btnImport.setBounds(10, 452, 145, 26);
        panel.add(btnImport);
        btnImport.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JScrollPane scrollPane1_1 = new JScrollPane();
        scrollPane1_1.setBounds(10, 51, 1118, 371);
        panel.add(scrollPane1_1);

        tableProducts = new JTable();
        tableProducts.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tableProducts.setModel(new DefaultTableModel(null, tableProductsHeader));
        scrollPane1_1.setViewportView(tableProducts);
        tableProducts.setAutoCreateRowSorter(true);

        btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnAdd.setBounds(778, 443, 110, 26);
        panel.add(btnAdd);

        btnDelete = new JButton("Remove");
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnDelete.setBounds(1018, 443, 110, 26);
        panel.add(btnDelete);

        btnModify = new JButton("Modify");
        btnModify.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnModify.setBounds(898, 443, 110, 26);
        panel.add(btnModify);

        btnCompose = new JButton("Compose Menu");
        btnCompose.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCompose.setBounds(487, 432, 158, 35);
        panel.add(btnCompose);

        JLabel lblNewLabel = new JLabel("Manage Products");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(477, 10, 168, 20);
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        tabbedPane.addTab("Generate Report", null, panel_1, null);
        panel_1.setLayout(null);

        radioButton[0] = new JRadioButton("<html>Orders performed between start hour and end hour regardless the date</html>");
        radioButton[0].setBackground(Color.WHITE);
        radioButton[0].setFont(new Font("Tahoma", Font.PLAIN, 14));
        radioButton[0].setBounds(16, 59, 430, 43);
        panel_1.add(radioButton[0]);

        radioButton[1] = new JRadioButton("<html>The products ordered more than specified number of times so far</html>");
        radioButton[1].setBackground(Color.WHITE);
        radioButton[1].setFont(new Font("Tahoma", Font.PLAIN, 14));
        radioButton[1].setBounds(16, 101, 430, 46);
        panel_1.add(radioButton[1]);

        radioButton[2] = new JRadioButton("<html>The clients that have ordered more than specified number of times so far and the value of the order was higher than specified amount</html>");
        radioButton[2].setBackground(Color.WHITE);
        radioButton[2].setFont(new Font("Tahoma", Font.PLAIN, 14));
        radioButton[2].setBounds(18, 149, 430, 51);
        panel_1.add(radioButton[2]);

        radioButton[3] = new JRadioButton("<html>The products ordered within specified day with the number of times they have been ordered</html>");
        radioButton[3].setBackground(Color.WHITE);
        radioButton[3].setFont(new Font("Tahoma", Font.PLAIN, 14));
        radioButton[3].setBounds(18, 202, 430, 43);
        panel_1.add(radioButton[3]);

        btnGenerateReport = new JButton("Generate Report");
        btnGenerateReport.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnGenerateReport.setBounds(214, 267, 158, 30);
        panel_1.add(btnGenerateReport);

        textFieldStartHour = new JTextField();
        textFieldStartHour.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldStartHour.setBounds(452, 71, 43, 22);
        panel_1.add(textFieldStartHour);
        textFieldStartHour.setColumns(10);

        textFieldEndHour = new JTextField();
        textFieldEndHour.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldEndHour.setColumns(10);
        textFieldEndHour.setBounds(505, 71, 43, 22);
        panel_1.add(textFieldEndHour);

        textFieldNrProductsOrdered = new JTextField();
        textFieldNrProductsOrdered.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldNrProductsOrdered.setColumns(10);
        textFieldNrProductsOrdered.setBounds(452, 111, 43, 22);
        panel_1.add(textFieldNrProductsOrdered);

        textFieldNrOrders = new JTextField();
        textFieldNrOrders.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldNrOrders.setColumns(10);
        textFieldNrOrders.setBounds(452, 160, 43, 22);
        panel_1.add(textFieldNrOrders);

        textFieldPrice = new JTextField();
        textFieldPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldPrice.setColumns(10);
        textFieldPrice.setBounds(505, 160, 43, 22);
        panel_1.add(textFieldPrice);

        textFieldNrOfOrders = new JTextField();
        textFieldNrOfOrders.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldNrOfOrders.setColumns(10);
        textFieldNrOfOrders.setBounds(591, 210, 43, 22);
        panel_1.add(textFieldNrOfOrders);

        comboBox = new JComboBox<>();
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"}));
        comboBox.setBounds(449, 210, 111, 21);
        panel_1.add(comboBox);

        JLabel lblGenerateReports = new JLabel("Generate Reports");
        lblGenerateReports.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblGenerateReports.setBounds(492, 10, 174, 25);
        panel_1.add(lblGenerateReports);

        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setBounds(664, 56, 464, 402);
        panel_1.add(scrollPane2);

        tableReports = new JTable();
        tableReports.setFont(new Font("Tahoma", Font.PLAIN, 14));
        scrollPane2.setViewportView(tableReports);
        tableReports.setAutoCreateRowSorter(true);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton[0]);
        buttonGroup.add(radioButton[1]);
        buttonGroup.add(radioButton[2]);
        buttonGroup.add(radioButton[3]);

        this.setVisible(true);
    }

    public void addListeners(ActionListener addBtn, ActionListener importBtn, ActionListener deleteBtn, ActionListener modifyBtn, ActionListener composeBtn, ActionListener generateBtn) {
        btnAdd.addActionListener(addBtn);
        btnImport.addActionListener(importBtn);
        btnDelete.addActionListener(deleteBtn);
        btnModify.addActionListener(modifyBtn);
        btnCompose.addActionListener(composeBtn);
        btnGenerateReport.addActionListener(generateBtn);
    }

    /**
     * metoda de actualizare a tabelului de produse din gui
     *
     * @param dataProducts tabelul de produse
     */
    public void refreshTable(Object[][] dataProducts) {
        tableProducts.setModel(new DefaultTableModel(dataProducts, tableProductsHeader));
    }

    /**
     * metoda de actualizar a tabelului de rapoarte
     *
     * @param data datele generate in urma raportului
     */
    public void refreshReportTable(Object[][] data) {
        DefaultTableModel model = new DefaultTableModel(data, data[0]);
        model.removeRow(0);
        tableReports.setModel(model);
    }

    public String getTextFieldStartHour() {
        return textFieldStartHour.getText();
    }

    public String getTextFieldEndHour() {
        return textFieldEndHour.getText();
    }

    public String getTextFieldNrProductsOrdered() {
        return textFieldNrProductsOrdered.getText();
    }

    public String getTextFieldNrOrders() {
        return textFieldNrOrders.getText();
    }

    public String getTextFieldPrice() {
        return textFieldPrice.getText();
    }

    public String getTextFieldNrOfOrders() {
        return textFieldNrOfOrders.getText();
    }

    public int getComboBox() {
        switch (String.valueOf(comboBox.getSelectedItem())) {
            case "MONDAY" -> {
                return 1;
            }
            case "TUESDAY" -> {
                return 2;
            }
            case "WEDNESDAY" -> {
                return 3;
            }
            case "THURSDAY" -> {
                return 4;
            }
            case "FRIDAY" -> {
                return 5;
            }
            case "SATURDAY" -> {
                return 6;
            }
            case "SUNDAY" -> {
                return 0;
            }
        }
        return 0;
    }

    /**
     * returneaza id ul JRadioButton ului selectat
     *
     * @return id-ul butonului
     */
    public int getSelectedOption() {
        for (int i = 0; i < 4; i++)
            if (radioButton[i].isSelected())
                return i + 1;
        return 0;
    }

    /**
     * returneaza indicele liniilor selectate din tabela
     *
     * @return array cu indicele liniilor selectate
     */
    public int[] getSelectedRows() {
        return tableProducts.getSelectedRows();
    }

    /**
     * valoarea tabelei la elementul dat
     *
     * @param row    linia
     * @param column coloana
     * @return valoare din tabela la indicele [row,column]
     */
    public String tableValueAt(int row, int column) {
        return String.valueOf(tableProducts.getValueAt(row, column));
    }

    /**
     * intr-o fereastra pop up este afisat un mesaj
     *
     * @param message mesajul supus afisarii
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
