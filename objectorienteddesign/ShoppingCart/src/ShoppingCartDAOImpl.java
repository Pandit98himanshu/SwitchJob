package objectorienteddesign.ShoppingCart.src;

import java.util.*;

public class ShoppingCartDAOImpl implements ShoppingCartDAO {
    private static List<Items> availableItems = new ArrayList<>();
    private static List<Items> bucket = new ArrayList<>();

    static {
        availableItems.add(new Items("iPhone 12", 120000.00, 2, new ArrayList<>(Arrays.asList(813203, 700014, 500001))));
        availableItems.add(new Items("iPhone 11", 49999.99, 2, new ArrayList<>(Arrays.asList(700014, 500001))));
        availableItems.add(new Items("OnePlus 9 ", 29999.98, 5, new ArrayList<>(Arrays.asList(813203, 700014, 700102))));
        availableItems.add(new Items("Samsung Note", 114998.45, 3, new ArrayList<>(Arrays.asList(813203, 500001))));
        availableItems.add(new Items("Nokia 510", 1200.65, 7, new ArrayList<>(Arrays.asList(813203, 700014, 700102, 120003))));
    }

    @Override
    public void printBucket() {
        if (bucket.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        int counter = 1;
        double totalPrice = 0d;
        System.out.println("\tItem name\t\tQuantity\t\tPrice");
        for (Items item : bucket) {
            totalPrice += item.getQuantity() * item.getPrice();
            System.out.println(counter + ". " + item.getName() + "\t\t\t" + item.getQuantity() + "\t\t\t" + item.getPrice());
            counter++;
        }
        System.out.println("Total Order Amount = " + totalPrice);
    }

    @Override
    public void removeNonDeliverableItemsToPIN(int pinCode) {
        List<Items> finalList = new ArrayList<>();
        bucket.stream().filter(items -> isItemDeliverableToPIN(items, pinCode)).forEach(finalList::add);
        finalList.forEach(items -> System.out.print(items.getName()));
        bucket = finalList;
    }

    public boolean isItemDeliverableToPIN(Items item, int pinCode) {
        for (Items availableItem : availableItems) {
            if (availableItem.getName().equals(item.getName())) {
                List<Integer> pinCodes = availableItem.getPinCodes();
                if(!pinCodes.contains(pinCode)) {
                    System.out.println(item.getName() + " can not be delivered to PIN " + pinCode);
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public void addItemsToBucket(int itemIndex, int quantity) {
        String itemName = availableItems.get(itemIndex).getName();
        double price = availableItems.get(itemIndex).getPrice();
        bucket.add(new Items(itemName, quantity, price));
    }

    @Override
    public void printAllAvailableItems() {
        int counter = 1;
        System.out.println("\tItem name\t\tQuantity\t\tPrice");
        for (Items item : availableItems) {
            System.out.println(counter + ". " + item.getName() + "\t\t\t" + item.getQuantity() + "\t\t\t" + item.getPrice());
            counter++;
        }
    }

    @Override
    public boolean isQuantityAvailable(int itemIndex, int quantity) {
        Items item = availableItems.get(itemIndex);
        return item.getQuantity() >= quantity;
    }
}
