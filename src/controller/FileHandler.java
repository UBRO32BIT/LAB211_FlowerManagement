package controller;
import model.OrderSet;
import model.FlowerSet;
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
 * This class provides methods for reading and writing flowers and orders data to files using high-level I/O stream
 * 
 * @author ubro3
 */
public class FileHandler {
    private final String flowersPath;
    private final String ordersPath;
    
    /**
     * Constructor for FileHandler.
     * 
     * @param flowersPath The path of the flower data file.
     * @param ordersPath The path of the order data file.
     */
    public FileHandler(String flowersPath, String ordersPath) {
        this.flowersPath = flowersPath;
        this.ordersPath = ordersPath;
    }
    
    /**
     * Reads order data from a file and returns an OrderSet object.
     *
     * @return The OrderSet object containing the read order data.
     * @throws IOException if there is an error reading the file.
     * @throws ClassNotFoundException if the Order class is not found during deserialization.
     */
    public OrderSet readOrder() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(ordersPath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        OrderSet orders = new OrderSet();
        Order order;
        try {
            // Loop to read a single object from the file
            while ((order = (Order) ois.readObject()) != null) {
                orders.add(order);
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
     * Reads flower data from a file and returns a FlowerSet object.
     *
     * @return The FlowerSet object containing the read flower data.
     * @throws IOException if there is an error reading the file.
     * @throws ClassNotFoundException if the Flower class is not found during deserialization.
     */
    public FlowerSet readFlower() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(flowersPath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        FlowerSet flowers = new FlowerSet();
        Flower flower;
        try {
            // Loop to read a single object from the file
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
     * Writes order data to a file.
     *
     * @param orders The OrderSet object containing the order data to write.
     * @throws IOException if there is an error writing the file.
     */
    public void writeOrderFile(OrderSet orders) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(ordersPath);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (Order order : orders) {
                oos.writeObject(order);
            }
        }
    }
    
    /**
     * Writes flower data to a file.
     *
     * @param flowers The FlowerSet object containing the flower data to write.
     * @throws IOException if there is an error writing the file.
     */
    public void writeFlowerFile(FlowerSet flowers) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(flowersPath);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (Flower flower : flowers) {
                oos.writeObject(flower);
            }
        }
    }
}
