public class ShoppingCart implements Cart {
    ShoppingCartDAOImpl connectToData = new ShoppingCartDAOImpl();

    @Override
    public void showAvailableItems() {
        connectToData.printAllAvailableItems();
    }

    @Override
    public boolean addItemToMyCart(int itemNo, int quantity) {
        if (!connectToData.isQuantityAvailable(itemNo - 1, quantity)) {
            System.out.println(quantity + " quantity of Item " + itemNo + " is not available.");
            System.out.println("Either reorder with lesser quantity or choose other item from available items.");
            return false;
        }
        connectToData.addItemsToBucket(itemNo - 1, quantity);
        return true;
    }

    @Override
    public void checkItemsDeliverableToPIN(int pinCode) {
        connectToData.removeNonDeliverableItemsToPIN(pinCode);
    }

    @Override
    public void printMyCart() {
        connectToData.printBucket();
    }
}
