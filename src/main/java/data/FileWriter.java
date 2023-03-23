package data;

import business.Order;

import java.io.File;
import java.io.IOException;

/**
 * calsa principala pentru metoda de generare a fisierelor
 */
public class FileWriter {

    /**
     * metoda creaza o chitanta fisier .txt pentru comanda dat ca parametru
     *
     * @param o comanda propriu zisa
     */
    public static void createBill(Order o) {
        String fileName = "order_" + o.getOrderID() + ".txt";
        try {
            java.io.FileWriter writer = new java.io.FileWriter(new File("./_orders", fileName));
            writer.write("\tOrder " + o.getOrderID() + "\n\n");
            writer.write("Client ID: " + o.getClientID() + "\n");
            writer.write("Date: " + o.getDate() + "\n\n");
            writer.write("Product List:\n");
            writer.write(o.toString());
            writer.write("\nTotal: " + o.getPrice() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
