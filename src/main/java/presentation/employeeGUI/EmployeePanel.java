package presentation.employeeGUI;

import business.DeliveryService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

/**
 * interfata grafica pentru panoul angajatilor
 */
@SuppressWarnings("deprecation")
public class EmployeePanel extends JFrame implements Observer, WindowListener {

    private final JTable table;

    private final String[] tableHeader = new String[]{"Order ID", "Date", "Ordered Products"};

    public EmployeePanel() {
        this.setTitle("Employee Panel");
        this.setBounds(100, 100, 450, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(this);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 588, 342);
        this.getContentPane().add(scrollPane);

        table = new JTable();
        refreshTable();
        table.setAutoCreateRowSorter(true);
        scrollPane.setViewportView(table);

        this.setVisible(true);
    }

    /**
     * reactualizeaza continului tabelei cu comenzile curente din lista de comenzi
     */
    private void refreshTable() {
        table.setModel(new DefaultTableModel(DeliveryService.getClassInstance().getOrders(), tableHeader));
    }


    @Override
    public void update(Observable o, Object arg) {
        refreshTable();
        JOptionPane.showMessageDialog(this, arg, "Information", JOptionPane.INFORMATION_MESSAGE);
    }


    // urmeaza metodele suprascrise in urma implementarii interfetei WindowListener
    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
        DeliveryService.getClassInstance().deleteObserver(this);
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
