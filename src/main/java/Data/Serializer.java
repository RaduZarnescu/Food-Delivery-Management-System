package Data;

import Business.DeliveryService;
import Business.User;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Radu Zarnescu
 * @version 1.0
 * The name describes what this class represents. It is used to serialize any object and to deserialize the DeliveryService class and an array list of users.
 */

public class Serializer {
    public Serializer() {
    }

    public static void serialize(Object object, String file){
        try
        {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public static ArrayList<User> deserializeUsers() {

        ArrayList<User> result = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("users.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            result = (ArrayList) ois.readObject();

            ois.close();
            fis.close();

            return result;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
    }

    public static DeliveryService deserializeService(){

        DeliveryService deliveryService = new DeliveryService();
        try {
            FileInputStream fis = new FileInputStream("service.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            deliveryService = (DeliveryService) ois.readObject();

            ois.close();
            fis.close();

            return deliveryService;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
    }
}
