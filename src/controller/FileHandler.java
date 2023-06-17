package controller;
import model.OrderList;
import model.FlowerList;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import model.Flower;
import model.Order;
/**
 * Class to manage file I/O operations.
 * This class provides methods for reading and writing nurse and flower data to files,
 as well as writing log files.
 * 
 * @author ubro3
 */
public class FileHandler {
    private final String flowersPath;
    private final String ordersPath;
    public FileHandler(String flowersPath, String ordersPath) {
        this.flowersPath = flowersPath;
        this.ordersPath = ordersPath;
    }
    /**
     * Reads nurse data from a file and returns a NurseList object.
     *
     * @param fileName The name of the file to read nurse data from.
     * @return The NurseList object containing the read nurse data.
     * @throws IOException if there is an error reading the file.
     * @throws ClassNotFoundException if the Nurse class is not found during de-serialization.
     */
    public OrderList readOrder() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(ordersPath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        OrderList orders = new OrderList();
        Order order;
        try {
            // Loop to read single object in the file
            while ((order = (Order) ois.readObject()) != null) {
                orders.put(order.getOrderID(), order);
            }
        } catch (EOFException e) {
            // Reached the end of the file, no need to do anything
        } finally {
            ois.close();
            fis.close();
        }
        return orders;
    }
    
    /**
     * Reads flower data from a file and returns a PatientList object.
     *
     * @param fileName The name of the file to read flower data from.
     * @return The PatientList object containing the read flower data.
     * @throws IOException if there is an error reading the file.
     * @throws ClassNotFoundException if the Patient class is not found during de-serialization.
     */
    public FlowerList readFlower() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(flowersPath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        FlowerList flowers = new FlowerList();
        Flower flower;
        try {
            while ((flower = (Flower) ois.readObject()) != null) {
                flowers.add(flower);
            }
        } catch (EOFException e) {
            // Reached the end of the file, no need to do anything
        } finally {
            ois.close();
            fis.close();
        }
        return flowers;
    }
    
    /**
     * Writes nurse data to a file.
     *
     * @param orders   The OrderList object containing the order data to write.
     * @param filePath The path of the file to write the nurse data to.
     * @throws IOException if there is an error writing the file.
     */
    public void writeOrderFile(OrderList orders) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(ordersPath);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (Order order : orders.values()) {
                oos.writeObject(order);
            }
        }
    }
    
    /**
     * Writes flower data to a file.
     *
     * @param patients The PatientList object containing the flower data to write.
     * @param filePath The path of the file to write the flower data to.
     * @throws IOException if there is an error writing the file.
     */
    public void writeFlowerFile(FlowerList flowers) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(flowersPath);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (Flower flower : flowers) {
                oos.writeObject(flower);
            }
        }
    }
}
