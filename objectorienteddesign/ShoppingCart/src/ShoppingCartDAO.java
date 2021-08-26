public interface ShoppingCartDAO {
    void printAllAvailableItems();
    boolean isQuantityAvailable(int itemIndex, int quantity);
    void addItemsToBucket(int itemIndex, int quantity);
    void printBucket();
    void removeNonDeliverableItemsToPIN(int pinCode);
}
