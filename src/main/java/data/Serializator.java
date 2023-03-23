package data;

import java.io.*;

/**
 * clasa principala pentru serializarea si deserializarea datelor la pronirea si actualizarea datelor interne ale aplicatiei
 */
public class Serializator {

    public static final byte USERS = 0;
    public static final byte PRODUCTS = 1;
    public static final byte ORDERS = 2;

    private static String getIOFile(byte flag) {
        return switch (flag) {
            case 0 -> "users.txt";
            case 1 -> "products.txt";
            case 2 -> "orders.txt";
            default -> null;
        };
    }

    /**
     * metoda de serializare a datelor
     *
     * @param obj  obiectul supus serializarii
     * @param flag identificatorul tipului de obiect
     */
    public static void serialize(Object obj, byte flag) {
        try {
            FileOutputStream fileOut = new FileOutputStream(getIOFile(flag));
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * metoda de deserializare a datelor
     *
     * @param flag identificatorul tipului de obiect
     * @return obiectul deserializat
     */
    public static Object deserialize(byte flag) {
        Object data = null;
        FileInputStream fileIn;
        try {
            fileIn = new FileInputStream(getIOFile(flag));
            ObjectInputStream in = new ObjectInputStream(fileIn);
            data = in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Dezactivati deserializarea pentru " + getIOFile(flag));
        }
        return data;
    }
}
