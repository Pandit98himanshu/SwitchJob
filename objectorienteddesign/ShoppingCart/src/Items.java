import java.util.*;

public class Items {
    private String name;
    private double price;
    private int quantity;
    private List<Integer> pinCodes;

    public Items(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.pinCodes = null;
    }

    public Items(String name, double price, int quantity, List<Integer> pinCodes) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.pinCodes = new ArrayList<>(pinCodes);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Integer> getPinCodes() {
        return pinCodes;
    }

    public void setPinCodes(List<Integer> pinCodes) {
        this.pinCodes = pinCodes;
    }
}
